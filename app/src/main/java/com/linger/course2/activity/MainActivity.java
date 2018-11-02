package com.linger.course2.activity;

import android.content.Intent;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.linger.course2.R;
import com.linger.course2.adapter.ClazzAdapter;
import com.linger.course2.base.BaseActivity;
import com.linger.course2.bean.Clazz;
import com.linger.course2.listener.Service;

import java.util.List;

public class MainActivity extends BaseActivity {
    private ListView classList;
    private List<Clazz.ResultBean> list;

    @Override
    protected void addContentView() {
        setContentView(R.layout.activity_main);
    }

   
    @Override
    protected void initContentView() {
        setTitleName("课程分类");
        setTitleBarBackgroundColor(R.color.colorWhite);//设置标题栏背景颜色
        setTitleNameColor(Color.BLACK);//设置标题颜色
        classList=findViewById(R.id.class_list);
        classList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Clazz.ResultBean entity=list.get(position);//获取每一个item的数据
                startActivity(new Intent(MainActivity.this,SecondActivity.class)
                .putExtra("a",entity.getEntity_id())
                .putExtra("b",entity.getCourse_type()));
            }
        });
        //网络数据的请求
        StringRequest request=new StringRequest(Service.first_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Clazz clazz=new Gson().fromJson(response,Clazz.class);
                list=clazz.getResult();//获取数据集合
                ClazzAdapter adapter=new ClazzAdapter(list,MainActivity.this);
                classList.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);//将请求添加到队列中
    }

    @Override
    protected boolean initTitleBar() {
        return true;
    }

}
