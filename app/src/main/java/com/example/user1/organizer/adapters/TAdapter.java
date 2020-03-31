package com.example.user1.organizer.adapters;
import com.example.user1.organizer.*;
import com.example.user1.organizer.datatypes.*;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import static android.graphics.Paint.ANTI_ALIAS_FLAG;

/**
 * Created by user1 on 09/06/2017.
 */

public class TAdapter extends ArrayAdapter<scheHour> {
    public TAdapter(ArrayList<scheHour> data, Context context) {
        super(context, R.layout.item, data);

    }
    String[] times=new String[]{"8:30-9:15","9:15-10:00","10:15-11:00","11:00-11:45","11:55-12:40","13:00-13:45","13:50-14:35","14:40-15:25","15:25-16:10","16:10-16:55","16:55-17:40","rega","areg","aregr","sdg"};

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        scheHour dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }
        // Lookup view for data population
        ((Button) convertView.findViewById(R.id.name)).setText(dataModel.getHour());
        ((Button) convertView.findViewById(R.id.time)).setText(times[dataModel.getNum()-1]);
        ((FloatingActionButton) convertView.findViewById(R.id.num)).setImageBitmap(textAsBitmap(Integer.toString(dataModel.getNum()), 40, Color.WHITE));
        return convertView;
    }
    public static Bitmap textAsBitmap(String text, float textSize, int textColor) {
        Paint paint = new Paint(ANTI_ALIAS_FLAG);
        paint.setTextSize(textSize);
        paint.setColor(textColor);
        paint.setTextAlign(Paint.Align.LEFT);
        float baseline = -paint.ascent(); // ascent() is negative
        int width = (int) (paint.measureText(text) + 0.0f); // round
        int height = (int) (baseline + paint.descent() + 0.0f);
        Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(image);
        canvas.drawText(text, 0, baseline, paint);
        return image;
    }

public int getBellCount(){
    return times.length;
}
}

