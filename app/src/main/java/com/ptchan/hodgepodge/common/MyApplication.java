package com.ptchan.hodgepodge.common;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by chentao on 16-6-3.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //
//        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(getApplicationContext())

        //创建默认的ImageLoader配置参数
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);

        //Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(configuration);
    }
}
