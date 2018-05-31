package com.seaword.cn.network.helper;

import android.content.Context;

import com.seaword.cn.network.support.ApiConstants;
import com.seaword.cn.utils.AppUtils;
import com.seaword.cn.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by zl on 2018/5/22.
 * okHttp 帮助类
 * 1.需要Context -- 就把application的context给它
 */

public class OkHttpHelper {
    private static final long DEFAULT_READ_TIMEOUT_MILLIS = 20 * 1000;  //读取时间
    private static final long DEFAULT_WRITE_TIMEOUT_MILLIS = 20 * 1000; //写入时间
    private static final long DEFAULT_CONNECT_TIMEOUT_MILLIS = 20 * 1000; //超时时间
    private static final long HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = 20 * 1024 * 1024;//最大缓存 -- 设置20M
    private static final int CACHE_STALE_LONG = 60 * 60 * 24 * 7;  //长缓存有效期为7天

    private static OkHttpHelper sInstance;//单例
    private OkHttpClient mOkHttpClient;//依赖OkHttpClient
    private Context mContext = AppUtils.getmContext();

    public static OkHttpHelper getInstance() {
        if (sInstance == null) {
            synchronized (OkHttpHelper.class) {
                if (sInstance == null) {
                    sInstance = new OkHttpHelper();
                }
            }
        }
        return sInstance;
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    private OkHttpHelper(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);  //包含header、body数据
        mOkHttpClient = new OkHttpClient.Builder()
                .readTimeout(DEFAULT_READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                .writeTimeout(DEFAULT_WRITE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                .connectTimeout(DEFAULT_CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                .cache(getCache(mContext))//设置缓存
                .retryOnConnectionFailure(true) // 失败重发
                //http数据log，日志中打印出HTTP请求&响应数据
                .addInterceptor(loggingInterceptor)
                .addInterceptor(new UserAgentInterceptor())
                .build();
    }

    /** 获取缓存路径 -- 创建根缓存目录 */
    private Cache getCache(Context context) {
        Cache cache;
        String path = FileUtils.createRootPath(context);
        File baseDir = new File(path);//通过path构造文件
        File cacheDir = new File(baseDir,"CopyCache");////通过文件 + 字符串构造文件
        cache = new Cache(cacheDir,HTTP_RESPONSE_DISK_CACHE_MAX_SIZE);
        return cache;
    }


    /**
     * 添加UA拦截器，B站请求API需要加上UA才能正常使用
     */
    private static class UserAgentInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            Request requestWithUserAgent = originalRequest.newBuilder()
                    .removeHeader("User-Agent")
                    .addHeader("User-Agent", ApiConstants.COMMON_UA_STR)
                    .build();
            return chain.proceed(requestWithUserAgent);
        }
    }

}
