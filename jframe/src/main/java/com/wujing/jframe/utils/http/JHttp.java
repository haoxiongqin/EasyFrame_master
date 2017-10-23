package com.wujing.jframe.utils.http;


import com.wujing.jframe.utils.JHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

public class JHttp implements IHttpEngine {

    private static IHttpEngine httpEngine;
    private static JHttp jHttp;
    public static JHandler handler = new JHandler();

    public static void init(IHttpEngine engine){
        httpEngine=engine;
    }

    public static JHttp obtain(){
        if (httpEngine==null){
            throw new NullPointerException("Call JFrame.initXHttp(IHttpEngine httpEngine) within your Application onCreate() method." +
                    "Or extends XApplication");
        }
        if (jHttp == null) {
            jHttp = new JHttp();
        }
        return jHttp;
    }

    /**
     * 获取实体类的类型
     * @param obj
     * @return
     */
    public static Class<?> analysisClassInfo(Object obj){
        Type genType=obj.getClass().getGenericSuperclass();
        Type[] params=((ParameterizedType)genType).getActualTypeArguments();
        return (Class<?>) params[0];
    }

    @Override
    public void get(String url, Map<String, Object> params, HttpCallBack callBack) {
        httpEngine.get(url,params,callBack);
    }

    @Override
    public void otherGet(String url, Map<String, Object> params, HttpCallBack callBack) {
        httpEngine.get(url,params,callBack);
    }

    @Override
    public void post(String url, Map<String, Object> params, HttpCallBack callBack) {
        httpEngine.post(url,params,callBack);
    }

    @Override
    public void otherPost(String url, Map<String, Object> params, HttpCallBack callBack) {
        httpEngine.post(url,params,callBack);
    }
}
