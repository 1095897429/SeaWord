package com.seaword.cn.utils;

import android.content.Context;
import android.os.Environment;

/**
 * Created by zl on 2018/5/22.
 */

public class FileUtils {

    /** 判断是否有SD卡 */
    public static boolean isSdCardAvailable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /** 创建根缓存目录 */
    public static String createRootPath(Context context) {
        String path;
        if(isSdCardAvailable()){
            // /sdcard/Android/data/<application package>/cache
            path = context.getExternalCacheDir().getPath();
        }else
            // /data/data/<application package>/cache
            path = context.getCacheDir().getPath();
        return path;
    }

}
