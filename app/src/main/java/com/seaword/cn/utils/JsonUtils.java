package com.seaword.cn.utils;

import java.io.InputStream;

/**
 * Created by zl on 2018/5/23.
 */

public class JsonUtils {

    /** jsonFile为json文件的名字*/
    public static String readJson(String jsonFileName){
        InputStream inputStream = FileUtils.openAssetFile(AppUtils.getmContext(),jsonFileName);
        String json = IOUtils.streamToString(inputStream);
        return json;
    }
}
