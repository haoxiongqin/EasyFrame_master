package com.wujing.easyframe.ui.adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.wujing.easyframe.R;
import com.wujing.easyframe.entity.Publicity;
import com.wujing.jframe.adapter.BaseQuickAdapter;
import com.wujing.jframe.adapter.JViewHolder;

import java.util.List;


public class MainAdapter  extends BaseQuickAdapter<Publicity> {
    public MainAdapter(@NonNull RecyclerView mRecyclerView, List<Publicity> dataLists) {
        super(mRecyclerView, dataLists, R.layout.item_main_list);
    }

    @Override
    protected void bindData(JViewHolder holder, Publicity data, int position) {
        holder.setText(R.id.title,data.getTitle());
    }
}
