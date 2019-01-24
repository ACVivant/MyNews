package com.vivant.annecharlotte.mynews.Views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.vivant.annecharlotte.mynews.Controller.NYTPageFragment;
import com.vivant.annecharlotte.mynews.Controller.NYTSearchPageFragment;
import com.vivant.annecharlotte.mynews.R;

/**
 * Link between main tab position and content
 */
public class TabPagerAdapter extends FragmentStatePagerAdapter {

    private Context ctx;
    private int key;

    public TabPagerAdapter(FragmentManager fm, Context context, int key) {
        super(fm);
        ctx = context;
        this.key = key;
    }

    @Nullable
    @Override
    public String getPageTitle(int position) {
       return  ctx.getResources().getStringArray(R.array.TabTitles)[position];
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new NYTPageFragment().newInstance(0);
            case 1:
                return new NYTPageFragment().newInstance(1);
            case 2:
            return new NYTPageFragment().newInstance(12);
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
