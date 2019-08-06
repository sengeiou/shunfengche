package com.windmillsteward.jukutech.utils;

import java.math.BigDecimal;

public class CalculateUtils {
    /**
     * 加法
     *
     * @param var1
     * @param var2
     * @return
     */
    public static double add(double var1, double var2) {
        BigDecimal b1 = new BigDecimal(Double.toString(var1));
        BigDecimal b2 = new BigDecimal(Double.toString(var2));
        return b1.add(b2).doubleValue();

    }

    private BigDecimal result;

    public CalculateUtils adds(double var1) {
        return adds(Double.toString(var1));
    }

    /**
     * 加
     *
     * @param var1
     * @return
     */
    public CalculateUtils adds(String var1) {
        BigDecimal b1 = new BigDecimal(var1);
        if (result == null)
            result = b1;
        else
            result = result.add(b1);
        return this;
    }

    /**
     * 减
     *
     * @param var1
     * @return
     */
    public CalculateUtils subs(String var1) {
        BigDecimal b1 = new BigDecimal(var1);
        if (result == null)
            result = b1.multiply(new BigDecimal("-1"));
        else
            result = result.subtract(b1);
        return this;
    }

    /**
     * 乘
     *
     * @param var1
     * @return
     */
    public CalculateUtils multiply(String var1) {
        BigDecimal b1 = new BigDecimal(var1);
        if (result == null)
            result = b1.multiply(new BigDecimal("0"));
        else
            result = result.multiply(b1);
        return this;
    }

    /**
     * 乘
     *
     * @param var1
     * @return
     */
    public CalculateUtils multiply(double var1) {
        return multiply(Double.toString(var1));
    }

    /**
     * 除
     *
     * @param var1
     * @return
     */
    public CalculateUtils divide(String var1, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or ");
        }
        BigDecimal b1 = new BigDecimal(var1);
        if (result == null)
            result = b1.multiply(new BigDecimal("0"));
        else
            result =  result.divide(b1, scale, BigDecimal.ROUND_HALF_UP);
        return this;
    }

    /**
     * 除
     *
     * @param var1
     * @return
     */
    public CalculateUtils divide(double var1, int scale) {
        return divide(Double.toString(var1), scale);
    }

    /**
     * 设置初始值
     *
     * @param var1
     * @return
     */
    public CalculateUtils init(String var1) {
        result = new BigDecimal(var1);
        return this;
    }

    /**
     * 设置初始值
     *
     * @param var1
     * @return
     */
    public CalculateUtils init(double var1) {
        return init(Double.toString(var1));
    }

    /**
     * 计算
     *
     * @return
     */
    public BigDecimal calculate() {
        return result;
    }

    /**
     * 减
     *
     * @param var1
     * @return
     */
    public CalculateUtils subs(double var1) {
        return subs(Double.toString(var1));
    }

    /**
     * 减法
     *
     * @param var1
     * @param var2
     * @return
     */

    public static double sub(double var1, double var2) {
        BigDecimal b1 = new BigDecimal(Double.toString(var1));
        BigDecimal b2 = new BigDecimal(Double.toString(var2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 乘法
     *
     * @param var1
     * @param var2
     * @return
     */
    public static double mul(double var1, double var2) {
        BigDecimal b1 = new BigDecimal(Double.toString(var1));
        BigDecimal b2 = new BigDecimal(Double.toString(var2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 除法
     *
     * @param v1
     * @param v2
     * @param scale 精度，到小数点后几位
     * @return
     */

    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or ");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();

    }

    /**
     * 四舍五入
     *
     * @param v
     * @param scale 精确位数
     * @return
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}