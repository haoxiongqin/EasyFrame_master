package com.wujing.jframe.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.wujing.jframe.utils.JPermission;
import com.wujing.jframe.utils.JPrintUtils;


public  abstract class JActivity extends AppCompatActivity implements ICallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JPrintUtils.i(this.getClass().getName() + "---------onCreat");
        JActivityStack.getInstance().addActivity(this);
        if (getLayoutId()>0) {
            setContentView(getLayoutId());
        }
        initData();
        initView();
    }

    /**
     * Android M 全局权限申请回调
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
            grantResults) {
        JPermission.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onDestroy() {
        JPrintUtils.i(this.getClass().getName() + "---------onDestroy");
        super.onDestroy();
        JActivityStack.getInstance().finishActivity();
    }
    @Override
    protected void onStart() {
        super.onStart();
        JPrintUtils.i(this.getClass().getName() + "---------onStart");
    }

    protected void onResume() {
        super.onResume();
        JPrintUtils.i(this.getClass().getName() + "---------onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPrintUtils.i(this.getClass().getName() + "---------onPause");
    }
    @Override
    protected void onStop() {
        super.onStop();
        JPrintUtils.i(this.getClass().getName() + "---------onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        JPrintUtils.i(this.getClass().getName() + "---------onRestart");
    }
}
