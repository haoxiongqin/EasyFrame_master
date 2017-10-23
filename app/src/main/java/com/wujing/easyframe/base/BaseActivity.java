package com.wujing.easyframe.base;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.wujing.easyframe.R;
import com.wujing.jframe.base.JActivity;
import com.wujing.jframe.utils.JPrintUtils;
import com.wujing.jframe.utils.statusbar.JStatusBar;


public abstract class BaseActivity extends JActivity {

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        JStatusBar.setColor(this, getResources().getColor(R.color.main_color), 0);
    }

    protected void gone(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.GONE);
                }
            }
        }
    }

    protected void visible(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    protected boolean isVisible(View view) {
        return view.getVisibility() == View.VISIBLE;
    }
    /** 初始化 Toolbar */
    public void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
    }
}
