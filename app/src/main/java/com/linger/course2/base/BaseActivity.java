package com.linger.course2.base;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.linger.course2.R;
import com.linger.course2.app.MineApplication;

public abstract class BaseActivity extends AppCompatActivity {
    protected RequestQueue requestQueue;
    private RelativeLayout titleBar;
    private ImageView backBtn;
    private TextView titleName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestQueue=MineApplication.getRequestQueue();//获取请求队列
        addContentView();//设置界面的布局
        if(initTitleBar()){
            titleBar=findViewById(R.id.title_bar);
            backBtn=findViewById(R.id.btn_back);
            titleName=findViewById(R.id.title_name);
            addListener();
            setTitleBarShowType();
        }
        initContentView();//初始化界面

    }

    private void setTitleBarShowType() {
        if(showBackBtn()){
          backBtn.setVisibility(View.VISIBLE);
        }else {
            backBtn.setVisibility(View.GONE);
        }
    }

    private void addListener() {
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    protected abstract void addContentView();

    protected abstract void initContentView();

    protected  abstract boolean initTitleBar();
    protected boolean showBackBtn(){
        return false;
    }


    @SuppressLint("ResourceAsColor")
    protected final void setTitleBarBackgroundColor(int color){
        titleBar.setBackgroundResource(color);
    }
    protected final void setTitleName(@NonNull String name){
        titleName.setText(name);
    }
    @SuppressLint("ResourceAsColor")
    protected final void setTitleNameColor( int color){
        titleName.setTextColor(color);
    }

    @Override
    protected void onDestroy() {
        requestQueue.stop();
        super.onDestroy();

    }
}
