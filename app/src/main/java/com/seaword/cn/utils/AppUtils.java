package com.seaword.cn.utils;

import android.content.Context;

/**
 * Created by zl on 2018/5/22.
 * APP工具类 -- 以全局的context
 */

public class AppUtils {
    private static Context mContext;

    public static void init(Context context){
        mContext = context;
    }

    public static Context getmContext(){
        return mContext;
    }

}
