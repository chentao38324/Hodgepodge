package com.ptchan.hodgepodge.common;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ptchan.hodgepodge.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chentao on 16-6-6.
 */
public class TestForButterKnife extends Activity {
    public static final String TAG = "TestForButterKnife";

    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.tv5)
    TextView tv5;
    @BindView(R.id.btn)
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lv_item);
        ButterKnife.bind(this);
        //两种方法获得LayoutInflater实例
//        LayoutInflater layoutInflater = LayoutInflater.from(this);
//        LayoutInflater layoutInflater1 = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        layoutInflater.inflate(R.layout.lv_item,null);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick excute");
            }
        });
        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG,"onTouch excute,action:"+event.getAction());
                return false;
            }
        });
    }

    @OnClick({R.id.tv1, R.id.tv2, R.id.tv3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv1:
                break;
            case R.id.tv2:
                break;
            case R.id.tv3:
                break;
        }
    }
}
