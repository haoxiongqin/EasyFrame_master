package com.wujing.easyframe.ui;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wujing.easyframe.R;
import com.wujing.easyframe.WebActivity;
import com.wujing.easyframe.base.BaseFragment;
import com.wujing.easyframe.entity.Publicity;
import com.wujing.easyframe.ui.adapter.MainAdapter;
import com.wujing.jframe.adapter.BaseQuickAdapter;
import com.wujing.jframe.adapter.decoration.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;


public class PrinterFragment extends BaseFragment {
    RecyclerView recyclerView;
    MainAdapter adapter;
    List<Publicity> lists;
    @Override
    public int getLayoutId() {
        return R.layout.all_recycleview;
    }

    @Override
    public void initView() {
        setData();
        recyclerView=findView(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL, 1, getResources().getColor(R.color.colorAccent)));
        adapter =new MainAdapter(recyclerView,lists);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                if (position == 0) {
                    startActivity(new Intent(getActivity(), WebActivity.class).putExtra("url", "https://github.com/haoxiongqin/EasyFrame_master/blob/master/jframe/src/main/java/com/wujing/jframe/utils/log/JLog.java")
                            .putExtra("title", "JLog"));
                } else if (position == 1) {
                    startActivity(new Intent(getActivity(), WebActivity.class).putExtra("url", "https://github.com/haoxiongqin/EasyFrame_master/blob/master/jframe/src/main/java/com/wujing/jframe/utils/log/JLogConfig.java")
                            .putExtra("title", "JLogConfig"));
                } else if (position == 2) {
                    startActivity(new Intent(getActivity(), WebActivity.class).putExtra("url", "https://github.com/haoxiongqin/EasyFrame_master/blob/master/jframe/src/main/java/com/wujing/jframe/utils/log/LoggerPrinter.java")
                            .putExtra("title", "LoggerPrinter"));
                }else if (position == 3) {
                    startActivity(new Intent(getActivity(), WebActivity.class).putExtra("url", "https://github.com/haoxiongqin/EasyFrame_master/blob/master/jframe/src/main/java/com/wujing/jframe/utils/log/Printer.java")
                            .putExtra("title", "Printer"));
                }
            }
        });
    }

    @Override
    public void initData() {

    }
    public void setData(){
        lists=new ArrayList<>();
        lists.add(new Publicity("JLog"));
        lists.add(new Publicity("JLogConfig"));
        lists.add(new Publicity("LoggerPrinter"));
        lists.add(new Publicity("Printer"));
    }
}
