package com.meisterk.tghtr;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PagerAdapter extends FragmentStateAdapter {
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return null;
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
