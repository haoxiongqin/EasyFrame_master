package com.wujing.easyframe.http;

import android.app.Application;

import com.alibaba.fastjson.JSON;
import com.wujing.easyframe.App;
import com.wujing.jframe.utils.JEmptyUtils;
import com.wujing.jframe.utils.http.HttpCallBack;
import com.wujing.jframe.utils.http.IHttpEngine;
import com.wujing.jframe.utils.http.JHttp;
import com.wujing.jframe.utils.log.JLog;

import org.xutils.common.Callback;
import org.xutils.ex.HttpException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

/**
 * ViewUtils模块、HttpUtils模块、BitmapUtils模块 、 DbUtils模块
 * xUtils包含了很多实用的android工具；
 * xUtils支持超大文件(超过2G)上传，更全面的http请求协议支持(11种谓词)，拥有更加灵活的ORM，更多的事件注解支持且不受混淆影响；
 * xUtils 最低兼容Android 4.0 (api level 14)；
 * HTTP实现替换HttpClient为UrlConnection, 自动解析回调泛型, 更安全的断点续传策略；
 * 支持标准的Cookie策略, 区分domain, path；
 * 事件注解去除不常用的功能, 提高性能；
 * 数据库api简化提高性能, 达到和greenDao一致的性能；
 * 图片绑定支持gif(受系统兼容性影响, 部分gif文件只能静态显示), webp; 支持圆角, 圆形, 方形等裁剪, 支持自动旋转。
 * <p>
 * <p>
 * 此处只用HttpUtils模块
 */
public class XutilsEngine implements IHttpEngine {

    public XutilsEngine() {
        x.Ext.init((Application) App.getAppContext());
        x.Ext.setDebug(false); //输出debug日志，开启会影响性能
    }

    @Override
    public void get(String url, Map<String, Object> params, final HttpCallBack callBack) {
        RequestParams param = new RequestParams(url + getUrlParamsByMap(params));
        x.http().get(param, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(final String result) {
                if (!JEmptyUtils.isEmpty(result)) {
                    JHttp.handler.post(new Runnable() {
                        @Override
                        public void run() {
                            JLog.json("---", result);
                            callBack.onSuccess(JSON.parseObject(result, JHttp.analysisClassInfo(callBack)));
                        }
                    });
                } else {
                    JHttp.handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onFailed(result.toString());
                        }
                    });
                }
            }

            //请求异常后的回调方法
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                callBack.onFailed(ex.toString());
            }

            //主动调用取消请求的回调方法
            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }
    //带缓存的get请求
    @Override
    public void otherGet(String url, Map<String, Object> params,final HttpCallBack callBack) {
        RequestParams param = new RequestParams(url + getUrlParamsByMap(params));
        // 默认缓存存活时间, 单位:毫秒（如果服务器没有返回有效的max-age或Expires则参考）
        param.setCacheMaxAge(1000 * 60);
        x.http().get(param, new Callback.CacheCallback<String>() {
            private boolean hasError = false;
            private String result = null;
            @Override
            public boolean onCache(String result) { //得到缓存数据, 缓存过期后不会进入
                this.result = result;
                return true; //true: 信任缓存数据, 不再发起网络请求; false不信任缓存数据
            }

            @Override
            public void onSuccess(String result) {
                //如果服务返回304或onCache选择了信任缓存,这时result为null
                if (result != null) {
                    this.result = result;
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                hasError = true;
                if (ex instanceof HttpException) { //网络错误
                    HttpException httpEx = (HttpException) ex;
                    int responseCode = httpEx.getCode();
                    String responseMsg = httpEx.getMessage();
                    String errorResult = httpEx.getResult();
                    //...
                    callBack.onFailed(responseMsg);
                } else { //其他错误
                    callBack.onFailed(ex.toString());
                }
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
                if (!hasError && result != null) {
                    if (!JEmptyUtils.isEmpty(result)) {
                        JHttp.handler.post(new Runnable() {
                            @Override
                            public void run() {
                                JLog.json("---", result);
                                callBack.onSuccess(JSON.parseObject(result, JHttp.analysisClassInfo(callBack)));
                            }
                        });
                    } else {
                        JHttp.handler.post(new Runnable() {
                            @Override
                            public void run() {
                                callBack.onFailed(result.toString());
                            }
                        });
                    }
                }
            }
        });
    }

    @Override
    public void post(String url, Map<String, Object> params, final HttpCallBack callBack) {
        RequestParams param = new RequestParams(url);
        param.setMultipart(true); //表单形式
        if (null != params && !params.isEmpty()) {
            for (String key : params.keySet()) {
                Object value = params.get(key);
                if (value instanceof File) {
                    File file = (File) value;
                    param.addBodyParameter(key, file);
                } else if (value instanceof ArrayList) {
                    ArrayList<Object> lists = (ArrayList) value;
                    for (Object obj : lists) {
                        if (obj instanceof File) {
                            File file = (File) obj;
                            param.addBodyParameter(key, file);
                        }
                    }
                } else if (value instanceof File[]) {
                    File[] file = (File[]) value;
                    for (int i = 0; i < file.length; i++) {
                        param.addBodyParameter(key, file[i]);
                    }
                } else {
                    param.addBodyParameter(key, (String) value);
                }
            }
        }
        x.http().post(param, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(final String result) {
                if (!JEmptyUtils.isEmpty(result)) {
                    JHttp.handler.post(new Runnable() {
                        @Override
                        public void run() {
                            JLog.json("---", result);
                            callBack.onSuccess(JSON.parseObject(result, JHttp.analysisClassInfo(callBack)));
                        }
                    });
                } else {
                    JHttp.handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onFailed(result.toString());
                        }
                    });
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                callBack.onFailed(ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    @Override
    public void otherPost(String url, Map<String, Object> params, HttpCallBack callBack) {

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
}
