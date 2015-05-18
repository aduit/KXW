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
import com.kuanxue.kxw.bean.CourseInfoBean;
import com.kuanxue.kxw.view.activity.LearnCoursesActivity;

import java.util.List;
import java.util.Map;

/**
 * Created by Dlzh on 2015-05-01.
 */
public class FoundcoursesAdapter extends BaseAdapter {
    public LayoutInflater inflater;
    public Context context;
    //    public List<CourseInfoBean.CourseInfoData> dataList;
    public List<Map<String, String>> listItems;

    public FoundcoursesAdapter(Context context, List<Map<String, String>> listItems) {
        this.context = context;
//        this.dataList = dataList;
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
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
            convertView = inflater.inflate(R.layout.lv_content_prorecommended, null);
            holder.iv_pro_photo = (ImageView) convertView.findViewById(R.id.iv_pro_photo);
            holder.tv_pro_title = (TextView) convertView.findViewById(R.id.tv_pro_title);
            holder.tv_pro_content = (TextView) convertView.findViewById(R.id.tv_pro_content);
            holder.btn_learn = (Button) convertView.findViewById(R.id.btn_learn);
            holder.tv_online_person_num = (TextView) convertView.findViewById(R.id.tv_online_person_num);
            holder.tv_teacher_name = (TextView) convertView.findViewById(R.id.tv_teacher_name);  //teachername
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();

        }

        //ÃÌº”listview–≈œ¢°£
//        holder.iv_pro_photo = (ImageView) convertView.findViewById(R.id.iv_pro_photo);
        holder.tv_pro_title.setText(listItems.get(position).get("name"));  //name
        holder.tv_pro_content.setText(listItems.get(position).get("description")); //description
        holder.tv_online_person_num.setText(listItems.get(position).get("studyNum")); //studyNum
        holder.tv_teacher_name.setText(listItems.get(position).get("teacherName"));  //teachername
        holder.btn_learn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.btn_learn) {
                    //
                    Intent intent = new Intent(context, LearnCoursesActivity.class);
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
        private Button btn_learn;
        private TextView tv_teacher_name;
        private TextView tv_online_person_num;
    }


}
