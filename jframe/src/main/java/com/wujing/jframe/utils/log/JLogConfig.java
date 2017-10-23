package com.wujing.jframe.utils.log;


import android.text.TextUtils;

import com.wujing.jframe.JFrame;

public class JLogConfig {
    private boolean showThreadInfo = true;
    private boolean debug = JFrame.isDebug;
    private String tag = JFrame.tag;


    public JLogConfig setTag(String tag) {
        if (!TextUtils.isEmpty(tag)) {
            this.tag = tag;
        }
        return this;
    }

    public JLogConfig setShowThreadInfo(boolean showThreadInfo) {
        this.showThreadInfo = showThreadInfo;
        return this;
    }


    public JLogConfig setDebug(boolean debug) {
        this.debug = debug;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public boolean isDebug() {
        return debug;
    }

    public boolean isShowThreadInfo() {
        return showThreadInfo;
    }
}
