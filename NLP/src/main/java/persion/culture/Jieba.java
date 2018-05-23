package persion.culture;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.JiebaSegmenter.SegMode;

public class Jieba {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 JiebaSegmenter segmenter = new JiebaSegmenter();
	    	System.out.println(segmenter.process("提前还钱是有必要的", SegMode.SEARCH).toString());
	    	long start = System.currentTimeMillis();
		    for (int i = 0; i <1000*10; i++) {
		    	segmenter.process("欢迎使用ansj_seg,(ansj中文分词)在这里如果你遇到什么问题都可以联系我.我一定尽我所能.帮助大家.ansj_seg更快,更准,更自由!", SegMode.SEARCH);
			}
		    System.out.println(System.currentTimeMillis()-start);

	}

}
