package com.kuanxue.kxw.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.kuanxue.kxw.R;
import com.kuanxue.kxw.view.activity.CoursePublishActivity;
import com.kuanxue.kxw.view.adapter.MyLearnAdapter;
import com.kuanxue.kxw.view.adapter.MyteachAdapter;
import com.kuanxue.kxw.widget.xlistview.XListView;

import java.util.ArrayList;

public class MycourseFragment extends Fragment {
    private LinearLayout mMiddle;
    private View view;
    private Button btn_publish;
    private LinearLayout mTeach;
    private LinearLayout mLearn;
    private XListView lv_Learn;
    private XListView lv_Teach;
    private ArrayList<String> learnList, teachList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mycourse, container, false);
        RadioButton rb_learn = (RadioButton) view.findViewById(R.id.rb_mylearn);
        RadioButton rb_teach = (RadioButton) view.findViewById(R.id.rb_myteach);
        mMiddle = (LinearLayout) view.findViewById(R.id.layout_middle);
        mTeach = (LinearLayout) inflater.inflate(R.layout.layout_middleview_teach, container, false);
        mLearn = (LinearLayout) inflater.inflate(R.layout.layout_middleview_learn, container, false);
        btn_publish = (Button) mTeach.findViewById(R.id.btn_publish);
        btn_publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btn_publish) {
                    Intent intent = new Intent(getActivity(), CoursePublishActivity.class);
                    startActivity(intent);
                }
            }
        });
        lv_Teach = (XListView) mTeach.findViewById(R.id.lv_my_teach);
        lv_Learn = (XListView) mLearn.findViewById(R.id.lv_my_learn);

        learnList = new ArrayList<String>();
        teachList = new ArrayList<String>();
        learnList.add("1");
        learnList.add("2");
        learnList.add("3");
        learnList.add("4");
        learnList.add("5");
        learnList.add("6");
        learnList.add("7");
        learnList.add("8");
        teachList.add("1");
        teachList.add("2");
        teachList.add("3");
        teachList.add("4");
        teachList.add("5");
        teachList.add("6");
        teachList.add("7");
        teachList.add("8");
        lv_Teach.setAdapter(new MyteachAdapter(getActivity(), teachList));
        lv_Learn.setAdapter(new MyLearnAdapter(getActivity(), learnList));
        mMiddle.addView(mTeach);
        mMiddle.addView(mLearn);
        rb_teach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplication(), "rb_1", Toast.LENGTH_SHORT).show();
                hideAllView();
                mTeach.setVisibility(View.VISIBLE);
            }
        });
        rb_learn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplication(), "rb_2", Toast.LENGTH_SHORT).show();
                hideAllView();
                mLearn.setVisibility(View.VISIBLE);

            }
        });



        return view;
    }

    private void hideAllView() {
        mTeach.setVisibility(View.GONE);
        mLearn.setVisibility(View.GONE);
    }
}
