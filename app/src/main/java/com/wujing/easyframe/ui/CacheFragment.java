package com.wujing.easyframe.ui;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.wujing.easyframe.R;
import com.wujing.easyframe.base.BaseFragment;
import com.wujing.easyframe.http.Urls;
import com.wujing.easyframe.entity.Publicity;
import com.wujing.easyframe.entity.ResultData;
import com.wujing.easyframe.ui.adapter.MainAdapter;
import com.wujing.jframe.adapter.BaseQuickAdapter;
import com.wujing.jframe.adapter.decoration.DividerItemDecoration;
import com.wujing.jframe.utils.JPrintUtils;
import com.wujing.jframe.utils.http.HttpCallBack;
import com.wujing.jframe.utils.http.JHttp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CacheFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeLayout;
    MainAdapter adapter;
    int page=1,total=-1;
    @Override
    public int getLayoutId() {
        return R.layout.home_pager_fragment;
    }

    @Override
    public void initView() {
        swipeLayout = findView(R.id.refreshLayout);
        recyclerView=findView(R.id.recyclerView);
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorSchemeColors(Color.rgb(66, 209, 219),Color.rgb(240, 30, 30));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL, 1, getResources().getColor(R.color.blue)));
        adapter =new MainAdapter(recyclerView,new ArrayList<Publicity>());
        adapter.openLoadAnimation(BaseQuickAdapter.NewsPagers);
        recyclerView.setAdapter(adapter);
        adapter.isLoadMore(true);
        //加载更多事件
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.OnLoadMoreListener() {
            @Override
            public void onRetry() {//加载失败，重新加载回调方法
                JPrintUtils.i("---onRetry");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.showLoadComplete();
                    }
                }, 200);
            }
            @Override
            public void onLoadMore() {//加载更多回调方法
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (adapter.getDataLists().size() == total) {
                            adapter.showLoadComplete();
                        } else {
                            getPublicityList();
                        }
                    }
                }, 200);
            }
        });
    }
    @Override
    public void initData() {
    }
    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                page=1;
                getPublicityList();
            }
        }, 1000);
    }
    private void getPublicityList(){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("page",page+"");
        map.put("pageSize","10");
        map.put("status","1");
        JHttp.obtain().post(Urls.BASE_URL + "app_publicity_list.shtml", map, new HttpCallBack<ResultData>() {
            @Override
            public void onSuccess(ResultData data) {
                if (data.getCode()==0) {
                    total = data.getTotal();
                    List<Publicity> l = JSONObject.parseArray(data.getData(), Publicity.class);
                    if (l != null & l.size() > 0) {
                        if (page == 1) {
                            swipeLayout.setRefreshing(false);
                            adapter.setDataLists(l);
                        }else {
                            adapter.addAll(l);
                        }
                    } else
                        adapter.showLoadComplete();

                }
                page++;
            }
            @Override
            public void onFailed(String error) {

            }
        });
    }
}
