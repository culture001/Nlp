package org.ansj.domain;

import java.io.Serializable;
import java.util.Map;

import org.nlpcn.commons.lang.dat.Item;

public class AnsjItem extends Item implements Serializable{

	private static final long serialVersionUID = 1L;

	public static final AnsjItem NULL = new AnsjItem();

	public static final AnsjItem BEGIN = new AnsjItem();

	public static final AnsjItem END = new AnsjItem();

	static {
		NULL.base = 0;

		BEGIN.index = 0;
		BEGIN.termNatures = TermNatures.BEGIN;

		END.index = -1;
		END.termNatures = TermNatures.END;
	}

	public String param;

	/**
	 * frequency : 词性词典,以及词性的相关权重
	 */
	public TermNatures termNatures = null ;

	public Map<Integer,Integer> bigramEntryMap =  null ;

	@Override
	public void init(String[] split) {
		this.name = split[0];
		this.param = split[1];
	}

	@Override
	public void initValue(String[] split) {
		index = Integer.parseInt(split[0]);
		base = Integer.parseInt(split[2]);//词的index为前缀词的base+末字。如index(泰晤士报)=base(泰晤士)+‘报’。65536表示为叶子节点
		check = Integer.parseInt(split[3]);//是词由哪个词转换过来的，即前缀。如公因数、公因式的check为118193，而118193为公因的id。而公因的check为20844，为公的id。单字为-1
		status = Byte.parseByte(split[4]); //1为词性词频为null的字、词，不能单独存在，应继续；4为圆半角英文字母及'；5为数字、小数点、百分号；2、3为词，其中2表示是个词但是还可以继续，3表示停止已经是个词了
		if (status > 1) {
			name = split[1];
			termNatures = new TermNatures(TermNature.setNatureStrToArray(split[5]), index);
		}else{
			termNatures = new TermNatures(TermNature.NULL); 
		}
	}

	@Override
	public String toText() {
		return index + "\t" + name + "\t" + base + "\t" + check + "\t" + status + "\t" + param;
	}

}
