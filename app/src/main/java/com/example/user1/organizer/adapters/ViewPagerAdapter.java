package com.example.user1.organizer.adapters;
import com.example.user1.organizer.*;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by user1 on 30/08/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    day_fragment f;
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        f= new day_fragment();
    }
public void setday(int d){
   // f.setDay(d);
}
    @Override
    public Fragment getItem(int position) {
        f= new day_fragment();
        f.setDay(position);
        return f;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        switch (position)
        {
            case 0:
                title ="ראשון";
                break;
            case 1:
                title = "שני";
                break;
            case 2:
                title = "שלישי";
                break;
            case 3:
                 title ="רביעי";
                break;
            case 4:
                title = "חמישי";
                break;
            case 5:
                title = "שישי";
                break;
        }
        return title;
    }
}