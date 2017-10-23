package com.wujing.easyframe.utils;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lzy.imagepicker.loader.ImageLoader;

/**
 * Created  on 2017/10/20.
 * author:秦浩雄
 * 作用:
 */

public class GlideImage implements ImageLoader {
    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        Glide.with(activity)
                .load(path)
                .centerCrop()
                .crossFade()
                .into(imageView);
    }

    @Override
    public void clearMemoryCache() {

    }
}
