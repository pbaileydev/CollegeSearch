package com.pbaileyapps.android.collegesearch.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.pbaileyapps.android.collegesearch.Adapters.WindowFragmentAdapter;
import com.pbaileyapps.android.collegesearch.Fragments.CollegeSearch;
import com.pbaileyapps.android.collegesearch.R;
import com.pbaileyapps.android.collegesearch.RetrofitComponents.WindowItem;
import com.pbaileyapps.android.collegesearch.RoomComponents.CollegeViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private SearchView searchView;
    private String query;
    private ArrayList<WindowItem> universityList;
    private CollegeSearch timerFragment;
    private WindowFragmentAdapter fragmentAdapter;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    public static CollegeViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager = findViewById(R.id.viewPager);
        fragmentManager = getSupportFragmentManager();
        viewModel = ViewModelProviders.of(this).get(CollegeViewModel.class);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentAdapter = new WindowFragmentAdapter(fragmentManager,2);
        viewPager.setAdapter(fragmentAdapter);
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Interests"));
        tabLayout.addTab(tabLayout.newTab().setText("Search"));
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition() == 1){
                    timerFragment = (CollegeSearch) fragmentAdapter.getItem(1);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        Intent intent = getIntent();
        if(intent.getStringExtra("QUERY_SEARCH") != null){

           viewPager.postDelayed(new Runnable() {
               @Override
               public void run() {
                   Log.d("QUERYSEARCH",intent.getStringExtra("QUERY_SEARCH"));
                   tabLayout.getTabAt(1).select();
                   viewPager.setCurrentItem(1);
               }
           },2000);
        }
        else {
            //searchView.setIconified(false);



            // Not sure what next line does

        }
    }
    }
