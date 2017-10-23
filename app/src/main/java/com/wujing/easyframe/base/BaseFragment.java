package com.wujing.easyframe.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wujing.jframe.base.JFragment;
import com.wujing.jframe.utils.JPrintUtils;

public abstract class BaseFragment extends JFragment {
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        JPrintUtils.i(this.getClass().getName()+"---------onAttach");
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JPrintUtils.i(this.getClass().getName()+"---------onCreat");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        JPrintUtils.i(this.getClass().getName()+"---------onCreateView");
        return  super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        JPrintUtils.i(this.getClass().getName()+"---------onActivityCreated");
    }
    @Override
    public void onStart() {
        super.onStart();
        JPrintUtils.i(this.getClass().getName()+"---------onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        JPrintUtils.i(this.getClass().getName()+"---------onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        JPrintUtils.i(this.getClass().getName()+"---------onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        JPrintUtils.i(this.getClass().getName()+"---------onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        JPrintUtils.i(this.getClass().getName()+"---------onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        JPrintUtils.i(this.getClass().getName()+"---------onDestroy");
    }
}
