package com.ptchan.hodgepodge.photoWallFalls;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.LruCache;

/**
 * Created by chentao on 16-5-29.
 */
public class ImageLoader {

    /**
     * 图片缓存核心类 ，用于缓存已下载好 的图片，在程序内存达到定值时会将最近最少使用的图片移除
     */
    private static LruCache<String,Bitmap> mMemoryCache;

    /**
     *ImageLoader的实例
     */
    private static ImageLoader mImageLoader;

    private ImageLoader() {
        int maxMemory = (int)Runtime.getRuntime().maxMemory();//程序最大可用内存
        int cacheSize = maxMemory/8;//设置图片缓存大小为其1/8
        mMemoryCache = new LruCache<String,Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount();
            }
        };
    }

    /**
     * 获取ImageLoader 的实例
     * 单例模式
     */
    public static ImageLoader getInstance(){
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader();
        }
        return mImageLoader;
    }

    /**
     * 将图片存储到LruCache中
     * @param key
     * @param bitmap
     */
    public void addBitmapToMemoryCache(String key,Bitmap bitmap) {
        if (mMemoryCache.get(key)==null) {
            mMemoryCache.put(key,bitmap);
        }
    }

    /**
     * 从LruCache中取出一张图片 不存在返回null
     * @param key
     * @return
     */
    public Bitmap getBitmapFromMemoryCache(String key) {
        return mMemoryCache.get(key);
    }

    /**
     * 计算需要缩小的比例
     * @param options
     * @param reqWidth
     * @return
     */
    public static int caculateInSampleSize(BitmapFactory.Options options,int reqWidth) {
        //原图片宽度
        int width = options.outWidth;
        int inSampleSize = 1;
        if (width > reqWidth) {
            int widthRatio = (int) (Math.floor(width)/Math.floor(reqWidth));
            inSampleSize = widthRatio;
        }
        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(String pathName,int reqWidth){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//第一次解析将inJustDecodeBounds设为true，获取图片大小
        BitmapFactory.decodeFile(pathName,options);
        options.inSampleSize = caculateInSampleSize(options,reqWidth);//得到 压缩比例
        options.inJustDecodeBounds = false;//在设为false 再次解析图片
        return BitmapFactory.decodeFile(pathName,options);

    }
}
