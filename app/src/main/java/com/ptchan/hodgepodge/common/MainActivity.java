package com.ptchan.hodgepodge.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jakewharton.disklrucache.DiskLruCache;
import com.ptchan.hodgepodge.R;
import com.ptchan.hodgepodge.photoWall.PhotosWall;
import com.ptchan.hodgepodge.photoWallFalls.PhotoWallFalls;
import com.ptchan.hodgepodge.scroller.ScrollerActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {
    public static final String TAG = "MainActivity";
    @BindView(R.id.btn_bk)
    Button btnBk;
    @BindView(R.id.btn_scroller)
    Button btnScroller;
    @BindView(R.id.btn_photos_wall)
    Button btnPhotosWall;
    @BindView(R.id.btn_photo_wall_falls)
    Button btnPhotoWallFalls;
    @BindView(R.id.btn_pic_load)
    Button btnPicLoad;
//    private Button btnPhotosWall;
//    private Button btnPhotoWallFalls;
//    private Button btnPicLoad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //just for record 2 what
//        btnPhotosWall = (Button) findViewById(R.id.btn_photos_wall);
//        btnPhotosWall.setOnClickListener(this);
//        btnPhotoWallFalls = (Button) findViewById(R.id.btn_photo_wall_falls);
//        btnPhotoWallFalls.setOnClickListener(this);
//        btnPicLoad = (Button) findViewById(R.id.btn_pic_load);
//        btnPicLoad.setOnClickListener(this);

    }



    @OnClick({R.id.btn_photos_wall, R.id.btn_photo_wall_falls, R.id.btn_pic_load, R.id.btn_bk, R.id.btn_scroller})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_photos_wall:
                startActivity(new Intent(MainActivity.this, PhotosWall.class));
                break;
            case R.id.btn_photo_wall_falls:
                startActivity(new Intent(MainActivity.this, PhotoWallFalls.class));
                break;
            case R.id.btn_pic_load:
                startActivity(new Intent(MainActivity.this, PicLoadActivity.class));
                break;
            case R.id.btn_bk:
                startActivity(new Intent(MainActivity.this, TestForButterKnife.class));
                break;
            case R.id.btn_scroller:
                startActivity(new Intent(MainActivity.this, ScrollerActivity.class));
                break;
        }
    }
}

