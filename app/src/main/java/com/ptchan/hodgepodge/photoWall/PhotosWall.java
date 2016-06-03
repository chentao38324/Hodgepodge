package com.ptchan.hodgepodge.photoWall;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

import com.ptchan.hodgepodge.common.Images;
import com.ptchan.hodgepodge.R;

/**
 * Created by chentao on 16-5-29.
 */
public class PhotosWall extends Activity{
    private GridView gvPic;
    private GVAdapter mGvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos_wall);
        gvPic = (GridView)findViewById(R.id.gv_pic);
        mGvAdapter = new GVAdapter(this,0, Images.imageThumbUrls,gvPic);
        gvPic.setAdapter(mGvAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //退出程序结束所有下载任务
        mGvAdapter.cancelAllTasks();
    }
}
