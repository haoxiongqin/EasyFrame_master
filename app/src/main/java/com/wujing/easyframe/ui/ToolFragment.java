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


public class ToolFragment extends BaseFragment {
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
                if(position==0){
                    startActivity(new Intent(getActivity(),WebActivity.class).putExtra("url","https://github.com/haoxiongqin/EasyFrame_master/blob/master/jframe/src/main/java/com/wujing/jframe/utils/JAppUtils.java")
                            .putExtra("title","JAppUtils"));
                }else if(position==1){
                    startActivity(new Intent(getActivity(),WebActivity.class).putExtra("url","https://github.com/haoxiongqin/EasyFrame_master/blob/master/jframe/src/main/java/com/wujing/jframe/utils/JBitmapUtils.java")
                            .putExtra("title","JBitmapUtils"));
                }else if(position==2){
                    startActivity(new Intent(getActivity(),WebActivity.class).putExtra("url","https://github.com/haoxiongqin/EasyFrame_master/blob/master/jframe/src/main/java/com/wujing/jframe/utils/JCrashHandlerUtils.java")
                            .putExtra("title","JCrashHandlerUtils"));
                }else if(position==3){
                    startActivity(new Intent(getActivity(),WebActivity.class).putExtra("url","https://github.com/haoxiongqin/EasyFrame_master/blob/master/jframe/src/main/java/com/wujing/jframe/utils/JDateUtils.java")
                            .putExtra("title","JDateUtils"));
                }else if(position==4){
                    startActivity(new Intent(getActivity(),WebActivity.class).putExtra("url","https://github.com/haoxiongqin/EasyFrame_master/blob/master/jframe/src/main/java/com/wujing/jframe/utils/JDensityUtils.java")
                            .putExtra("title","JDensityUtils"));
                }else if(position==5){
                    startActivity(new Intent(getActivity(),WebActivity.class).putExtra("url","https://github.com/haoxiongqin/EasyFrame_master/blob/master/jframe/src/main/java/com/wujing/jframe/utils/JEmptyUtils.java")
                            .putExtra("title","JEmptyUtils"));
                }else if(position==6){
                    startActivity(new Intent(getActivity(),WebActivity.class).putExtra("url","https://github.com/haoxiongqin/EasyFrame_master/blob/master/jframe/src/main/java/com/wujing/jframe/utils/JEncryptUtils.java")
                            .putExtra("title","JEncryptUtils"));
                }else if(position==7){
                    startActivity(new Intent(getActivity(),WebActivity.class).putExtra("url","https://github.com/haoxiongqin/EasyFrame_master/blob/master/jframe/src/main/java/com/wujing/jframe/utils/JFastBlurUtils.java")
                            .putExtra("title","JFastBlurUtils"));
                }else if(position==8){
                    startActivity(new Intent(getActivity(),WebActivity.class).putExtra("url","https://github.com/haoxiongqin/EasyFrame_master/blob/master/jframe/src/main/java/com/wujing/jframe/utils/JFormatTimeUtils.java")
                            .putExtra("title","JFormatTimeUtils"));
                }else if(position==9){
                    startActivity(new Intent(getActivity(),WebActivity.class).putExtra("url","https://github.com/haoxiongqin/EasyFrame_master/blob/master/jframe/src/main/java/com/wujing/jframe/utils/JHandler.java")
                            .putExtra("title","JHandler"));
                }else if(position==10){
                    startActivity(new Intent(getActivity(),WebActivity.class).putExtra("url","https://github.com/haoxiongqin/EasyFrame_master/blob/master/jframe/src/main/java/com/wujing/jframe/utils/JKeyboardUtils.java")
                            .putExtra("title","JKeyboardUtils"));
                }else if(position==11){
                    startActivity(new Intent(getActivity(),WebActivity.class).putExtra("url","https://github.com/haoxiongqin/EasyFrame_master/blob/master/jframe/src/main/java/com/wujing/jframe/utils/JNetworkUtils.java")
                            .putExtra("title","JNetworkUtils"));
                }else if(position==12){
                    startActivity(new Intent(getActivity(),WebActivity.class).putExtra("url","https://github.com/haoxiongqin/EasyFrame_master/blob/master/jframe/src/main/java/com/wujing/jframe/utils/JOutMethodUtils.java")
                            .putExtra("title","JOutMethodUtils"));
                }else if(position==13){
                    startActivity(new Intent(getActivity(),WebActivity.class).putExtra("url","https://github.com/haoxiongqin/EasyFrame_master/blob/master/jframe/src/main/java/com/wujing/jframe/utils/JPermission.java")
                            .putExtra("title","JPermission"));
                }else if(position==14){
                    startActivity(new Intent(getActivity(),WebActivity.class).putExtra("url","https://github.com/haoxiongqin/EasyFrame_master/blob/master/jframe/src/main/java/com/wujing/jframe/utils/JPreferencesUtils.java")
                            .putExtra("title","JPreferencesUtils"));
                }else if(position==15){
                    startActivity(new Intent(getActivity(),WebActivity.class).putExtra("url","https://github.com/haoxiongqin/EasyFrame_master/blob/master/jframe/src/main/java/com/wujing/jframe/utils/JPrintUtils.java")
                            .putExtra("title","JPrintUtils"));
                }else if(position==16){
                    startActivity(new Intent(getActivity(),WebActivity.class).putExtra("url","https://github.com/haoxiongqin/EasyFrame_master/blob/master/jframe/src/main/java/com/wujing/jframe/utils/JRegexUtils.java")
                            .putExtra("title","JRegexUtils"));
                }else if(position==17){
                    startActivity(new Intent(getActivity(),WebActivity.class).putExtra("url","https://github.com/haoxiongqin/EasyFrame_master/blob/master/jframe/src/main/java/com/wujing/jframe/utils/JSpannableStringUtils.java")
                            .putExtra("title","JSpannableStringUtils"));
                }else if(position==18){
                    startActivity(new Intent(getActivity(),WebActivity.class).putExtra("url","https://github.com/haoxiongqin/EasyFrame_master/blob/master/jframe/src/main/java/com/wujing/jframe/utils/JStringUtils.java")
                            .putExtra("title","JStringUtils"));
                }else if(position==19){
                    startActivity(new Intent(getActivity(),WebActivity.class).putExtra("url","https://github.com/haoxiongqin/EasyFrame_master/blob/master/jframe/src/main/java/com/wujing/jframe/utils/JViewHolderUtils.java")
                            .putExtra("title","JViewHolderUtils"));
                }else if(position==20){
                    startActivity(new Intent(getActivity(),WebActivity.class).putExtra("url","https://github.com/haoxiongqin/EasyFrame_master/blob/master/jframe/src/main/java/com/wujing/jframe/widget/JStateButton.java")
                            .putExtra("title","JStateButton"));
                }else if(position==21){
                    startActivity(new Intent(getActivity(),WebActivity.class).putExtra("url","https://github.com/haoxiongqin/EasyFrame_master/blob/master/jframe/src/main/java/com/wujing/jframe/widget/JStateImageView.java")
                            .putExtra("title","JStateImageView"));
                }else if(position==22){
                    startActivity(new Intent(getActivity(),WebActivity.class).putExtra("url","https://github.com/haoxiongqin/EasyFrame_master/blob/master/jframe/src/main/java/com/wujing/jframe/widget/JDrawableTextView.java")
                            .putExtra("title","JDrawableTextView"));
                }
            }
        });
    }
    @Override
    public void initData() {

    }
    public void setData(){
        lists=new ArrayList<>();
        lists.add(new Publicity("JAppUtils"));
        lists.add(new Publicity("JBitmapUtils"));
        lists.add(new Publicity("JCrashHandlerUtils"));
        lists.add(new Publicity("JDateUtils"));
        lists.add(new Publicity("JDensityUtils"));
        lists.add(new Publicity("JEmptyUtils"));
        lists.add(new Publicity("JEncryptUtils"));
        lists.add(new Publicity("JFastBlurUtils"));
        lists.add(new Publicity("JFormatTimeUtils"));
        lists.add(new Publicity("JHandler"));
        lists.add(new Publicity("JKeyboardUtils"));
        lists.add(new Publicity("JNetworkUtils"));
        lists.add(new Publicity("JOutMethodUtils"));
        lists.add(new Publicity("JPermission"));
        lists.add(new Publicity("JPreferencesUtils"));
        lists.add(new Publicity("JPrintUtils"));
        lists.add(new Publicity("JRegexUtils"));
        lists.add(new Publicity("JSpannableStringUtils"));
        lists.add(new Publicity("JStringUtils"));
        lists.add(new Publicity("JViewHolderUtils"));
        lists.add(new Publicity("JStateButton"));
        lists.add(new Publicity("JStateImageView"));
        lists.add(new Publicity("JDrawableTextView"));
    }
}
