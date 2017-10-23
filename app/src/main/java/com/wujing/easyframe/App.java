package com.wujing.easyframe;

import android.content.Context;

import com.wujing.easyframe.http.VolleyEngine;
import com.wujing.easyframe.utils.GlideImageLoader;
import com.wujing.jframe.JFrame;
import com.wujing.jframe.base.JApplication;


public class App extends JApplication {
    private static App instance;
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        context=this;
        JFrame.tag="---";
        JFrame.initJLog().setShowThreadInfo(false);
        JFrame.initJHttp(new VolleyEngine());
        JFrame.initJImageLoader(new GlideImageLoader(getApplicationContext()));
    }
    public static App getInstance() {
        return instance;
    }

    public static Context getAppContext() {
        return context;
    }
}
