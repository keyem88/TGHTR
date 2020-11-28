package com.meisterk.tghtr;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PagerAdapter extends FragmentStateAdapter {


    public PagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Log.i("PagerAdapter", "Position: " + position);
            switch (position){
                case 0:
                    return new SongtextFragment();
                case 1:
                    return new SongsFragment();
                case 2:
                    return new ProfileFragment();
                case 3:
                    return new SettingsFragment();
                default:
                    return null;
            }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}