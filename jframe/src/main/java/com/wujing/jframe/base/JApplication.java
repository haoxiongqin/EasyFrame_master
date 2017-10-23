package com.wujing.jframe.base;

import android.app.Application;

import com.wujing.jframe.JFrame;


public class JApplication extends Application{
    private static JApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        JFrame.init(this);
    }
    public static JApplication getInstance() {
        return instance;
    }
}
