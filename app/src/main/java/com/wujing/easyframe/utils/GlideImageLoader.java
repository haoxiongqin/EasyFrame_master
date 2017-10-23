package com.wujing.easyframe.utils;

import android.content.Context;
import android.widget.ImageView;

import com.wujing.jframe.utils.imageload.ImageLoader;


public class GlideImageLoader implements ImageLoader {

    private Context mContext;

    public GlideImageLoader(Context context) {
        this.mContext=context;
    }

    @Override
    public void load(ImageView imageView, Object imageUrl) {
    }
    @Override
    public void load(ImageView imageView, Object imageUrl, int defaultImage) {
    }

    @Override
    public void load(ImageView imageView, Object imageUrl, Object transformation) {

    }

    public void loadCircleImg(ImageView imageView, Object imageUrl, int defaultImage){
    }
}
