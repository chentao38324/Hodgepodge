package com.ptchan.hodgepodge.common;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.ptchan.hodgepodge.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by chentao on 16-5-30.
 */
public class PicLoadActivity extends Activity {

    private ImageView imageView_glide;
    private ImageView imageView_picasso;
    private ImageView imageView_fresco;
    private CircleImageView circleImageView_uil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_load);
        imageView_glide = (ImageView)findViewById(R.id.iv_display_glide);
        imageView_picasso = (ImageView)findViewById(R.id.iv_display_picasso);
        imageView_fresco = (ImageView)findViewById(R.id.iv_display_fresco);
        circleImageView_uil = (CircleImageView)findViewById(R.id.civ_display_uil);
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

    public void uil(View v) {
        String imageUrl = "chrome-extension://laookkfknpbbblfpciffpaejjkokdgca/backgrounds/3068befa-45dd-491c-a506-66b86a5c65f4.jpg";

        //loadImage()
        /*ImageLoader.getInstance().loadImage(imageUrl,new SimpleImageLoadingListener(){
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                super.onLoadingComplete(imageUri, view, loadedImage);
                circleImageView_uil.setImageBitmap(loadedImage);
            }
        });*/

        //displayImage()
        ImageLoader.getInstance().displayImage(imageUrl,circleImageView_uil);

        ListView listView = new ListView(this);
        listView.setOnScrollListener(new PauseOnScrollListener(ImageLoader.getInstance(),true,true));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
