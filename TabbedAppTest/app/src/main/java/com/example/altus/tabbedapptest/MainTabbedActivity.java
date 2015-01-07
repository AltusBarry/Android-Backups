package com.example.altus.tabbedapptest;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

public class MainTabbedActivity extends FragmentActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tabbed);

        //if(savedInstanceState == null) {
            ViewPager viewPager = (ViewPager) findViewById(R.id.tabbedPager);
            viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        //}

    }

}
