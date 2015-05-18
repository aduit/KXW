package com.kuanxue.kxw.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kuanxue.kxw.R;
import com.kuanxue.kxw.view.activity.CourseliveActivity;

import java.util.ArrayList;

/**
 * Created by Dlzh on 2015-05-01.
 */
public class MyteachAdapter extends BaseAdapter {
    public LayoutInflater inflater;
    public Context context;
    public ArrayList<String> mList;

    public MyteachAdapter(Context context, ArrayList<String> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    ViewHolder holder;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            holder = new ViewHolder();
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.lv_content_myteach, null);
            holder.iv_pro_photo = (ImageView) convertView.findViewById(R.id.iv_pro_photo);
            holder.tv_pro_title = (TextView) convertView.findViewById(R.id.tv_pro_title);
            holder.tv_pro_content = (TextView) convertView.findViewById(R.id.tv_pro_content);
            holder.btn_teach = (Button) convertView.findViewById(R.id.btn_teach);
            holder.tv_teacher_name = (TextView) convertView.findViewById(R.id.tv_teacher_name);
            holder.tv_online_person_num = (TextView) convertView.findViewById(R.id.tv_online_person_num);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.btn_teach.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if(view.getId()==R.id.btn_teach){
                    Intent intent =  new Intent(context, CourseliveActivity.class);
                     context.startActivity(intent);
                 }
             }
         });
        return convertView;
    }

    class ViewHolder {
        private ImageView iv_pro_photo;
        private TextView tv_pro_title;
        private TextView tv_pro_content;
        private Button btn_teach;
        private TextView tv_teacher_name;
        private TextView tv_online_person_num;
    }


}
