package com.meisterk.tghtr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private PagerAdapter pagerAdapter;
    private String[] titles = new String[]{"Tab1", "Tab2", "Tab3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.main_tablayout);
        viewPager = findViewById(R.id.main_viewPager);
        pagerAdapter = new PagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText(titles[position])).attach();
    }
}