package com.wujing.easyframe.http;


import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.wujing.jframe.utils.JEmptyUtils;
import com.wujing.jframe.utils.http.HttpCallBack;
import com.wujing.jframe.utils.http.IHttpEngine;
import com.wujing.jframe.utils.http.JHttp;
import com.wujing.jframe.utils.log.JLog;

import org.apache.http.Header;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;

/*
*  author:悟静
* 这是android-async-http网络请求框架,提供方法有get,post,二进制请求数据，上传各种文件等。
* */
public class AsyncHttpEngine implements IHttpEngine {
    private static AsyncHttpClient client;
    private static String data;

    public AsyncHttpEngine() {
        client = new AsyncHttpClient();
        client.setTimeout(7000);
    }

    //取消网络请求。
    public static void cancelRequests(Context context, boolean mayInterruptIfRunning) {
        client.cancelRequests(context, mayInterruptIfRunning);
    }
    @Override
    public void get(String url, Map<String, Object> params, final HttpCallBack callBack) {
        JLog.e("--", url);
        JLog.e("--", "params:" + params);
        client.get(url+getUrlParamsByMap(params),new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                data = new String(responseBody);
                if (!JEmptyUtils.isEmpty(data)) {
                    JHttp.handler.post(new Runnable() {
                        @Override
                        public void run() {
                            JLog.json("---", data);
                            callBack.onSuccess(JSON.parseObject(data, JHttp.analysisClassInfo(callBack)));
                        }
                    });
                } else {
                    JHttp.handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onFailed(data.toString());
                        }
                    });
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                callBack.onFailed(error.toString());
            }
        });
    }
    //BinaryHttpResponseHandler用于获取二进制数据如图片和其他文件
    @Override
    public void otherGet(String url, Map<String, Object> params, final HttpCallBack callBack) {
            client.get(url, new BinaryHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] binaryData) {
                    data = new String(binaryData);
                    if (!JEmptyUtils.isEmpty(data)) {
                        JHttp.handler.post(new Runnable() {
                            @Override
                            public void run() {
                                JLog.json("---", data);
                                callBack.onSuccess(JSON.parseObject(data, JHttp.analysisClassInfo(callBack)));
                            }
                        });
                    } else {
                        JHttp.handler.post(new Runnable() {
                            @Override
                            public void run() {
                                callBack.onFailed(data.toString());
                            }
                        });
                    }
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] binaryData, Throwable error) {
                    callBack.onFailed(error.toString());
                }
            });
    }

    @Override
    public void post(String url, Map<String, Object> params, final HttpCallBack callBack) {
        RequestParams param = new RequestParams();
        if (null != params && !params.isEmpty()) {
            for (String key : params.keySet()) {
                Object value = params.get(key);
                if (value instanceof File) {
                    File file = (File) value;
                    try {
                        param.put(key, file);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } else if (value instanceof ArrayList) {
                    ArrayList<Object> lists = (ArrayList) value;
                    for (Object obj : lists) {
                        if (obj instanceof File) {
                            File file = (File) obj;
                            try {
                                param.put(key, file);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } else if (value instanceof File[]) {
                    File[] file = (File[]) value;
                    for (int i = 0; i < file.length; i++) {
                        try {
                            param.put(key, file[i]);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    param.put(key, value);
                }
            }
        }
        JLog.e("--", url);
        JLog.map(params);
        client.post(url, param, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                data = new String(responseBody);
                if (!JEmptyUtils.isEmpty(data)) {
                    JHttp.handler.post(new Runnable() {
                        @Override
                        public void run() {
                            JLog.json("---", data);
                            callBack.onSuccess(JSON.parseObject(data, JHttp.analysisClassInfo(callBack)));
                        }
                    });
                } else {
                    JHttp.handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onFailed(data.toString());
                        }
                    });
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                callBack.onFailed(error.toString());
            }
        });
    }

    @Override
    public void otherPost(String url, Map<String, Object> params, HttpCallBack callBack) {
    }

    private String getUrlParamsByMap(Map<String, Object> map) {
        if (map == null||map.isEmpty()) {
            return "";
        }
        StringBuffer params = new StringBuffer("?");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            params.append(entry.getKey());
            params.append("=");
            params.append(entry.getValue());
            params.append("&");
        }
        String str=params.toString();
        return str.substring(0,str.length()-1);
    }
}
