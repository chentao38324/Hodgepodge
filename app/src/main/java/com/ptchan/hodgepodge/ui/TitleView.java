package com.ptchan.hodgepodge.ui;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ptchan.hodgepodge.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chentao on 16-6-16.
 */
public class TitleView extends FrameLayout {

    @BindView(R.id.btn_left)
    Button btnLeft;//左边按钮
    @BindView(R.id.title_text)
    TextView titleText;//中间标题


    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title, this);
        ButterKnife.bind((Activity)context);
    }

    @OnClick(R.id.btn_left)
    public void onClick() {
        ((Activity)getContext()).finish();
    }

    /**
     * 更改标题文字
     * */
    public void setTitleText(String text){
        titleText.setText(text);
    }

    /**
     * 更改左边按钮文字
     * */
    public void setBtnLeft(String text) {
        btnLeft.setText(text);
    }

    public void setLeftButtonListener(OnClickListener l){
        btnLeft.setOnClickListener(l);
    }
}
