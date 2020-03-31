package com.example.user1.organizer;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.user1.organizer.adapters.TAdapter;

/**
 * Created by user1 on 13/11/2017.
 */

public  class editdialod extends DialogFragment {

        int pos;
        TAdapter p;
        View v;

    public void initiate(int pos, TAdapter p) {
        this.pos = pos;
        this.p = p;
    }

    @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = getActivity().getLayoutInflater();
            v =inflater.inflate(R.layout.editdialog, null);
            builder.setView(v)
                    .setPositiveButton("אשר", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            p.getItem(pos).setHour(((EditText)v.findViewById(R.id.editText)).getText().toString());
                            p.notifyDataSetChanged();
                        }
                    })
                    .setNegativeButton("בטל", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            editdialod.this.getDialog().cancel();
                        }
                    });
        ((EditText)v.findViewById(R.id.editText)).setText(p.getItem(pos).getHour());
            // Create the AlertDialog object and return it
            return builder.create();
        }
    }
