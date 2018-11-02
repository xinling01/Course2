package com.linger.course2.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.linger.course2.R;
import com.linger.course2.base.BaseActivity;
import com.linger.course2.bean.Details;
import com.linger.course2.listener.Service;

public class SecondActivity extends BaseActivity {
    private TextView title,time,people,name,about,message;
    private ImageView image;
    @Override
    protected void addContentView() {
        setContentView(R.layout.activity_second);
    }

    @Override
    protected void initContentView() {
        setTitleName("体系课程");
        setTitleBarBackgroundColor(Color.TRANSPARENT);//设置标题栏背景颜色
        setTitleNameColor(Color.WHITE);//设置标题颜色
        initView();//初始化控件
        Intent intent=getIntent();
        String a=intent.getStringExtra("a");
        String b=intent.getStringExtra("b");
        String url=String.format(Service.second_url,a,b);
        StringRequest request=new StringRequest(url, new Response.Listener<String>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onResponse(String response) {
                Details details=new Gson().fromJson(response,Details.class);
                title.setText(details.getCourse().getCourse_name());
                time.setText("课程时长："+details.getCourse().getClass_hour());
                people.setText("学习人数："+details.getCourse().getStudy_num());
                message.setText(details.getCourse().getDescription());
                name.setText(details.getCourse().getMain_teacher());
                about.setText(details.getCourse().getTeacher_info());
                Glide.with(SecondActivity.this).load(details.getCourse().getTeacher_pic()).into(image);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);
        requestQueue.start();
    }

    private void initView() {
        title=findViewById(R.id.second_title);
        time=findViewById(R.id.second_time);
        people=findViewById(R.id.second_people);
        message=findViewById(R.id.second_message);
        name=findViewById(R.id.second_name);
        about=findViewById(R.id.second_about);
        image=findViewById(R.id.second_image);
    }

    @Override
    protected boolean initTitleBar() {
        return true;
    }
    protected boolean showBackBtn(){
        return true;
    }
}
