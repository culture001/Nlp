package org.nlpcn.commons.lang.dat;

import org.nlpcn.commons.lang.util.FileIterator;
import org.nlpcn.commons.lang.util.IOUtil;
import org.nlpcn.commons.lang.util.StringUtil;

import java.io.*;

/**
 * 双数组使用
 *
 * @author ansj
 */
public class DoubleArrayTire {

	public Item[] dat;

	public int arrayLength;

	public DoubleArrayTire() {
	}

	/**
	 * 获得dat数组
	 */
	public Item[] getDAT() {
		return dat;
	}

	/**
	 * 一个词在词典中的id
	 */
	public int getId(String str) {
		final Item item = getItem(str);
		return item != null ? item.index : 0;
	}

	/**
	 * 获得一个词语的item
	 */
	@SuppressWarnings("unchecked")
	public <T extends Item> T getItem(String str) {
		if (StringUtil.isBlank(str)) {
			return null;
		}
		if (str.length() == 1) {
			return (T) dat[str.charAt(0)];
		}

		Item item = dat[str.charAt(0)];
		if (item == null) {
			return null;
		}
		for (int i = 1; i < str.length(); i++) {
			final int checkValue = item.index;
			if (item.base + str.charAt(i) > dat.length - 1)
				return null;

			item = dat[item.base + str.charAt(i)];
			if (item == null) {
				return null;
			}
			if (item.check != -1 && item.check != checkValue) {
				return null;
			}
		}
		return (T) item;
	}

	@SuppressWarnings("unchecked")
	public <T extends Item> T getItem(int id) {
		return (T) dat[id];
	}

	public static DoubleArrayTire load(final String filePath) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filePath)));
			final DoubleArrayTire instance = new DoubleArrayTire();
			instance.dat = new Item[ois.readInt()];
			instance.arrayLength = ois.readInt();
			for (int i = 0; i < instance.arrayLength; i++) {
				final Item item = (Item) ois.readObject();
				instance.dat[item.index] = item;
			}
			return instance;
		} finally {
			if (ois != null) {
				ois.close();
			}
		}
	}

	/**
	 * 从文本中加载模型
	 * 
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws FileNotFoundException
	 */
	public static DoubleArrayTire loadText(String filePath, Class<? extends Item> cla) throws FileNotFoundException, InstantiationException,
			IllegalAccessException {
		return loadText(IOUtil.getInputStream(filePath), cla);
	}

	/**
	 * 从文本中加载模型
	 * 
	 * @throws FileNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static DoubleArrayTire loadText(InputStream is, Class<? extends Item> cla) throws FileNotFoundException, InstantiationException,
			IllegalAccessException {
		final DoubleArrayTire obj = new DoubleArrayTire();
		final FileIterator it = IOUtil.instanceFileIterator(is, IOUtil.UTF8);
		if (it == null) {
			throw new FileNotFoundException();
		}
		String temp = it.next();
		obj.arrayLength = Integer.parseInt(temp);
		obj.dat = new Item[obj.arrayLength];
		while (it.hasNext()) {
			temp = it.next();
			final Item item = cla.newInstance();
			item.initValue(temp.split("\t"));
			obj.dat[item.index] = item;
		}
		//列：index(词id),name(词),base,check,status,{词性=词频,词性=词频….}
		//eg:
//		86682	中国共	65782	107473	1	null
//		85987	中国人	65538	107473	2	{n=0}
//		107473	中国	65833	20013	2	{n=0, ns=3357, nz=0, v=0}
//		20154	人	89095	-1	2	{n=2707, q=0, r=0, v=1}
//		20013	中	85204	-1	2	{a=0, b=1, f=3234, j=449, nr=0, p=1, tg=0, v=13}
//		22269	国	114655	-1	2	{j=2, m=0, n=801, ng=0, nr=0, q=0}
		
//		26377	有	154786	-1	2	{m=2, n=0, nr=0, v=4638, vn=0}
//		38065	钱	150824	-1	2	{n=333, nr=0, q=0}
//		192851	有钱	65536	26377	3	{a=1}
//		36824	还	165951	-1	2	{c=0, d=1529, nr=0, v=38, vn=0}
//		204016	还钱	65536	26377	3	{a=1}
		
//		204016	白砂	105112	30333	2	{nr=0}
//		30333	白	173294	-1	2	{a=49, an=0, b=1, d=6, j=18, ng=1, nr=3, v=4}
//		30722	砂	131772	-1	2	{n=7}
		
		
		return obj;
	}

	/**
	 * 从文本中加载模型
	 * 
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws FileNotFoundException
	 */
	public static DoubleArrayTire loadText(String filePath) throws FileNotFoundException, InstantiationException, IllegalAccessException {
		return loadText(filePath, BasicItem.class);
	}
}
