package com.ptchan.hodgepodge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ptchan.hodgepodge.photoWall.PhotosWall;
import com.ptchan.hodgepodge.photoWallFalls.PhotoWallFalls;

public class MainActivity extends Activity implements View.OnClickListener{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_photos_wall:
                startActivity(new Intent(MainActivity.this,PhotosWall.class));
                break;
            case R.id.btn_photo_wall_falls:
                startActivity(new Intent(MainActivity.this,PhotoWallFalls.class));
                break;
            default:
                break;
        }
    }
}

