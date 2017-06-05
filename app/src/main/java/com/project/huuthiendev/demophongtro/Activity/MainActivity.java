package com.project.huuthiendev.demophongtro.Activity;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.project.huuthiendev.demophongtro.Fragment.LocationFragment;
import com.project.huuthiendev.demophongtro.Fragment.MenuFragment;
import com.project.huuthiendev.demophongtro.Fragment.TimelineFragment;
import com.project.huuthiendev.demophongtro.R;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity {
    BottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
            if (tabId == R.id.tab_timeline) {
                TimelineFragment f = new TimelineFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frameContent, f).commit();
            }
            else if (tabId == R.id.tab_nearby) {
                LocationFragment f = new LocationFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frameContent, f).commit();
            }
            else if (tabId == R.id.tab_menu) {
                MenuFragment f = new MenuFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frameContent, f).commit();
            }
            }
        });
    }
}
