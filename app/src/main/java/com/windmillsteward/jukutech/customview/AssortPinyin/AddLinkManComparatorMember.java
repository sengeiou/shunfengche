package com.windmillsteward.jukutech.customview.AssortPinyin;

import com.windmillsteward.jukutech.bean.CityBean;

import net.sourceforge.pinyin4j.PinyinHelper;

import java.util.Comparator;

/**
 * 汉字排序 2018 规则 城市
 * 
 * @author cyq
 * 
 */
public class AddLinkManComparatorMember implements Comparator<CityBean> {

	public int compare(CityBean lhs, CityBean rhs) {
		String ostr1 = lhs.getArea_name();// 城市名
		String ostr2 = rhs.getArea_name();
		for (int i = 0; i < ostr1.length() && i < ostr2.length(); i++) {

			int codePoint1 = ostr1.charAt(i);
			int codePoint2 = ostr2.charAt(i);
			if (Character.isSupplementaryCodePoint(codePoint1)
					|| Character.isSupplementaryCodePoint(codePoint2)) {
				i++;
			}
			if (codePoint1 != codePoint2) {
				if (Character.isSupplementaryCodePoint(codePoint1)
						|| Character.isSupplementaryCodePoint(codePoint2)) {
					return codePoint1 - codePoint2;
				}
				String pinyin1 = pinyin((char) codePoint1);
				String pinyin2 = pinyin((char) codePoint2);

				if (pinyin1 != null && pinyin2 != null) { // 两个字符都是汉字
					if (!pinyin1.equals(pinyin2)) {
						return pinyin1.compareTo(pinyin2);
					}
				} else {
					return codePoint1 - codePoint2;
				}
			}
		}
		return ostr1.length() - ostr2.length();
	}

	// 获得汉字拼音的首字符
	private String pinyin(char c) {
		String[] pinyins = PinyinHelper.toHanyuPinyinStringArray(c);
		if (pinyins == null) {
			return null;
		}
		return pinyins[0];
	}

}
