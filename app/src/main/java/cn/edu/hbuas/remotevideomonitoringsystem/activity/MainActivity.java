package cn.edu.hbuas.remotevideomonitoringsystem.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.util.ArrayList;
import java.util.List;

import cn.edu.hbuas.remotevideomonitoringsystem.R;
import cn.edu.hbuas.remotevideomonitoringsystem.adapter.MyPagerAdapter;
import cn.edu.hbuas.remotevideomonitoringsystem.fragment.RecordFragment;
import cn.edu.hbuas.remotevideomonitoringsystem.fragment.SettingFragment;
import cn.edu.hbuas.remotevideomonitoringsystem.fragment.VideoFragment;

public class MainActivity extends FragmentActivity {

    private ViewPager viewPager;
    private static BottomNavigationBar bottomNavigationBar;
    private List<Fragment> fragments; //ViewPager的数据源
    private static boolean BOTTOM_NAVIGATION_BAR_STATE = true;
//    private static final int BOTTOM_NAVIGATION_BAR_HIDE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initView();
    }

    private void initView() {
        setDefaultFragment();
        initBottomNavigationBar();
        initViewPager();
    }

    private void setDefaultFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fram_layout, new VideoFragment());
        transaction.commit();
    }

    //初始化ViewPager
    private void initViewPager() {
        viewPager = findViewById(R.id.viewPager);
        fragments = new ArrayList<>();
        fragments.add(new VideoFragment());
        fragments.add(new RecordFragment());
        fragments.add(new SettingFragment());

        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), fragments));
        viewPager.setOffscreenPageLimit(4);//ViewPager的缓存为2帧
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                bottomNavigationBar.selectTab(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        viewPager.setCurrentItem(0);//初始设置ViewPager选中第一帧
    }

    //初始化底部导航条
    private void initBottomNavigationBar() {
        bottomNavigationBar = findViewById(R.id.bottom_nav_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.setBarBackgroundColor(R.color.white);
        bottomNavigationBar.setActiveColor(R.color.black);
        bottomNavigationBar.setInActiveColor(R.color.transparent);


        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.video_select, "视频").setInactiveIcon(ContextCompat.getDrawable(this, R.drawable.video)))
                .addItem(new BottomNavigationItem(R.drawable.record_select, "记录").setInactiveIcon(ContextCompat.getDrawable(this, R.drawable.record)))
                .addItem(new BottomNavigationItem(R.drawable.setting_select, "设置").setInactiveIcon(ContextCompat.getDrawable(this, R.drawable.setting)))
                .setFirstSelectedPosition(0)
                .initialise(); //所有的设置需在调用该方法前完成

        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                viewPager.setCurrentItem(position);
                if (position != 1) {
                    bottomNavigationBar.show();
                }
//                switchTab(position);
            }

            @Override
            public void onTabUnselected(int position) {
            }

            @Override
            public void onTabReselected(int position) {
            }
        });
    }

    private void switchTab(int position) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (position) {
            case 0:
                transaction.replace(R.id.fram_layout, new VideoFragment());
                break;
            case 1:
                transaction.replace(R.id.fram_layout, new RecordFragment());
                break;
            case 2:
                transaction.replace(R.id.fram_layout, new SettingFragment());
                break;
        }
        transaction.commit();
    }

    public static void hideBottomNavigation() {
        if (BOTTOM_NAVIGATION_BAR_STATE) {
            bottomNavigationBar.hide();
            BOTTOM_NAVIGATION_BAR_STATE = false;
        } else {
            bottomNavigationBar.show();
            BOTTOM_NAVIGATION_BAR_STATE = true;
        }
    }
}
