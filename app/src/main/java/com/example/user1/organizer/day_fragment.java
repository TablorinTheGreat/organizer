package com.example.user1.organizer;
import com.example.user1.organizer.adapters.TAdapter;
import com.example.user1.organizer.datatypes.*;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobeta.android.dslv.DragSortController;
import com.mobeta.android.dslv.DragSortListView;
import java.util.ArrayList;
import java.util.List;


public class day_fragment extends Fragment {
    public day_fragment() {
    }
 int day=0,pos;
    TAdapter p;
    DragSortListView l;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_day_fragment, container, false);
        ArrayList<scheHour> Day=MainActivity.week.get(day);
       /* String[] s=new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13"};

        int[] a=new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13};
        ArrayList<scheHour> k=new ArrayList<>();
        for(int i=0;i<12;i++)
            k.add(new scheHour(s[i],a[i]));*/
       p=new TAdapter(Day,getActivity());

     l=(DragSortListView) v.findViewById(R.id.day);
        ((FloatingActionButton)v.findViewById(R.id.add)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(p.getCount()<p.getBellCount()){
                    p.add(new scheHour("",p.getCount()+1));
                    p.notifyDataSetChanged();
                    l.setSelection(p.getCount());
                }
                else Toast.makeText(getActivity(),"מערכת מלאה. כדי להוסיף עוד שעות הארך את לוח הצלצולים",Toast.LENGTH_LONG).show();
            }
        });
        DragSortController controller = new DragSortController(l);
        controller.setDragHandleId(R.id.num);
        //controller.setClickRemoveId(R.id.);
        controller.setRemoveEnabled(false);
        controller.setSortEnabled(true);
        controller.setDragInitMode(1);
        controller.setBackgroundColor(Color.TRANSPARENT);
        //controller.setRemoveMode(removeMode);

        l.setFloatViewManager(controller);
        l.setOnTouchListener(controller);
        l.setDragEnabled(true);
        l.setAdapter(p);
        l.setDropListener(onDrop);
        l.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                pos=position;
                getActivity().startActionMode(mCallback);
                return false;
            }
        });




        return v;
    }

    private ActionMode.Callback mCallback = new ActionMode.Callback()
    {
        @Override
        public boolean onPrepareActionMode( ActionMode mode, Menu menu )
        {
            return false;
        }
        @Override
        public void onDestroyActionMode( ActionMode mode )
        {
            // TODO Auto-generated method stub
        }
        @Override
        public boolean onCreateActionMode( ActionMode mode, Menu menu )
        {
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate( R.menu.subeditmenu, menu );

            return true;
        }
        @Override
        public boolean onActionItemClicked( ActionMode mode, MenuItem item )
        {
            switch (item.getItemId())
            {
                case R.id.delete:
                    p.remove(p.getItem(pos));
                    for(int i=pos;i<p.getCount();i++){
                        p.getItem(i).setNum(i+1);
                    }
                    p.notifyDataSetChanged();
                    mode.finish();
                    break;
                case R.id.edit:
                    editdialod newFragment = new editdialod();
                    newFragment.initiate(pos,p);
                    newFragment.show(getActivity().getSupportFragmentManager(),"edit");
                    mode.finish();
                    break;
            }
            return false;
        }
    };
    private DragSortListView.DropListener onDrop = new DragSortListView.DropListener()
    {
        @Override
        public void drop(int from, int to)
        {
            if (from != to)
            {
                scheHour item = p.getItem(from);
                p.remove(item);
              //  MainActivity.week.get(day).remove(item);
                 p.insert(item, to);
              //  MainActivity.week.get(day).add(to,item);
                int start,finish;
                if(to<from)
                {
                    start=to;
                    finish=from;
                }
               else
                   {
                       start=from;
                       finish=to;
                   }
                for(int i=start;i<=finish;i++){
                    p.getItem(i).setNum(i+1);
                }
            }
        }
    };

    public void setDay(int day) {
        this.day = day;
    }
}
