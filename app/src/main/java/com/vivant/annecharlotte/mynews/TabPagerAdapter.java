package com.vivant.annecharlotte.mynews;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Anne-Charlotte Vivant on 13/12/2018.
 */
public class TabPagerAdapter extends FragmentStatePagerAdapter {

   // String tabarray[] = new String[]{"1", "2", "3"};
    Context ctx;

    public TabPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        ctx = context;
    }

    @Nullable
    @Override
    public String getPageTitle(int position) {
       // return tabarray[position];
       return  ctx.getResources().getStringArray(R.array.TabTitles)[position];
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new TopStoriesPageFragment();
            case 1:
                return new MostPopularPageFragment();
            case 2:
                return new BusinessFragmentPage();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
