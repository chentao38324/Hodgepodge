package com.ptchan.hodgepodge.photoWallFalls;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.ptchan.hodgepodge.R;

/**
 * Created by chentao on 16-5-29.
 */
public class PhotoWallFalls extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_photo_wall_falls);
    }
}
