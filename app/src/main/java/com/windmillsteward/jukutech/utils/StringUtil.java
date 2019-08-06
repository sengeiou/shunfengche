package com.windmillsteward.jukutech.utils;

import android.text.TextUtils;


import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @date: on 2018/10/4
 * @author: cretin
 * @email: mxnzp_life@163.com
 * @desc: 字符串工具类
 */
public class StringUtil {
    /**
     * 是否全部为空
     *
     * @param args
     * @return
     */
    public static boolean isAllEmpty(String... args) {
        for (int i = 0; i < args.length; i++) {
            String text = args[i];
            if (!TextUtils.isEmpty(text)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 是否为空
     *
     * @param text
     * @return
     */
    public static boolean isEmpty(String text) {
        return isAllEmpty(text);
    }

    /**
     * 是否为空,不为空则返回字符串
     *
     * @param text
     * @return
     */
    public static String notEmptyBackValue(String text) {
        return TextUtils.isEmpty(text)?"":text;
    }


    /**
     * 是否全部不为空
     *
     * @param args
     * @return
     */
    public static boolean isAllNotEmpty(String... args) {
        for (int i = 0; i < args.length; i++) {
            String text = args[i];
            if (TextUtils.isEmpty(text)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 是否全部不为空
     *
     * @param args
     * @return
     */
    public static boolean hasEmptyAndNotEmpty(String... args) {
        if (args.length < 2) {
            throw new RuntimeException("至少包含两个元素");
        }
        boolean empty = false;
        boolean notEmpty = false;
        for (int i = 0; i < args.length; i++) {
            String text = args[i];
            if (TextUtils.isEmpty(text)) {
                empty = true;
            } else {
                notEmpty = true;
            }
        }
        return empty && notEmpty;
    }

    /**
     * 是否有空数据
     *
     * @param args
     * @return
     */
    public static boolean isSomeOneEmpty(String... args) {
        for (int i = 0; i < args.length; i++) {
            String text = args[i];
            if (TextUtils.isEmpty(text)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 数据签名加密处理
     *
     * @param data
     */
    public static void dataSignatureProcess(Map<String, Object> data) {
        Iterator titer = data.entrySet().iterator();
        StringBuffer sb = new StringBuffer();
        while (titer.hasNext()) {
            Map.Entry ent = (Map.Entry) titer.next();
            String keyt = ent.getKey().toString();
            String valuet = ent.getValue().toString();
            sb.append(keyt + valuet);
        }
        sb.insert(0, "fll");
        sb.insert(sb.length(), "fll");
        String s2 = sb.toString();
        // MD5加密
        String s = EncryptUtils.encryptMD5ToString(s2);
        // 字符串转换成16进制
        String s1 = str2HexStr(s);
        data.put("sys_sign", s1);
    }

    /**
     * 字符串转换成十六进制字符串
     *
     * @return String 每个Byte之间空格分隔，如: [61 6C 6B]
     */
    public static String str2HexStr(String str) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;

        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
//            sb.append(' ');
        }
        return sb.toString().trim();
    }

    /**
     * 是否是纯英文
     *
     * @param data 要匹配的数据
     * @return
     */
    public static boolean isPureEnglish(String data) {
        Pattern p = Pattern
                .compile("^[a-zA-Z]{1,10}$");
        Matcher m = p.matcher(data);
        return m.matches();
    }

    /**
     * 是否包含中文
     *
     * @param str
     * @return
     */
    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    /**
     * 获取URL中https://weqxin.com/up/2016/1020/1476971753_457814.jpg
     * 图片名称  1476971753_457814.jpg
     *
     * @param url 要获取的地址
     * @return null
     * @author lc
     * @time 2016/10/13 18:58
     */
    public static String getURLPhotoName(String url) {
        int start = url.lastIndexOf("/");
        return url.subSequence(start + 1, url.length()).toString();
    }

    /**
     * 已逗号的形式拼接字符串
     *
     * @param data
     * @return xxx, xxxx, xxxxx, xxxxxxxxxxxxx, x
     */
    public static String jointString(List<Map<String, Object>> data) {
        StringBuffer aa = new StringBuffer();
        for (int j = 0; j < data.size(); j++) {
            Map<String, Object> stringObjectMap = data.get(j);
            if (j == 0) {
                aa.append(stringObjectMap.get("path"));
            } else {
                aa.append("," + stringObjectMap.get("path"));
            }
        }
        return aa.toString();
    }


    /**
     * 已逗号的形式拼接字符串
     *
     * @param data
     * @return xxx, xxxx, xxxxx, xxxxxxxxxxxxx, x
     */
    public static String jointString(String[] data) {
        boolean fast = true;
        StringBuffer aa = new StringBuffer();
        for (int j = 0; j < data.length; j++) {
            String datum = data[j];
            if (!TextUtils.isEmpty(datum)) {
                if (fast) {
                    aa.append(datum);
                    fast = false;
                } else {
                    aa.append("," + datum);
                }
            }

        }
        return aa.toString();
    }


}
