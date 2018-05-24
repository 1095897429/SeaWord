package com.seaword.cn.utils;

/**
 * Created by zl on 2018/5/24.
 */

public class NumberUtils {

    /** num = 38631 -- 3.8631  显示为xx:xx的形式*/
    public static String format(String num) {
        Integer integer = new Integer(num);
        if(integer < 10000){
            return String.valueOf(num);
        }
        String unit = "万";
        double newNum = integer / 10000.0;
        /** %f （表示浮点数）  %d （表示整数） %s （表示字符串） -- %.1f 只保留小数点后一位数（并且是四舍五入）*/
        String numStr = String.format("%." + 1 + "f", newNum);
        return numStr + unit;
    }
}
