package com.example.newgankio.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.newgankio.View.HomeFragment;
import com.example.newgankio.View.KnowladgeFragment;
import com.example.newgankio.View.ProjectFragment;

public class ViewpagerAdapter extends FragmentPagerAdapter{
    private String[] mBlowView = new String[]{"首页", "知识体系", "项目"};

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //
    }

    public ViewpagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return new KnowladgeFragment();
        } else if (position == 2) {
            return new ProjectFragment();
        }
        return new HomeFragment();
    }

    @Override
    public int getCount() {
        return mBlowView.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mBlowView[position];
    }
}
