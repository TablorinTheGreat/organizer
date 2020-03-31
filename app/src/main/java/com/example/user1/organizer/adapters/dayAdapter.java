package com.example.user1.organizer.adapters;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import  android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user1.organizer.R;

import java.util.Calendar;

/**
 * Created by user1 on 22/11/2017.
 */

public class dayAdapter extends BaseAdapter {
    int Count,firstday,rownum,header;
    static int height=0;
    Context c;
     String[] data;


    public dayAdapter(int count, Context c,int day) {
        firstday=day-1;
        Count = count;
        this.c = c;
        data=createmonth();
        rownum = (getCount()-7) / 7;
        header= Color.argb(0xff, 0x66, 0x99, 0x00);
    }

    public void setMonth(int count,int day) {
        Count = count;
        firstday=day-1;
        rownum = (getCount()-7) / 7;
    }

    public int getHeight() {
        return height;
    }

    public static void setHeight(int height) {
        dayAdapter.height = height;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        data=createmonth();
    }
    public String[] createmonth(){
        String[] l=new String[getCount()];
        int k=1;
        l[0]="s";
        l[1]="m";
        l[2]="t";
        l[3]="w";
        l[4]="t";
        l[5]="f";
        l[6]="s";
        for(int i=7;i<getCount();i++)
        if(i>=firstday+7&&k<=Count) {
            l[i]=Integer.toString(k);
            k++;
        }
        else l[i]="";
        return l;
    }

    @Override
    public int getCount() {
        int j=0;
        int y=Count+firstday;
        if(y%7!=0)
            j=7-(y%7);
        return y+j+7;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

            if (view == null) {
                final LayoutInflater layoutInflater = LayoutInflater.from(c);
                view = layoutInflater.inflate(R.layout.day, null);
            }
            if(height!=0) {


            TextView p = (TextView) view.findViewById(R.id.num);
            p.setText(data[i]);
            if (i < 7) {
                p.setBackgroundColor(header);
                view.setMinimumHeight(0);
            } else {

                view.setMinimumHeight(height / rownum);
                p.setBackgroundColor(Color.WHITE);
                    if(i%4==0)
                ((Button)view.findViewById(R.id.ev1)).setVisibility(View.VISIBLE);
                if(i%3==0)
                    ((Button)view.findViewById(R.id.ev2)).setVisibility(View.VISIBLE);

            }

        }
        else if(i==0)
            {
                TextView p = (TextView) view.findViewById(R.id.num);
                p.setText(data[i]);
            }
        return view;
    }
}
