package com.wujing.jframe.widget.loadingview;


import android.support.annotation.LayoutRes;

import com.wujing.jframe.R;


public class JLoadingViewConfig {

    private int emptyViewResId = R.layout.jloading_empty_view;
    private int errorViewResId = R.layout.jloading_error_view;
    private int loadingViewResId = R.layout.jloading_loading_view;
    private int noNetworkViewResId = R.layout.jloading_no_network_view;

    public int getEmptyViewResId() {
        return emptyViewResId;
    }

    public JLoadingViewConfig setEmptyViewResId(@LayoutRes int emptyViewResId) {
        this.emptyViewResId = emptyViewResId;
        return this;
    }

    public int getErrorViewResId() {
        return errorViewResId;
    }

    public JLoadingViewConfig setErrorViewResId(@LayoutRes int errorViewResId) {
        this.errorViewResId = errorViewResId;
        return this;
    }

    public int getLoadingViewResId() {
        return loadingViewResId;
    }

    public JLoadingViewConfig setLoadingViewResId(@LayoutRes int loadingViewResId) {
        this.loadingViewResId = loadingViewResId;
        return this;
    }

    public int getNoNetworkViewResId() {
        return noNetworkViewResId;
    }

    public JLoadingViewConfig setNoNetworkViewResId(@LayoutRes int noNetworkViewResId) {
        this.noNetworkViewResId = noNetworkViewResId;
        return this;
    }
}
