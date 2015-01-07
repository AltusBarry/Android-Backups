package com.example.altus.tabbedapptest;


import android.app.FragmentManager;
//import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.altus.tabbedapptest.com.example.altus.tabswipe.FragemtnTab1;
import com.example.altus.tabbedapptest.com.example.altus.tabswipe.FragemtnTab2;
import com.example.altus.tabbedapptest.com.example.altus.tabswipe.FragemtnTab3;

/**
 * Created by altus on 2015/01/06.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;

    private String tabTitles[] = new String[] { "Tab1", "Tab2", "Tab3" };

    public ViewPagerAdapter(android.support.v4.app.FragmentManager fragmentManager) {
        super(fragmentManager);
    }


    public int getCount() {
        return PAGE_COUNT;
    }


    public Fragment getItem(int position) {
        switch (position) {

// Open FragmentTab1.java
            case 0:
                FragemtnTab1 fragmenttab1 = new FragemtnTab1();
                return fragmenttab1;

// Open FragmentTab2.java
            case 1:
                FragemtnTab2 fragmenttab2 = new FragemtnTab2();
                return fragmenttab2;

// Open FragmentTab3.java
            case 2:
                FragemtnTab3 fragmenttab3 = new FragemtnTab3();
                return fragmenttab3;
        }
        return null;
    }

    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
