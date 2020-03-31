package com.example.user1.organizer;
import com.example.user1.organizer.datatypes.*;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by user1 on 09/11/2017.
 */

public class datahelper extends SQLiteOpenHelper {
    public datahelper(Context context) {
        super(context, "datahelper.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      db.execSQL("CREATE TABLE "+ scheHour.TABLE_NAME+" ( "
              +scheHour.HOUR_TITLE+" TEXT,"
              +scheHour.DAY_NUM_TITLE+" TINYINT,"
              +scheHour.HOUR_NUM_TITLE+" TINYINT )");
    }
    public  ArrayList<ArrayList<scheHour>> getWeek(){
    SQLiteDatabase db=getReadableDatabase();
    Cursor c=db.rawQuery("SELECT * FROM "+scheHour.TABLE_NAME,null);
    ArrayList<ArrayList<scheHour>> a= new ArrayList<>();
   for(int i=0;i<6;i++) {
       ArrayList<scheHour> b= new ArrayList<>();
       a.add(b);
   }
    if (c.moveToFirst()) {
        do {
            int f=Integer.valueOf(c.getString(1));
          a.get(f).add(new scheHour(c.getString(0),Integer.valueOf(c.getString(2))));

        } while (c.moveToNext());
        for(int i=0;i<6;i++)
            Collections.sort(a.get(i));
    }
    return a;
}
public boolean setWeek(ArrayList<ArrayList<scheHour>> l)
{
    if(l!=null&&!same(l,getWeek()))
    {
    String g="INSERT INTO "+scheHour.TABLE_NAME+" VALUES ";
    SQLiteDatabase db=getWritableDatabase();
    for(int i=0;i<l.size();i++) {
        for (int j = 0; j < l.get(i).size(); j++) {
            g += " ('" + l.get(i).get(j).getHour() + "'," + i + "," + l.get(i).get(j).getNum() + "), ";
        }
    }
        StringBuilder myName = new StringBuilder(g);
        myName.setCharAt(g.length()-2, ';');
        db.execSQL("DELETE FROM "+scheHour.TABLE_NAME+";");
    db.execSQL(myName.toString());
    return true;}
    return false;

}
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
    public boolean same(ArrayList<ArrayList<scheHour>> l,ArrayList<ArrayList<scheHour>> m)
    {
        for(int i=0;i<l.size();i++)
        {
            if(l.get(i).size()==m.get(i).size()) {
                for (int j = 0; j < l.get(i).size(); j++) {

                    if (!l.get(i).get(j).isequal(m.get(i).get(j)))
                        return false;
                }
            }
            else return false;
        }
        return true;
    }

}
