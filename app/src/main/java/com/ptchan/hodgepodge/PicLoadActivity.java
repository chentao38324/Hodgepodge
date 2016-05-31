package com.ptchan.hodgepodge;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

/**
 * Created by chentao on 16-5-30.
 */
public class PicLoadActivity extends Activity {

    private ImageView imageView_glide;
    private ImageView imageView_picasso;
    private ImageView imageView_fresco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_load);
        imageView_glide = (ImageView)findViewById(R.id.iv_display_glide);
        imageView_picasso = (ImageView)findViewById(R.id.iv_display_picasso);
        imageView_fresco = (ImageView)findViewById(R.id.iv_display_fresco);
    }

    public void glide(View v){
        Glide.with(PicLoadActivity.this)
                .load("http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg")
                .into(imageView_glide);
    }

    public void picasso(View v){
        Picasso.with(this)
                .load("http://img.my.csdn.net/uploads/201309/01/1378037234_6318.jpg")
                .into(imageView_picasso);
    }

    public void fresco(View v){

    }



}
