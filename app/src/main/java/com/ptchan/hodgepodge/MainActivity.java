package com.ptchan.hodgepodge;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class MainActivity extends Activity {

    private GridView gvPic;
    private GVAdapter mGvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

    }

    private void init() {
        setContentView(R.layout.activity_main);
        gvPic = (GridView) findViewById(R.id.gv_pic);
        mGvAdapter = new GVAdapter(this,0,Images.imageThumbUrls,gvPic);
        gvPic.setAdapter(mGvAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //退出程序结束所有下载任务
        mGvAdapter.cancelAllTasks();
    }
}

