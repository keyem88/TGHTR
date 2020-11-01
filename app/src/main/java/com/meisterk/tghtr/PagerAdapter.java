package com.meisterk.tghtr;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.meisterk.tghtr.ProfileFragment;
import com.meisterk.tghtr.SettingsFragment;
import com.meisterk.tghtr.SongsFragment;
import com.meisterk.tghtr.SongtextFragment;

class PagerAdapter extends FragmentStateAdapter {

    private static final int NUM_PAGES = 4;

    public PagerAdapter(FragmentActivity fa) {
        super(fa);
    }

    @Override
    public Fragment createFragment(int pos) {
        switch (pos) {
            case 0: {
                return new SongtextFragment();
            }
            case 1: {

                return new SongsFragment();
            }
            case 2: {
                return new ProfileFragment();
            }
            case 3: {
                return new SettingsFragment();
            }
            default:
                return new SongtextFragment();
        }
    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }
}