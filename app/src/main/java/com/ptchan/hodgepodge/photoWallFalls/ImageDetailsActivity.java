package com.ptchan.hodgepodge.photoWallFalls;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

import com.ptchan.hodgepodge.R;

/**
 * Created by chentao on 16-5-30.
 */
public class ImageDetailsActivity extends Activity {

    public static final String TAG = "ImageDetailsActivity";
    private ZoomImageView zoomImageView;
    //GestureDetector
    private GestureDetector mGestureDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.image_details);
        zoomImageView = (ZoomImageView)findViewById(R.id.zoom_image_view);
        String imagePath = getIntent().getStringExtra("image_path");
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        zoomImageView.setImageBitmap(bitmap);
        //手势监控
        mGestureDetector = new GestureDetector(this,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                //单击返回 不属于双击中的一次单击
                Log.d(TAG,"sinlge tap");
                finish();
                return false;//返回true 才能获取到完整的事件
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                //双击放大图片
                Log.d(TAG,"double tap");
                return true;
            }


        });
        zoomImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mGestureDetector.onTouchEvent(event);
                return false;
            }
        });
    }
}
