package com.kuanxue.kxw.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kuanxue.kxw.R;
import com.kuanxue.kxw.utils.WinToast;


public class LoginActivity extends Activity {
    private Button bt_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initControl();
        setListener();
    }
    private void initControl(){
        bt_login = (Button) findViewById(R.id.bt_login);
    }
    private void setListener(){
             bt_login.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Intent intent = new Intent();
                     intent.setClass(getApplicationContext(), MainActivity.class);
                     startActivity(intent);
//                     WinToast.makeText(getApplicationContext(),"µÇÂ¼³Â¹¦");
//                     WinToast.toast(getApplicationContext(),"µÇÂ¼³Â¹¦");
                     WinToast.toastWithCat(getApplication(),"xxx",true);
                     finish();
                 }
             });
    }
}

