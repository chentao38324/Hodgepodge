package com.ptchan.hodgepodge.scroller;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.ptchan.hodgepodge.R;
import com.ptchan.hodgepodge.title.TitleView;

/**
 * Created by chentao on 16-6-14.
 */
public class ScrollerActivity extends Activity {

//    @BindView(R.id.scroller_title)
    private TitleView scrollerTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏状态栏
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_scrollerlayout);
//        ButterKnife.bind(this);
        scrollerTitle = (TitleView) findViewById(R.id.scroller_title);
        scrollerTitle.setLeftButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

//    @OnClick(R.id.scroller_title)
//    public void onClick() {
//    }
}
