package com.seaword.cn.network.response;

/**
 * Created by zl on 2018/5/23.
 * * 描述:统一处理HttpResponse,网络响应的基类
 * 每个返回对象都有code msg 和 result | data
 {
 "code":300,
 "msg":"成功",
 "result":[]
 }

 {
 "code":200,
 "msg":"成功",
 "result":{}
 }
 */

public class HttpResponse<T> {
    public T data;//数据
    public T result;//数据
    public String message;//信息
    public int code;//服务器状态
}
