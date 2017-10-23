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


public class OnlineFragment extends BaseFragment {
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
                    startActivity(new Intent(getActivity(), WebActivity.class).putExtra("url", "https://github.com/haoxiongqin/EasyFrame_master/blob/master/app/src/main/java/com/wujing/easyframe/http/AsyncHttpEngine.java")
                            .putExtra("title", "AsyncHttpEngine"));
                } else if (position == 1) {
                    startActivity(new Intent(getActivity(), WebActivity.class).putExtra("url", "https://github.com/haoxiongqin/EasyFrame_master/blob/master/app/src/main/java/com/wujing/easyframe/http/OkHttpEngine.java")
                            .putExtra("title", "OkHttpEngine"));
                } else if (position == 2) {
                    startActivity(new Intent(getActivity(), WebActivity.class).putExtra("url", "https://github.com/haoxiongqin/EasyFrame_master/blob/master/app/src/main/java/com/wujing/easyframe/http/XutilsEngine.java")
                            .putExtra("title", "XutilsEngine"));
                }else if (position == 3) {
                    startActivity(new Intent(getActivity(), WebActivity.class).putExtra("url", "https://github.com/haoxiongqin/EasyFrame_master/blob/master/app/src/main/java/com/wujing/easyframe/http/VolleyEngine.java")
                            .putExtra("title", "VolleyEngine"));
                }else if (position == 4) {
                    startActivity(new Intent(getActivity(), OnlineTestActivity.class));
                }
            }
        });
    }

    @Override
    public void initData() {
    }
    void setData(){
        lists=new ArrayList<>();
        lists.add(new Publicity("AsyncHttpEngine"));
        lists.add(new Publicity("OkHttpEngine"));
        lists.add(new Publicity("XutilsEngine"));
        lists.add(new Publicity("VolleyEngine"));
        lists.add(new Publicity("测试例子"));
    }
}
