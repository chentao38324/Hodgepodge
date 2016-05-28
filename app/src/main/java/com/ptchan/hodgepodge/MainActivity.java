package com.ptchan.hodgepodge;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class MainActivity extends Activity {

    private BitmapCache bitmapCache;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

    }

    private void init() {
        setContentView(R.layout.activity_main);
        Log.d("TT","teeest");
        bitmapCache = new BitmapCache();
        imageView = (ImageView) findViewById(R.id.iv_pic);
        //用ImageLoader加载图片
        loadWebPicUseImageLoader();
    }

    private void loadWebPicUseImageLoader() {
        RequestQueue queue = Volley.newRequestQueue(this);
        ImageLoader imageLoader = new ImageLoader(queue,bitmapCache);
        ImageLoader.ImageListener listener = ImageLoader
                .getImageListener(imageView,R.drawable.loading,R.drawable.load_fail);
        imageLoader.get("https://drscdn.500px.org/photo/134088563/m%3D2048/31fd572e90f5ea4d9cd84055c9baf58f",
                listener);
    }
}

