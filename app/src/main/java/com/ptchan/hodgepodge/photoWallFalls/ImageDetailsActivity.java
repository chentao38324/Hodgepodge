package com.ptchan.hodgepodge.photoWallFalls;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.ptchan.hodgepodge.R;

/**
 * Created by chentao on 16-5-30.
 */
public class ImageDetailsActivity extends Activity {

    public static final String TAG = "ImageDetailsActivity";
    private ZoomImageView zoomImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.image_details);
        zoomImageView = (ZoomImageView)findViewById(R.id.zoom_image_view);
        String imagePath = getIntent().getStringExtra("image_path");
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        zoomImageView.setImageBitmap(bitmap);

        /*zoomImageView.setOnClickListener(new ZoomImageView.OnClickListener() {
            @Override
            public void onClick() {
                Log.d(TAG,"define onClick");
                finish();
            }
        });*/
    }
}
