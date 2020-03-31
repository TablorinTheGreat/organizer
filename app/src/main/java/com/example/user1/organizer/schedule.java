package com.example.user1.organizer;
import com.example.user1.organizer.adapters.*;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.Calendar;


public class schedule extends Fragment {
    public schedule() {
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_schedule,container,false);
        ViewPagerAdapter  viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        ViewPager mViewPager = (ViewPager) v.findViewById(R.id.container);
        mViewPager.setAdapter(viewPagerAdapter);
        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK)-1;
        if(day==6)day=0;
        mViewPager.setCurrentItem(day);
        return v;
    }

}
