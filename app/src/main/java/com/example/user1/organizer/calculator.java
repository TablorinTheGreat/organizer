package com.example.user1.organizer;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


public class calculator extends Fragment {
    public calculator() {
    }
    TimePicker time;
    int h1 = 0, h2, m1 = 0, m2,t=-1;
    String s = "", g = "";
    TextView text;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v= inflater.inflate(R.layout.fragment_calculator,container,false);
        time =(TimePicker)v.findViewById(R.id.timePicker);
                time.setIs24HourView(true);
        text=(TextView)v.findViewById(R.id.result);
        ((Button)v.findViewById(R.id.clear)).setOnClickListener(new clear());
        ((Button)v.findViewById(R.id.plus)).setOnClickListener(new plus());
        ((Button)v.findViewById(R.id.min10)).setOnClickListener(new minus());
        ((Button)v.findViewById(R.id.min15)).setOnClickListener(new minus());
        ((Button)v.findViewById(R.id.min20)).setOnClickListener(new minus());
        ((Button)v.findViewById(R.id.min30)).setOnClickListener(new minus());
        ((Button)v.findViewById(R.id.min)).setOnClickListener(new minus());

        return v;
    }

    public void f(int i,int j)
    {
        if (t == -1)
        {
            h1 = time.getCurrentHour().intValue();
            m1 = time.getCurrentMinute().intValue();
            t = 0;
        }
        else
        {
            h2 = i;
            m2 = j;

            m1 = m1 - m2;
            h1 = h1 - h2;
            if (m1 < 0)
            {
                h1 = h1 - 1;
                m1 = 60 + m1;
            }
        }
        if (h1 < 0) h1 = h1 + 24;
        if (h1 < 10) s = "0";
        if (m1 < 10) g = "0";
        text.setText( s + "" + h1 + ":" + g + "" + m1);
        s = "";
        g = "";
    }
   class clear implements View.OnClickListener {

       @Override
       public void onClick(View view) {
           h1 = 0;
           h2 = 0;
           m1 = 0;
           m2 = 0;
           t = -1;
           text.setText ("0" + h1 + ":" +"0" + m1);
       }
   }

    class minus implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.min:
                    f(time.getCurrentHour().intValue(), time.getCurrentMinute().intValue());
                    break;
                case R.id.min15:
                    f(0, 15);
                    break;
                case R.id.min20:
                    f(0, 20);
                    break;
                case R.id.min10:
                    f(0, 10);
                    break;
                case R.id.min30:
                    f(0, 30);
                    break;
            }
        }
    }
    class plus implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (t == -1) t = 0;
            h2 = time.getCurrentHour().intValue();
            m2 = time.getCurrentMinute().intValue();
            h1 = h1 + h2;
            m1 = m1 + m2;
            if (m1 >= 60)
            {
                m1 = m1 - 60;
                h1 = h1 + 1;
            }
            if (h1 > 24) h1 = h1 - 24;
            if (h1 < 10) s = "0";
            if (m1 < 10) g = "0";
            text.setText( s + "" + h1 + ":" + g + "" + m1);
            s = "";
            g = "";
        }
    }

}



