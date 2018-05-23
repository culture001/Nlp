//package persion.culture;
//
//import net.sourceforge.pinyin4j.PinyinHelper;
//import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
//import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
//
//public class Pinyin4j {
//
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
////		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE); //WITHOUT_TONE--不带声调
//
//		String str = "我爱自然语言处理";
//		System.out.println(str);
//		String[] pinyin = null;
//		for (int i = 0; i < str.length(); ++i) {
//			try {
//				pinyin = PinyinHelper.toHanyuPinyinStringArray(str.charAt(i),
//						format);
//			} catch (BadHanyuPinyinOutputFormatCombination e) {
//				e.printStackTrace();
//			}
//
//			if (pinyin == null) {
//				System.out.print(str.charAt(i));
//			} else {
//				if (i != 0) {
//					System.out.print(" ");
//				}
//				System.out.print(pinyin[0]);
//			}
//		}
//
//	}
//
//}
