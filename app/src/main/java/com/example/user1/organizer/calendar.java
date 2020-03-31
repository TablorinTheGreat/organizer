package com.example.user1.organizer;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user1.organizer.adapters.ViewPagerAdapter;
import com.example.user1.organizer.adapters.monthAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class calendar extends Fragment {
    public calendar() {
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_calendar,container,false);
        final ViewPager mViewPager = (ViewPager) v.findViewById(R.id.container);
        final monthAdapter viewPagerAdapter = new monthAdapter(getActivity());
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.setCurrentItem(1);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
             int pos;
            Calendar c=Calendar.getInstance();
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pos=position;


            }
            @Override
            public void onPageScrollStateChanged(int state) {
             if(state==ViewPager.SCROLL_STATE_IDLE){

                 if (pos == 0)
                     c.add(Calendar.MONTH,-1);
                 else if (pos == 2)
                     c.add(Calendar.MONTH,1);
                 for(int i=0;i<3;i++)
                 {
                     c.add(Calendar.MONTH,i-1);
                     c.set(Calendar.DAY_OF_MONTH,1);
                     viewPagerAdapter.setText(new SimpleDateFormat("MMMM yyyy").format(c.getTime()),i);
                     viewPagerAdapter.setcount(c.getActualMaximum(Calendar.DAY_OF_MONTH),i,c.get(Calendar.DAY_OF_WEEK));
                     c.add(Calendar.MONTH,-(i-1));
                 }
                 mViewPager.setCurrentItem(1, false);
             }
            }
        });
        return v;
    }
}
