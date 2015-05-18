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
        // 初始化。
        RongIM.init(this);
        // String token = getTokenFromAppServer();
        // 此处直接 hardcode 给 token 赋值，请替换为您自己的 Token。
        String token = "FctIRWq4lbwkhEwIgL/kp4oBQECSGOLe8fgqAldgrHuereTJljnyKUFcifBRjmXkb9ZoGuDYpYf+zD1ou1p2fw==";
        // 连接融云服务器。
        try {
            RongIM.connect(token, new RongIMClient.ConnectCallback() {

                @Override
                public void onSuccess(String s) {
                    // 此处处理连接成功。
                    Log.d("Connect:", "Login successfully.");
                    Toast.makeText(getApplicationContext(),"Login successfully--1",Toast.LENGTH_LONG).show();
                }

                @Override
                public void onError(ErrorCode errorCode) {
                    // 此处处理连接错误。
                    Log.d("Connect:", "Login failed.");
                    Toast.makeText(getApplicationContext(),"Login failed--2.",Toast.LENGTH_LONG).show();

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
