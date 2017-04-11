package com.cn.keranbing.ice.Glide.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by keranbin on 2017/2/22.
 */

public class PagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> lists;
    private String str[][];

    public PagerAdapter(FragmentManager fm, ArrayList<Fragment> list, String[][] str) {
        super(fm);
        this.lists = list;
        this.str = str;
    }

    @Override
    public Fragment getItem(int position) {
        return lists.get(position);
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return str[position][0];
    }
}
