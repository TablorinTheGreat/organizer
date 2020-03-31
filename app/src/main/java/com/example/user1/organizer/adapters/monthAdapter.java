package com.example.user1.organizer.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user1.organizer.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by user1 on 22/11/2017.
 */

public class monthAdapter extends PagerAdapter {
    Context mContext;
    dayAdapter[] booksAdapter;
    TextView[] p;


    public monthAdapter(Context mContext) {
        this.mContext = mContext;
        p=new TextView[3];
        booksAdapter=new dayAdapter[3];
    }
public void setText(String g,int i){
    p[i].setText(g);
}
public void setcount(int i,int j,int k){
    if(booksAdapter!=null){
    booksAdapter[j].setMonth(i,k);
    booksAdapter[j].notifyDataSetChanged();

}}
    public void setHeight( int h,int h2)
    { dayAdapter.setHeight(h-h2/2);
        for(int i=0;i<3;i++)
        {
            booksAdapter[i].notifyDataSetChanged();
        }
       }
    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.calendar, container, false);
        Calendar c=Calendar.getInstance();
        c.add(Calendar.MONTH,position-1);
            c.set(Calendar.DAY_OF_MONTH,1);
            booksAdapter[position]  = new dayAdapter(c.getActualMaximum(Calendar.DAY_OF_MONTH), mContext,c.get(Calendar.DAY_OF_WEEK));
            ((GridView)layout.findViewById(R.id.month)).setAdapter(booksAdapter[position]);
        final LinearLayout l= (LinearLayout)layout.findViewById(R.id.l);
        ViewTreeObserver o=l.getViewTreeObserver();

        o.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
               if(dayAdapter.height==0)
                setHeight(l.getHeight(),((View)(((GridView)l.findViewById(R.id.month)).getChildAt(0))).getHeight());
                 else
               l.getViewTreeObserver().removeOnPreDrawListener(this);
                return true;
            }
        });
            p[position]=(TextView)layout.findViewById(R.id.t);
             p[position].setText(new SimpleDateFormat("MMMM yyyy").format(c.getTime()));
        container.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
}
