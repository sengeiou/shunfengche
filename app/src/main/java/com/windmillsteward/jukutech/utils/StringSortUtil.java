package com.windmillsteward.jukutech.utils;

/**
 * 描述：字符串排序(仅对int)
 * author:cyq
 * 2017/6/25
 * Created by 2017 广州聚酷软件科技有限公司 All Right Reserved
 */

public class StringSortUtil {

    private String data;

    // 创建将字符串中的数据进行排序的方法
    public String sortString(String s) {
        //
        // System.out.println("sortSting 启动");
        // 将字符串进行分割，转成字符串数组
        String[] c = s.split(",");
        int[] arr = new int[c.length];
        for (int i = 0; i < c.length; i++) {
            // 将字符串中的元素转成int数据类型并储存到int数组中去
            arr[i] = Integer.parseInt(c[i]);
        }
        // 对int数组中的元素进行排序
        sortIntArray(arr);
        // 将int数组转换为字符串输出
        intArrayToString(arr);
        return data;
    }

    // 将int数组中的元素转成字符串并输出
    private void intArrayToString(int[] arr) {
        // System.out.println("intArayToStirng启动");
        // TODO Auto-generated method stub
        StringBuilder sb = new StringBuilder();
//		sb.append("[ ");
        for (int i = 0; i < arr.length; i++) {
            if (i != arr.length - 1) {
                sb.append(arr[i]+",");
            }
            if (i == arr.length - 1) {
                sb.append(arr[i] );
            }
        }
        data =  sb.toString();
        System.out.println(sb.toString());

    }

    // 对int数组进行排序
    private void sortIntArray(int[] arr) {
        // System.out.println("sortInArray启动");
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j])
                    swap(arr, i, j);
            }
        }
    }

    // 对数据进行交换
    private  void swap(int[] arr, int i, int j) {
        // System.out.println("swap启动");
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
