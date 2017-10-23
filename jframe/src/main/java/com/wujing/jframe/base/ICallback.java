package com.wujing.jframe.base;

public interface ICallback {
    //返回布局文件id
    int getLayoutId();
    //初始化布局文件
    void initView();
    //初始化数据
    void initData();
}
