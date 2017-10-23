package com.wujing.easyframe.ui;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.wujing.easyframe.R;
import com.wujing.easyframe.base.BaseFragment;
import com.wujing.easyframe.entity.ResultData;
import com.wujing.easyframe.utils.GlideImage;
import com.wujing.jframe.JFrame;
import com.wujing.jframe.utils.http.HttpCallBack;
import com.wujing.jframe.utils.http.JHttp;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ToolFragment extends BaseFragment {
    private final int IMAGE_PICKER = 1223;
    Button button;
    Button buttons;

    @Override
    public int getLayoutId() {
        return R.layout.tool_fragment;
    }

    @Override
    public void initView() {
        button = findView(R.id.button);
        buttons = findView(R.id.buttons);
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImage());
        imagePicker.setShowCamera(true);
        imagePicker.setCrop(true);
        imagePicker.setSaveRectangle(true);
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);
        imagePicker.setFocusWidth(JFrame.screenWidth / 2);
        imagePicker.setFocusHeight(JFrame.screenHeight / 2);
        imagePicker.setOutPutX(300);
        imagePicker.setOutPutY(300);
        imagePicker.setSelectLimit(1);
        buttons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ImageGridActivity.class);
                startActivityForResult(intent, IMAGE_PICKER);
            }
        });
    }

    @Override
    public void initData() {

    }

//    private void upload(String path) {
//        File file = new File(path);
//        Map<String, Object> params = new HashMap();
//        params.put("itemId", "4028818d5f2e4868015f3832a5a90198");
//        params.put("uploadUser", "2c937a4c5beb19e4015beb21ae76000f");
//        params.put("path", "行政检查记录签字确认");
//        params.put("attachExt", "ATTACH_CFM_SIGN_SURE");
//        params.put("files", file);
//        JHttp.obtain().post("http://60.160.30.50:9999/AttachService/app_attach_singleUpload.shtml", params, new HttpCallBack<ResultData>() {
//            @Override
//            public void onSuccess(ResultData resultData) {
//                if (resultData.getCode() == 0) {
//                    Log.e("成功", "成功");
//                }
//            }
//            @Override
//            public void onFailed(String error) {
//                Log.e("失败", error.toString());
//            }
//        });
//    }

        private void upload(String path) {
        Map<String, Object> params = new HashMap<>();
        params.put("itemId", "2c937a4c5beb19e4015beb21ae76000f");
        params.put("attachExt", "ATTACH_CFM_AVATAR");
        params.put("uploadUser", "2c937a4c5beb19e4015beb21ae76000f");
        params.put("path", "基础路径/用户信息/用户头像");
        File file=new File(path);
        File[] files=new File[]{file};
        params.put("files", files);
        params.put("fileName", file.getName());
        JHttp.obtain().post("http://60.160.30.50:9999/AttachService/app_attach_singleUpload.shtml", params, new HttpCallBack<ResultData>() {
            @Override
            public void onSuccess(ResultData resultData) {
                if(resultData.getCode()==0){
                    Log.e("成功","成功");
                }
            }
            @Override
            public void onFailed(String error) {
                Log.e("失败","失败");
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
                if (requestCode == IMAGE_PICKER) {
                    ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                    if (images != null && images.size() > 0) {
                        for (ImageItem item : images) {
                            upload(item.path);
                        }
                    }
                }
            }
        }
    }
}
