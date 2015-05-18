package com.kuanxue.kxw.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.kuanxue.kxw.R;
import com.kuanxue.kxw.view.fragment.FoundcourseFragment;
import com.kuanxue.kxw.view.fragment.MycourseFragment;
import com.kuanxue.kxw.view.fragment.ProrecommendedFragment;
import com.kuanxue.kxw.view.fragment.SettingsFragment;

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {

    private static final String Tag = "MainActivity";
    private android.support.v4.app.FragmentManager frgManager;
    private RadioGroup bottomBar;
    private android.support.v4.app.FragmentTransaction frgTransaction;
    private ProrecommendedFragment proFragment;
    private FoundcourseFragment fouFragment;
    private MycourseFragment myFragment;
    private SettingsFragment setFragment;
    private RadioButton rb_prorecommended;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        frgManager = getSupportFragmentManager();
        initControls();
        setListener();
    }

    private void initControls() {
        bottomBar = (RadioGroup) this.findViewById(R.id.bottomBar);
    }

    private void setListener() {
        bottomBar.setOnCheckedChangeListener(this);
        onCheckedChanged(bottomBar,R.id.rb_prorecommended);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        frgTransaction = frgManager.beginTransaction();
        switch (i) {
            case R.id.rb_prorecommended:
                hideAllFragments(frgTransaction);
                if (proFragment == null) {
                    proFragment = new ProrecommendedFragment();
                    frgTransaction.add(R.id.fragment_root, proFragment, null);
                }
                frgTransaction.show(proFragment);
                break;
            case R.id.rb_foundsource:
                hideAllFragments(frgTransaction);
                if (fouFragment == null) {
                    fouFragment = new FoundcourseFragment();
                    frgTransaction.add(R.id.fragment_root, fouFragment, null);
                }
                frgTransaction.show(fouFragment);

                break;
            case R.id.rb_mycourse:
                hideAllFragments(frgTransaction);
                if (myFragment == null) {
                    myFragment = new MycourseFragment();
                    frgTransaction.add(R.id.fragment_root, myFragment, null);
                }
                frgTransaction.show(myFragment);
                break;
            case R.id.rb_settings:
                hideAllFragments(frgTransaction);
                if (setFragment == null) {
                    setFragment = new SettingsFragment();
                    frgTransaction.add(R.id.fragment_root, setFragment, null);
                }
                frgTransaction.show(setFragment);
                break;
        }
        frgTransaction.commit();
    }

    private void hideAllFragments(FragmentTransaction ft) {
        if (proFragment != null) {
            frgTransaction.hide(proFragment);
        }
        if (fouFragment != null) {
            frgTransaction.hide(fouFragment);
        }
        if (myFragment != null) {
            frgTransaction.hide(myFragment);
        }
        if (setFragment != null) {
            frgTransaction.hide(setFragment);
        }

    }
}
