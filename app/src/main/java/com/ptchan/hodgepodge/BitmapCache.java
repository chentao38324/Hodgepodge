package com.ptchan.hodgepodge;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by chentao on 16-5-28.
 */
public class BitmapCache implements ImageLoader.ImageCache{


    private LruCache<String,Bitmap> mCache;

    public BitmapCache() {
        int maxCache = (int)(Runtime.getRuntime().maxMemory()/1024);
        int cacheSize = maxCache/8;
        mCache = new LruCache<String,Bitmap>(cacheSize){

            @Override
            protected int sizeOf(String key, Bitmap value) {
                return super.sizeOf(key, value);
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {
        return null;
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {

    }
}
