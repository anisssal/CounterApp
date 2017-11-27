package com.example.z.counter;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.z.counter.activity.PertandinganBaru;

import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * Created by z on 26/11/17.
 */


public  class DateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    public String dateee ;
    int year, month, day;
    EditText editText = PertandinganBaru.edtDate;
    String hari;
    public DateFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        if (editText.getText().toString().equals("")){

             year = calendar.get(Calendar.YEAR);
             month = calendar.get(Calendar.MONTH);
             day = calendar.get(Calendar.DAY_OF_MONTH);

        }else{

         String a = editText.getText().toString();
            StringTokenizer token = new StringTokenizer(a);
            day = Integer.parseInt(token.nextToken("/"));
            month = Integer.parseInt(token.nextToken("/"));
            year = Integer.parseInt(token.nextToken("/"));

        }

        return new DatePickerDialog(getActivity(),this, year,month,day );
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        month = month+1;
        dateee = new StringBuilder().append(day).append("/").append(month).append("/").append(year)+"";
        editText.setText(dateee);



    }
}