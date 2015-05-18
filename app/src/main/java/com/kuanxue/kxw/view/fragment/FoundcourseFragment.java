package com.kuanxue.kxw.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kuanxue.kxw.R;
import com.kuanxue.kxw.bean.CourseInfoBean;
import com.kuanxue.kxw.bean.CourseInfoBean.CourseInfoData;
import com.kuanxue.kxw.constant.UrlsInfo;
import com.kuanxue.kxw.view.adapter.FoundcoursesAdapter;
import com.kuanxue.kxw.widget.xlistview.XListView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.util.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.rong.imkit.RongIM;


public class FoundcourseFragment extends Fragment implements XListView.IXListViewListener {
    private XListView mListview;
    private Handler mHandler;
    private Button btn_learn;
    private int curPage = 1;
    //    private int currentPosition = 0;
    private FoundcoursesAdapter adapter;
    //定义该list，用于保存当前获取到的每页的item信息
    private final List<Map<String, String>> listItems = new ArrayList<Map<String, String>>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_foundcourse, container, false);

        mListview = (XListView) view.findViewById(R.id.lv_foundcourses);
        mListview.setPullRefreshEnable(true); //允许下拉刷新
        mListview.setPullLoadEnable(true); //上拉加载更多
        mListview.setXListViewListener(this);
        requestNetData();
        adapter = new FoundcoursesAdapter(getActivity(), listItems);
        mListview.setAdapter(adapter);
        mHandler = new Handler();
//        btn_learn = (Button) mListview.findViewById(R.id.btn_learn);
//        mHandler.post(new Runnable() {
//            @Override
//            public void run() {
//                String userId = "1";
//                String title = "自问自答";
//
//                RongIM.getInstance().startPrivateChat(getActivity(), userId, title);
//            }
//        });
        return view;
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                listItems.clear();
                curPage = 1;
                requestNetData();
                onLoad();
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                curPage++;
                requestNetData();
                onLoad();
            }
        }, 2000);
    }

    private void onLoad() {
        mListview.stopRefresh();
        mListview.stopLoadMore();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        mListview.setRefreshTime(sdf.format(new Date()));
    }


    //获取课程信息。
    private void requestNetData() {

        RequestParams params = new RequestParams();
        params.addQueryStringParameter("request_type", "getPhoneLiveList");
//		      请求json字符串对象
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("curPage", Integer.toString(curPage));
            jsonObj.put("pageSize", 5);
            jsonObj.put("name", "");
            jsonObj.put("teacherId", 0);
            jsonObj.put("tag", "");
            jsonObj.put("liveTypeId", 0);
            jsonObj.put("status", 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        params.addBodyParameter("param", jsonObj.toString());
        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.POST,
                UrlsInfo.HOST_TEST,
                params,
                new RequestCallBack<String>() {

                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        System.err.println(responseInfo.result.toString());
//                        Toast.makeText(getActivity(), "调用成功"+responseInfo.result.toString(), Toast.LENGTH_LONG).show();
                        //实例化gson框架
                        Gson gson = new Gson();
                        //gson框架将json数据和javabean绑定
                        CourseInfoBean courseInfo = gson.fromJson(responseInfo.result, CourseInfoBean.class);
                        //获取相应的返回数据信息
                        String status = courseInfo.getStatus();
                        String message = courseInfo.getMessage();
                        List<CourseInfoData> dataList = courseInfo.Result;
                        for (CourseInfoBean.CourseInfoData cinfo : dataList) {
                            Map<String, String> mapItem = new HashMap<String, String>();
                            mapItem.put("studyCost", cinfo.getStudyCost());
                            mapItem.put("studyNum", cinfo.getStudyNum());
                            mapItem.put("lectureDate", cinfo.getLectureDate());
                            mapItem.put("status", cinfo.getStatus());
                            mapItem.put("description", cinfo.getDescription());
                            mapItem.put("name", cinfo.getName());
                            mapItem.put("teacherName", cinfo.getTeacherName());
                            listItems.add(mapItem);
                        }
                        adapter.notifyDataSetChanged();
//                        mListview.setSelection(currentPosition-1);
//                        currentPosition = listItems.size();
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        System.err.println(error.toString());
                        Toast.makeText(getActivity(), "调用失败" + error + "" + msg.toString(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}


//                            cinfo.setStudyCost(cinfo.getStudyCost());
//                            cinfo.setStudyNum(cinfo.getStudyNum());
//                            cinfo.setLectureDate(cinfo.getLectureDate());
//                            cinfo.setStatus(cinfo.getStatus());
//                            cinfo.setDescription(cinfo.getDescription());
//                            cinfo.setName(cinfo.getName());
//                            cinfo.setTeacherName(cinfo.getTeacherName());