package com.seaword.cn.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zl on 2018/5/23.
 */

public class IOUtils {

    /** 将输入流读取的数据存BAOS  -- String */
    public static String streamToString(InputStream inputStream){
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int length;
            byte [] butter = new byte[1024];
            while((length = inputStream.read(butter)) != -1){
                baos.write(butter,0,length);
                baos.flush();//刷新缓存区
            }
            String result = baos.toString();
            baos.close();
            inputStream.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
