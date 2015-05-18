package com.kuanxue.kxw.view.activity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.kuanxue.kxw.R;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imkit.fragment.ConversationGroupListFragment;
import io.rong.imkit.fragment.ConversationListFragment;

/*
 */
public class LearnCoursesActivity extends FragmentActivity {

    private ViewPager viewPager;// 页卡内容
    private ImageView imageView;// 动画图片
    private TextView voiceAnswer, healthPedia, pDected;// 选项名称
    private List<Fragment> fragments;// Tab页面列表
    private int offset = 0;// 动画图片偏移量
    private int currIndex = 0;// 当前页卡编号
    private int bmpW;// 动画图片宽度
    private int selectedColor, unSelectedColor;
    /** 页卡总数 **/
    private static final int pageSize = 3;
//    Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String targetId = intent.getStringExtra("targetId");
        getIntent().setData(Uri.parse("rong://io.rong.imkit.demo").buildUpon().appendPath("conversation").appendPath("private")
                .appendQueryParameter("targetId", targetId).appendQueryParameter("title", null).build());
        setContentView(R.layout.activity_learn_courses);
        initView();


        final ImageView fabIconNew = new ImageView(this);
        fabIconNew.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_new_light));
        final FloatingActionButton rightLowerButton = new FloatingActionButton.Builder(this)
                .setContentView(fabIconNew)
                .build();
        SubActionButton.Builder rLSubBuilder = new SubActionButton.Builder(this);
        ImageView rlIcon1 = new ImageView(this);
        ImageView rlIcon2 = new ImageView(this);
        ImageView rlIcon3 = new ImageView(this);
        ImageView rlIcon4 = new ImageView(this);
        rlIcon1.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_chat_light));
        rlIcon2.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_camera_light));
        rlIcon3.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_video_light));
        rlIcon4.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_place_light));
        // Build the menu with default options: light theme, 90 degrees, 72dp radius.
        // Set 4 default SubActionButtons
        final FloatingActionMenu rightLowerMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(rLSubBuilder.setContentView(rlIcon1).build())
                .addSubActionView(rLSubBuilder.setContentView(rlIcon2).build())
                .addSubActionView(rLSubBuilder.setContentView(rlIcon3).build())
                .addSubActionView(rLSubBuilder.setContentView(rlIcon4).build())
                .attachTo(rightLowerButton)
                .build();
        // Listen menu open and close events to animate the button content view
        rightLowerMenu.setStateChangeListener(new FloatingActionMenu.MenuStateChangeListener() {
            @Override
            public void onMenuOpened(FloatingActionMenu menu) {
                // Rotate the icon of rightLowerButton 45 degrees clockwise
                fabIconNew.setRotation(0);
                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 45);
                ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(fabIconNew, pvhR);
                animation.start();
            }
            @Override
            public void onMenuClosed(FloatingActionMenu menu) {
                // Rotate the icon of rightLowerButton 45 degrees counter-clockwise
                fabIconNew.setRotation(45);
                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 0);
                ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(fabIconNew, pvhR);
                animation.start();
            }
        });
        // With custom button and content sizes and margin
//        int redActionButtonSize = getResources().getDimensionPixelSize(R.dimen.red_action_button_size);
//        int redActionButtonMargin = getResources().getDimensionPixelOffset(R.dimen.action_button_margin);
//        int redActionButtonContentSize = getResources().getDimensionPixelSize(R.dimen.red_action_button_content_size);
//        int redActionButtonContentMargin = getResources().getDimensionPixelSize(R.dimen.red_action_button_content_margin);
//        int redActionMenuRadius = getResources().getDimensionPixelSize(R.dimen.red_action_menu_radius);
//        int blueSubActionButtonSize = getResources().getDimensionPixelSize(R.dimen.blue_sub_action_button_size);
//        int blueSubActionButtonContentMargin = getResources().getDimensionPixelSize(R.dimen.blue_sub_action_button_content_margin);

    }
    private void initView() {
        selectedColor = getResources()
                .getColor(R.color.tab_title_pressed_color);
        unSelectedColor = getResources().getColor(
                R.color.tab_title_normal_color);

        InitImageView();
        InitTextView();
        InitViewPager();
    }
    /**
     * 初始化Viewpager页
     */
    private void InitViewPager() {
        viewPager = (ViewPager) findViewById(R.id.vPager);
        fragments = new ArrayList<Fragment>();
        fragments.add(new ConversationListFragment());

        fragments.add(new ConversationFragment());
//                fragments.add(new ConversationSettingFragment());
        fragments.add(new ConversationGroupListFragment());
        viewPager.setAdapter(new myPagerAdapter(getSupportFragmentManager(),
                fragments));
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    /**
     * 初始化头标
     *
     */
    private void InitTextView() {
        voiceAnswer = (TextView) findViewById(R.id.tab_1);
        healthPedia = (TextView) findViewById(R.id.tab_2);
        pDected = (TextView) findViewById(R.id.tab_3);

        voiceAnswer.setTextColor(selectedColor);
        healthPedia.setTextColor(unSelectedColor);
        pDected.setTextColor(unSelectedColor);

        voiceAnswer.setText("会话列表");
        healthPedia.setText("聊天会话");
        pDected.setText("群组");

        voiceAnswer.setOnClickListener(new MyOnClickListener(0));
        healthPedia.setOnClickListener(new MyOnClickListener(1));
        pDected.setOnClickListener(new MyOnClickListener(2));
    }

    /**
     * 初始化动画，这个就是页卡滑动时，下面的横线也滑动的效果，在这里需要计算一些数据
     */

    private void InitImageView() {
        imageView = (ImageView) findViewById(R.id.cursor);
        bmpW = BitmapFactory.decodeResource(getResources(),
                R.drawable.tab_selected_bg).getWidth();// 获取图片宽度
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        offset = (screenW / pageSize - bmpW) / 2;// 计算偏移量--(屏幕宽度/页卡总数-图片实际宽度)/2
        // = 偏移量
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        imageView.setImageMatrix(matrix);// 设置动画初始位置
    }

    /**
     * 头标点击监听
     */
    private class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        public void onClick(View v) {

            switch (index) {
                case 0:
                    voiceAnswer.setTextColor(selectedColor);
                    healthPedia.setTextColor(unSelectedColor);
                    pDected.setTextColor(unSelectedColor);
                    break;
                case 1:
                    healthPedia.setTextColor(selectedColor);
                    voiceAnswer.setTextColor(unSelectedColor);
                    pDected.setTextColor(unSelectedColor);
                    break;
                case 2:
                    pDected.setTextColor(selectedColor);
                    voiceAnswer.setTextColor(unSelectedColor);
                    healthPedia.setTextColor(unSelectedColor);
                    break;
            }
            viewPager.setCurrentItem(index);
        }

    }

    /**
     * 为选项卡绑定监听器
     */
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
        int two = one * 2;// 页卡1 -> 页卡3 偏移量

        public void onPageScrollStateChanged(int index) {
        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        public void onPageSelected(int index) {
            Animation animation = new TranslateAnimation(one * currIndex, one
                    * index, 0, 0);// 显然这个比较简洁，只有一行代码。
            currIndex = index;
            animation.setFillAfter(true);// True:图片停在动画结束位置
            animation.setDuration(300);
            imageView.startAnimation(animation);

            switch (index) {
                case 0:
                    voiceAnswer.setTextColor(selectedColor);
                    healthPedia.setTextColor(unSelectedColor);
                    pDected.setTextColor(unSelectedColor);
                    break;
                case 1:
                    healthPedia.setTextColor(selectedColor);
                    voiceAnswer.setTextColor(unSelectedColor);
                    pDected.setTextColor(unSelectedColor);
                    break;
                case 2:
                    pDected.setTextColor(selectedColor);
                    voiceAnswer.setTextColor(unSelectedColor);
                    healthPedia.setTextColor(unSelectedColor);
                    break;
            }
        }
    }

    /**
     * 定义适配器
     */
    class myPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragmentList;

        public myPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        /**
         * 得到每个页面
         */
        @Override
        public Fragment getItem(int arg0) {
            return (fragmentList == null || fragmentList.size() == 0) ? null
                    : fragmentList.get(arg0);
        }

        /**
         * 每个页面的title
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }

        /**
         * 页面的总个数
         */
        @Override
        public int getCount() {
            return fragmentList == null ? 0 : fragmentList.size();
        }
    }

}
