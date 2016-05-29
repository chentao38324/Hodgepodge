package com.ptchan.hodgepodge.photoWall;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.ptchan.hodgepodge.Images;
import com.ptchan.hodgepodge.R;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by chentao on 16-5-29.
 */
public class GVAdapter extends ArrayAdapter<String> implements AbsListView.OnScrollListener {

    private GridView mGvPic;//GriView 的实例
    private Set<BitmapWorkerTask> taskCollection;//记录正在下载或等待下载的任务
    private LruCache<String,Bitmap> mMemoryCache;//图片缓存的核心类，缓存下载好的图片，在程序内存达到预定值时将最少使用的图片移除
    private int mFirstVisibleItem;//第一张可见图片的下标
    private int mVisibleItemCount;//一屏可见图片数量
    private boolean isFirstEnter = true;//记录是否第一次打开应用，解决第一次打开应用 不滑动不加载的问题

    public GVAdapter(Context context, int tvResId, String[] objs, GridView gvPic){
        super(context,tvResId,objs);
        mGvPic = gvPic;
        taskCollection = new HashSet<BitmapWorkerTask>();
        //获取程序最大可用内存,设置可用缓存为其1/8
        int maxMemory = (int)Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory/8;
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount();
            }
        };
        mGvPic.setOnScrollListener(this);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final String url = getItem(position);
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.pic_item,null);
        }else {
            view = convertView;
        }
        final ImageView picItem = (ImageView)view.findViewById(R.id.iv_pic);
        //给iv设置一个tag，保证异步加载不会乱序
        picItem.setTag(url);
        setImageView(url,picItem);
        return view;
    }

    /**
     * 给iv设置图片 先从LruCache中取图片的缓存，设置，若没有，设置张正在加载的图片
     * @param imageUrl
     * @param imageView
     */
    private void setImageView(String imageUrl, ImageView imageView) {
        Bitmap bitmap = getBitmapFromCache(imageUrl);
        if (bitmap != null){
            imageView.setImageBitmap(bitmap);
        }else {
            imageView.setImageResource(R.drawable.loading);
        }
    }

    /**
     *将一张图片存储到缓存中
     * @param key
     * @param bitmap
     */
    public void addBitmapToCache(String key,Bitmap bitmap) {
        if (getBitmapFromCache(key) != null) {
            mMemoryCache.put(key,bitmap);
        }
    }

    /**
     * 从Cache中取图片，不存在返回null
     * @param key
     * @return 返回缓存对应键的图片或者null
     */
    public Bitmap getBitmapFromCache(String key) {
        return mMemoryCache.get(key);
    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        //仅当GideView静止时才加载图片，滑动时取消所有正在下载的任务
        if (scrollState == SCROLL_STATE_IDLE){
            loadBitmaps(mFirstVisibleItem,mVisibleItemCount);
        }else {
            cancelAllTasks();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        mFirstVisibleItem = firstVisibleItem;
        mVisibleItemCount = visibleItemCount ;
        //下载的任务应该由onScrollStateChanged里调用，但首次进入程序的时候，onScrollStateChanged并不会调用
        //所以在这里开启下载任务
        if(isFirstEnter && visibleItemCount > 0) {
            loadBitmaps(mFirstVisibleItem,visibleItemCount);
            isFirstEnter = false;
        }
    }

    /**
     * 加载bitmap对象，此方法会在LruCache中检查所有屏幕中可见的iv中的bitmap对象，
     * 如果发现任何一个iv 中的bitmap不在缓存中，就会开启异步线程 去下载
     * @param firstVisibleItem
     * @param visibleItemCount
     */
    private void loadBitmaps(int firstVisibleItem,int visibleItemCount){
        try {
            for (int i = firstVisibleItem; i < firstVisibleItem + visibleItemCount; i++) {
                String imageUrl = Images.imageThumbUrls[i];
                Bitmap bitmap = getBitmapFromCache(imageUrl);
                if (bitmap == null) {
                    BitmapWorkerTask task = new BitmapWorkerTask();
                    taskCollection.add(task);
                    task.execute(imageUrl);
                }else {
                    ImageView imageView = (ImageView) mGvPic.findViewWithTag(imageUrl);
                    if (imageView != null && bitmap != null) {
                        imageView.setImageBitmap(bitmap);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 取消所有正在下载或等待的任务
     */
    public void cancelAllTasks() {
        if (taskCollection != null) {
            for (BitmapWorkerTask task : taskCollection) {
                task.cancel(false);
            }
        }
    }

    /**
     * 异步下载任务
     */

    class BitmapWorkerTask extends AsyncTask<String,Void,Bitmap> {
        //图片的url地址
        private String imageUrl;

        @Override
        protected Bitmap doInBackground(String... params) {
            imageUrl = params[0];
            //在后台下载图片
            Bitmap bitmap = downloadBitmap(imageUrl);
            if (bitmap != null) {
                //存储在缓存中
                addBitmapToCache(imageUrl,bitmap);
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            //根据tag找到相应 的iv控件 将图片显示出来
            ImageView imageView = (ImageView)mGvPic.findViewWithTag(imageUrl);
            if (imageView != null && bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
            taskCollection.remove(this);
        }

        /**
         * 建立HTTP请求，并获取bitmap对象
         * @param imageUrl 请求的url
         * @return 返回bitmap对象
         */
        private Bitmap downloadBitmap(String imageUrl) {
            Bitmap bitmap = null;
            HttpURLConnection con = null;
            try {
                URL url = new URL(imageUrl);
                con = (HttpURLConnection)url.openConnection();
                con.setReadTimeout(5*1000);
                con.setConnectTimeout(10*1000);
                bitmap = BitmapFactory.decodeStream(con.getInputStream());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (con != null){
                    con.disconnect();
                }
            }
            return bitmap;
        }
    }
}
