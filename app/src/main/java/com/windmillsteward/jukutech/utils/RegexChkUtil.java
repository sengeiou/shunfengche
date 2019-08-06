package com.windmillsteward.jukutech.utils;

import android.text.Editable;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则检查匹配字符串
 *
 * @author zhuxian
 */
public class RegexChkUtil {

    /**
     * 正则匹配调用
     *
     * @param reg    正则表达式
     * @param string 待匹配的字符串
     * @return true通过，false未通过
     */
    public static boolean startCheck(String reg, String string) {
        boolean tem = false;
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(string);
        tem = matcher.matches();
        return tem;
    }

    /**
     * 检验整数,适用于正整数、负整数、0，负整数不能以-0开头, 正整数不能以0开头
     */
    public static boolean checkNr(String nr) {
        String reg = "^(-?)[1-9]+\\d*|0";
        return startCheck(reg, nr);
    }


    /**
     * 判断字符串是否为数字，包括整数和小数
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("^[-+]?[0-9]+(\\.[0-9]+)?$");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 只允许2位小数
     * @param edt
     */
    public static void saveTwoPoint(Editable edt) {
        String temp = edt.toString();
        int posDot = temp.indexOf(".");//只允许输入两位小数
        if (posDot <= 0) return;
        if (temp.length() - posDot - 1 > 2) {
            edt.delete(posDot + 3, posDot + 4);
        }
    }

    /**
     * 提供精确的小数位四舍五入处理。
     *
     * @param v 需要四舍五入的数字
     *          小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static String round(float v, String scale) {
        if (v == 0.0) {
            return "0.00";
        }
        if (v < 1 && v >0){//小于1大于0
            DecimalFormat df = new DecimalFormat(scale);
            return df.format(v);
        }
        int i = (int) v;
        if (i == 0) {
            return String.valueOf(v);
        } else {
            DecimalFormat df = new DecimalFormat(scale);
            return df.format(v);
        }
    }

    /**
     * 手机号码验证,11位，不知道详细的手机号码段，只是验证开头必须是1和位数
     */
    public static boolean checkCellPhone(String cellPhoneNr) {
        String reg = "^[1][\\d]{10}";
        return startCheck(reg, cellPhoneNr);
    }

    /**
     * 验证港澳地区手机号
     */
    public static boolean checkCellPhoneHongKong(String cellPhoneNr) {
        String reg = "^[6][\\d]{8}";
        return startCheck(reg, cellPhoneNr);
    }

    /**
     * 检验空白符
     */
    public static boolean checkWhiteLine(String line) {
        String regex = "(\\s|\\t|\\r)+";
        return startCheck(regex, line);
    }

    /**
     * 检验用户名 取值范围为a-z,A-Z,0-9,"_",汉字，不能以"_"结尾 用户名有最小长度和最大长度限制，比如用户名必须是4-20位
     */
    public static boolean checkUsername(String username, int min, int max) {
        String regex = "[\\w\u4e00-\u9fa5]{" + min + "," + max + "}(?<!_)";
        return startCheck(regex, username);
    }

    /**
     * 检验用户名 取值范围为a-z,A-Z,0-9,"_",汉字，不能以"_"结尾 有最小位数限制的用户名，比如：用户名最少为4位字符
     */
    public static boolean checkUsername(String username, int min) {
        // [\\w\u4e00-\u9fa5]{2,}?
        String regex = "[\\w\u4e00-\u9fa5]{" + min + ",}(?<!_)";
        return startCheck(regex, username);
    }

    /**
     * 验证国内电话号码 格式：010-67676767，区号长度3-4位，必须以"0"开头，号码是7-8位
     */
    public static boolean checkPhoneNr(String phoneNr) {
        String regex = "^[0]\\d{2,3}\\-\\d{7,8}";
        return startCheck(regex, phoneNr);
    }
}
