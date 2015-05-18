package com.kuanxue.kxw.utils;


import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.kuanxue.kxw.R;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * on 2015/5/13.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // ��ʼ����
        RongIM.init(this);
        // String token = getTokenFromAppServer();
        // �˴�ֱ�� hardcode �� token ��ֵ�����滻Ϊ���Լ��� Token��
        String token = "FctIRWq4lbwkhEwIgL/kp4oBQECSGOLe8fgqAldgrHuereTJljnyKUFcifBRjmXkb9ZoGuDYpYf+zD1ou1p2fw==";
        // �������Ʒ�������
        try {
            RongIM.connect(token, new RongIMClient.ConnectCallback() {

                @Override
                public void onSuccess(String s) {
                    // �˴��������ӳɹ���
                    Log.d("Connect:", "Login successfully.");
                    Toast.makeText(getApplicationContext(),"Login successfully--1",Toast.LENGTH_LONG).show();
                }

                @Override
                public void onError(ErrorCode errorCode) {
                    // �˴��������Ӵ���
                    Log.d("Connect:", "Login failed.");
                    Toast.makeText(getApplicationContext(),"Login failed--2.",Toast.LENGTH_LONG).show();

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
