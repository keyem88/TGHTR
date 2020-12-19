package com.meisterk.tghtr;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;

public class Main2Activity extends AppCompatActivity{

    TabLayout tabLayout;
    ViewPager2 viewPager;
    PagerAdapter adapter;
    String[] latestSongtexts;
    int latestSongtextsSize;
    String tag = getClass().getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Log.d("latestSongtexts", Arrays.toString(getLatestSongtexts()));

        tabLayout = findViewById(R.id.main_tabLayout);
        viewPager = findViewById(R.id.main_viewPager);

        adapter = new PagerAdapter(this);
        viewPager.setAdapter(adapter);

        Log.d(tag, Arrays.toString(latestSongtexts));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.i("PagerAdapter", "Tab: " + tab.getPosition());
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private String[] getLatestSongtexts(){
        Intent i = getIntent();
        int latestSongtextsSize = i.getIntExtra("latestSongtextsSize", 0);
        String[] result = new String[latestSongtextsSize];
        for(int index = 0; index<= latestSongtextsSize; index++){
            result[index] = i.getStringExtra(Integer.toString(index));
        }
        return result;
    }

}