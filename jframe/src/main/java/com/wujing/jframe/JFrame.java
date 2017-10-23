package com.wujing.jframe;


import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.util.DisplayMetrics;

import com.wujing.jframe.utils.JDensityUtils;
import com.wujing.jframe.utils.JOutMethodUtils;
import com.wujing.jframe.utils.http.IHttpEngine;
import com.wujing.jframe.utils.http.JHttp;
import com.wujing.jframe.utils.imageload.ImageLoader;
import com.wujing.jframe.utils.imageload.JImage;
import com.wujing.jframe.utils.log.JLog;
import com.wujing.jframe.utils.log.JLogConfig;
import com.wujing.jframe.widget.loadingview.JLoadingView;
import com.wujing.jframe.widget.loadingview.JLoadingViewConfig;

public class JFrame  {
    private static Context context;
    public static int screenHeight;
    public static int screenWidth;

    // #log
    public static String tag = "JFrame";
    public static boolean isDebug = true;


    public JFrame() {
    }

    public static void init(Context context) {
        JFrame.context = context;
        screenHeight = JDensityUtils.getScreenHeight();
        screenWidth = JDensityUtils.getScreenWidth();
    }
    public static JLogConfig initJLog() {
        return JLog.init();
    }

    public static JLoadingViewConfig initJLoadingView() {
        return JLoadingView.init();
    }

    public static void initJHttp(IHttpEngine httpEngine) {
        JHttp.init(httpEngine);
    }

    public static void initJImageLoader(ImageLoader loader) {
        JImage.init(loader);
    }

    public static Context getContext() {
        synchronized (JFrame.class) {
            if (JFrame.context == null)
                throw new NullPointerException("在application中没有实例化JFrame.init(context)");
            return JFrame.context.getApplicationContext();
        }
    }

    public static Resources getResources() {
        return JFrame.getContext().getResources();
    }

    public static String getString(@StringRes int id) {
        return getResources().getString(id);
    }

    public static Resources.Theme getTheme() {
        return JFrame.getContext().getTheme();
    }

    public static AssetManager getAssets() {
        return JFrame.getContext().getAssets();
    }

    public static Drawable getDrawable( @DrawableRes int id) {
        return JOutMethodUtils.getDrawable(id);
    }

    public static int getColor( @ColorRes int id) {
        return JOutMethodUtils.getColor(id);
    }

    public static Object getSystemService(String name){
        return JFrame.getContext().getSystemService(name);
    }

    public static Configuration getConfiguration() {
        return JFrame.getResources().getConfiguration();
    }

    public static DisplayMetrics getDisplayMetrics() {
        return JFrame.getResources().getDisplayMetrics();
    }
}
