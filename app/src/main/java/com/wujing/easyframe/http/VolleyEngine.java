package com.wujing.easyframe.http;


import com.alibaba.fastjson.JSON;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.wujing.easyframe.App;
import com.wujing.jframe.utils.JEmptyUtils;
import com.wujing.jframe.utils.JPrintUtils;
import com.wujing.jframe.utils.http.HttpCallBack;
import com.wujing.jframe.utils.http.IHttpEngine;
import com.wujing.jframe.utils.http.JHttp;
import com.wujing.jframe.utils.log.JLog;

import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/*
*  author:悟静
* 这是Volley网络请求框架，提供get,post,文件上传。 做大项目时不推荐使用volley框架,不适合大文件上传和下载,适合高频发送
* Volley自带磁盘缓存
* */
public class VolleyEngine implements IHttpEngine {
    interface ResponseListener extends Response.ErrorListener, Response.Listener {
    }
    private static RequestQueue mRequestQueue;

    public static RequestQueue getRequestQueue() {
        if (mRequestQueue == null)
            throw new RuntimeException("请先初始化mRequestQueue");
        return mRequestQueue;
    }

    public VolleyEngine() {
        if (mRequestQueue == null) {
            synchronized (VolleyEngine.class) {
                if (mRequestQueue == null) {
                    mRequestQueue = Volley.newRequestQueue(App.getAppContext());
                }
            }
        }
        mRequestQueue.start();
    }
    @Override
    public void otherPost(String url, Map<String, Object> params, HttpCallBack callBack) {

    }
    @Override
    public void otherGet(String url, Map<String, Object> params, HttpCallBack callBack) {

    }
    @Override
    public void get(String url, Map<String, Object> params, final HttpCallBack callBack) {
        if (!url.startsWith("http")) {
            url = Urls.BASE_URL + url;
        }
        JLog.json("---", url);
        JLog.json("---", "params:" + params);
        StringRequest mStringRequest = new StringRequest(Request.Method.GET, url + getUrlParamsByMap(params), new Response.Listener<String>() {
            @Override
            public void onResponse(final String response) {
                if (!JEmptyUtils.isEmpty(response)) {
                    JHttp.handler.post(new Runnable() {
                        @Override
                        public void run() {
                            JLog.json("---", response.toString());
                            callBack.onSuccess(JSON.parseObject(response, JHttp.analysisClassInfo(callBack)));
                        }
                    });
                } else {
                    JHttp.handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onFailed(response.toString());
                        }
                    });
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.onFailed(error.toString());
            }
        });
        mRequestQueue.add(mStringRequest);
    }
    /**
     * @param url
     * @param params
     * @param callBack
     */
    @Override
    public void post(String url, final Map<String, Object> params, final HttpCallBack callBack) {
        if (!url.startsWith("http")) {
            url = Urls.BASE_URL + url;
        }
        JPrintUtils.v("---", url);
        JLog.map(params);
        PostObjectRequest mStringRequest =new VolleyEngine().new PostObjectRequest(url, params, new ResponseListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.onFailed(error.toString());
            }
            @Override
            public void onResponse(final Object response) {
                if (response != null) {
                    JHttp.handler.post(new Runnable() {
                        @Override
                        public void run() {
                            JLog.json("---", response.toString());
                            callBack.onSuccess(JSON.parseObject(response.toString(), JHttp.analysisClassInfo(callBack)));
                        }
                    });
                } else {
                    JHttp.handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onFailed(response.toString());
                        }
                    });
                }
            }
        });
        mRequestQueue.add(mStringRequest);
    }
    private String getUrlParamsByMap(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return "";
        }
        StringBuffer params = new StringBuffer("?");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            params.append(entry.getKey());
            params.append("=");
            params.append(entry.getValue());
            params.append("&");
        }
        String str = params.toString();
        return str.substring(0, str.length() - 1);
    }

    public class PostObjectRequest extends Request<String> {
        private MultipartEntity entity = new MultipartEntity();
        private ResponseListener mListener;
        private Map<String, Object> mParams;

        public PostObjectRequest(String url, Map<String, Object> params, ResponseListener Listener) {
            super(Method.POST, url, Listener);
            mListener = Listener;
            mParams = params;
            buildMultipartEntity();
        }

        @Override
        protected Response<String> parseNetworkResponse(NetworkResponse response) {
            String parsed;
            try {
                parsed = new String(response.data,
                        HttpHeaderParser.parseCharset(response.headers));
            } catch (UnsupportedEncodingException e) {
                parsed = new String(response.data);
            }
            return Response.success(parsed,
                    HttpHeaderParser.parseCacheHeaders(response));
        }

        @Override
        protected void deliverResponse(String response) {
            mListener.onResponse(response);
        }

        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            Map<String, String> headers = super.getHeaders();

            if (headers == null || headers.equals(Collections.emptyMap())) {
                headers = new HashMap<String, String>();
            }
            return headers;
        }

        @Override
        public String getBodyContentType() {
            return entity.getContentType().getValue();
        }

        @Override
        public byte[] getBody() throws AuthFailureError {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try {
                entity.writeTo(bos);
            } catch (IOException e) {
                VolleyLog.e("IOException writing to ByteArrayOutputStream");
            }
            return bos.toByteArray();
        }

        private void buildMultipartEntity() {
            if (null != mParams && !mParams.isEmpty()) {
                for (String key : mParams.keySet()) {
                    Object value = mParams.get(key);
                    if (value instanceof File) {
                        File file = (File) value;
                        entity.addPart(key, new FileBody(file));
                    } else if (value instanceof ArrayList) {
                        ArrayList<Object> lists = (ArrayList) value;
                        for (Object obj : lists) {
                            if (obj instanceof File) {
                                File file = (File) obj;
                                entity.addPart(key, new FileBody(file));
                            }
                        }
                    } else if (value instanceof File[]) {
                        File[] file = (File[]) value;
                        for (int i = 0; i < file.length; i++) {
                            entity.addPart(key, new FileBody(file[i]));
                        }
                    } else {
                        try {
                            entity.addPart(key, new StringBody((String) value, Charset.forName("UTF-8")));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
