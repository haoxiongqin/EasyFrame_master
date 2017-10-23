package com.wujing.easyframe;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;

import com.wujing.easyframe.base.BaseActivity;
import com.wujing.easyframe.ui.CacheFragment;
import com.wujing.easyframe.ui.LimitsFragment;
import com.wujing.easyframe.ui.OnlineFragment;
import com.wujing.easyframe.ui.PrinterFragment;
import com.wujing.easyframe.ui.ToolFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    Toolbar toolbar;
    TabLayout tab;
    AppBarLayout appbar;
    ViewPager viewPager;
    TextView toolbartitle;
    FloatingActionButton fab;
    private List<Pair<String, Fragment>> items;
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tab = (TabLayout) findViewById(R.id.tab);
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        toolbartitle = (TextView) findViewById(R.id.toolbar_title);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        initToolBar(toolbar, false, "");
        items = new ArrayList<>();
        items.add(new Pair<String, Fragment>("工具", new ToolFragment()));
        items.add(new Pair<String, Fragment>("缓存", new CacheFragment()));
        items.add(new Pair<String, Fragment>("网络", new OnlineFragment()));
        items.add(new Pair<String, Fragment>("日志", new PrinterFragment()));
        items.add(new Pair<String, Fragment>("状态", new LimitsFragment()));
        viewPager.setAdapter(new MainAdapter(getSupportFragmentManager()));
        tab.setupWithViewPager(viewPager);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=viewPager.getCurrentItem();
                if(position%2==0){
                    startActivity(new Intent(MainActivity.this,WebActivity.class).putExtra("url","https://github.com/haoxiongqin")
                            .putExtra("title","我的Github,欢迎star"));
                }else{
                    startActivity(new Intent(MainActivity.this,WebActivity.class).putExtra("url","http://blog.csdn.net/qq_32365567")
                            .putExtra("title","我的CSDN博客,欢迎关注"));
                }
            }
        });
    }
    @Override
    public void initData() {

    }
    private class MainAdapter extends FragmentPagerAdapter {

        MainAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return items.get(position).second;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return items.get(position).first;
        }
    }
}
