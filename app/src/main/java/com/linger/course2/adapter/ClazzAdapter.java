package com.linger.course2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.linger.course2.R;
import com.linger.course2.bean.Clazz;

import java.util.List;

public class ClazzAdapter extends BaseAdapter {
    private List<Clazz.ResultBean> list;
 	private Context context;
    private LayoutInflater inflater;

    public ClazzAdapter(List<Clazz.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
        this.inflater=LayoutInflater.from(context);
    }

   
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder=new ViewHolder();
            convertView=inflater.inflate(R.layout.list_item,null);
            holder.image=convertView.findViewById(R.id.show_pic);
            holder.title=convertView.findViewById(R.id.show_title);
            holder.teacher = (TextView) convertView.findViewById(R.id.show_teacher);
            holder.time=convertView.findViewById(R.id.show_time);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        //增加展示的数据
        Clazz.ResultBean entity=list.get(position);
        holder.title.setText(entity.getCourse_name());
        holder.teacher.setText("主讲："+entity.getMain_teacher());
        holder.time.setText("时间："+entity.getClass_hour());
        Glide.with(context).load(entity.getCover()).into(holder.image);
        return convertView;
    }
    class ViewHolder{
        ImageView image;
        TextView title,teacher,time;
    }
}
