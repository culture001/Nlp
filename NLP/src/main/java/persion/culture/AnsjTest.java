package persion.culture;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.library.DicLibrary;
import org.ansj.recognition.impl.StopRecognition;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.nlpcn.commons.lang.tire.domain.Forest;
import org.nlpcn.commons.lang.tire.domain.Value;
import org.nlpcn.commons.lang.tire.library.Library;

import com.huaban.analysis.jieba.JiebaSegmenter.SegMode;
public class AnsjTest {
	public static void test() {
		// 我是修改后的代码111123 只关注这些词性的词 15350111 matter修改1v 分之修改了1
		
		Set<String> expectedNature = new HashSet<String>() {
			{
				add("n");
				add("v");
				add("vd");
				add("vn");
				add("vf");
				add("vx");
				add("vi");
				add("vl");
				add("vg");
				add("nt");
				add("nz");
				add("nw");
				add("nl");
				add("ng");
				add("userDefine");
				add("wh");
			}
		};
		String str = "欢迎使用ansj_seg,(ansj中文分词)在这里如果你遇到什么问题都可以联系我.我一定尽我所能.帮助大家.ansj_seg更快,更准,更自由!";
		Result result = ToAnalysis.parse(str); // 分词结果的一个封装，主要是一个List<Term>的terms
		System.out.println(result.getTerms());

		long start = System.currentTimeMillis();
	    for (int i = 0; i <1000*100; i++) {
	    	ToAnalysis.parse(str);
	    	result.getTerms();
		}
	    System.out.println(System.currentTimeMillis()-start);
	    
//		List<Term> terms = result.getTerms(); // 拿到terms
//		System.out.println(terms.size());
//
//		for (int i = 0; i < terms.size(); i++) {
//			String word = terms.get(i).getName(); // 拿到词
//			String natureStr = terms.get(i).getNatureStr(); // 拿到词性
//			if (expectedNature.contains(natureStr)) {
//				System.out.println(word + ":" + natureStr);
//			}
//		}
	}

	public static void main(String[] args) {
//		test();
		StopRecognition fitler = new StopRecognition();
//		fitler.insertStopWords("还钱"); //过滤单词
		fitler.insertStopWords("有"); //过滤单词
		DicLibrary.insert("dic", "还钱");
//		String[] paramers = new String[2];
//		paramers[0] = "userDefine";
//		paramers[1] = String.valueOf(100);
//		Value value = new Value("还钱", paramers);
//		Library.insertWord(new Forest(), value);
		Result result = ToAnalysis.parse("提前还钱是有必要的").recognition(fitler); // 分词结果的一个封装，主要是一个List<Term>的terms
		System.out.println(result.getTerms());
		Iterator<Term> iterator = result.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
		DicLibrary.delete("dic", "还钱");
//		 FilterModifWord.setUpdateDic(strHashMap);  
		result = ToAnalysis.parse("提前还钱是有必要的"); // 
		System.out.println(result.getTerms());
	}
}
