package com.wujing.jframe.utils.imageload;


import android.widget.ImageView;

public class JImage implements ImageLoader {

    private static ImageLoader imageLoader;
    private static JImage xImage;

    public static void init(ImageLoader loader) {
        imageLoader = loader;
    }

    public static JImage getInstance() {
        if (imageLoader==null){
            throw new NullPointerException("在Application中没有实例化initJImageLoader()");
        }
        if (xImage == null) {
            xImage = new JImage();
        }
        return xImage;
    }

    @Override
    public void load(ImageView imageView, Object imageUrl) {
        imageLoader.load(imageView, imageUrl);
    }

    @Override
    public void load(ImageView imageView, Object imageUrl, int defaultImage) {
        imageLoader.load(imageView, imageUrl, defaultImage);
    }

    @Override
    public void load(ImageView imageView, Object imageUrl, Object transformation) {
        imageLoader.load(imageView, imageUrl, transformation);
    }
}
