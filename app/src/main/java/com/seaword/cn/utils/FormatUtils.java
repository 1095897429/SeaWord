package com.seaword.cn.utils;

/**
 * Created by zl on 2018/5/24.
 */

public class FormatUtils {

    /** time = 173 显示为xx:xx的形式*/
    public static String formatDuration(String time){
        Integer integer = new Integer(time);
        StringBuffer buffer = new StringBuffer();
        int minute = integer / 60 ;
        int second = integer % 60 ;
        if(minute < 10){
            buffer.append("0" + minute);
        }else{
            buffer.append(minute);
        }

        buffer.append(":");

        if(second < 10){
            buffer.append("0" + second);
        }else{
            buffer.append(second);
        }
        return buffer.toString();
    }
}
