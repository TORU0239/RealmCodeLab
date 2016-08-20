package io.realm.hanson2.twitter.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import io.realm.hanson2.twitter.ui.fragment.FavoritedFragment;
import io.realm.hanson2.twitter.ui.fragment.TimelineFragment;

/**
 * Created by toru on 2016. 8. 20..
 */
public class MainPagerAdapter extends FragmentStatePagerAdapter{

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "타임라인";

            case 1:
                return "마음";

            default:
                throw new RuntimeException("unexpected position: " + position);
        }
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TimelineFragment();

            case 1:
                return new FavoritedFragment();

            default:
                throw new RuntimeException("unexpected position: " + position);
        }
    }
}
