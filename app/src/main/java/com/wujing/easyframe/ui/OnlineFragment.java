package com.wujing.easyframe.ui;

import android.support.v7.widget.RecyclerView;

import com.wujing.easyframe.R;
import com.wujing.easyframe.base.BaseFragment;


public class OnlineFragment extends BaseFragment {
    RecyclerView recyclerView;
    @Override
    public int getLayoutId() {
        return R.layout.all_recycleview;
    }

    @Override
    public void initView() {
        recyclerView=findView(R.id.recyclerView);
    }

    @Override
    public void initData() {
    }
}
