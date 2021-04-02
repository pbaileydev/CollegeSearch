package com.pbaileyapps.android.collegesearch.Adapters;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.pbaileyapps.android.collegesearch.Fragments.CollegeSearch;
import com.pbaileyapps.android.collegesearch.Fragments.ListFragment;
//import androidx.viewpager2.widget.ViewPager2;

public class WindowFragmentAdapter extends FragmentPagerAdapter {
    private FragmentManager fragmentManager;
    private int count;
    public WindowFragmentAdapter(FragmentManager fragmentManager, int count){
        super(fragmentManager);
        this.fragmentManager = fragmentManager;
        this.count = count;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ListFragment();
             case 1:
                return new CollegeSearch();
            default:
                return new ListFragment();
        }
    }

    @Override
    public int getCount() {
        return count ;
    }
}
