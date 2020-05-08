package com.example.p03_classjournal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class layout_adapter extends ArrayAdapter<DailyCA> {
    private ArrayList<DailyCA>grade;
    private TextView tvWeek, tvGrade;
    private Context context;


    public layout_adapter(@NonNull Context context, int resource,ArrayList<DailyCA>objects) {
        super(context, resource,objects);
        // Store the food that is passed to this adapter
        grade = objects;
        // Store Context object as we would need to use it later
        this.context = context;
    }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

        // The usual way to get the LayoutInflater object to
        //  "inflate" the XML file into a View object
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // "Inflate" the row.xml as the layout for the View object
        View rowView = inflater.inflate(R.layout.module_info_layout, parent, false);


        // Get the TextView object
        tvWeek = (TextView) rowView.findViewById(R.id.tvWeek);
        tvGrade = (TextView)rowView.findViewById(R.id.tvGrade);



        // The parameter "position" is the index of the
        //  row ListView is requesting.
        //  We get back the food at the same index.
        DailyCA currentDays = grade.get(position);
        // Set the TextView to show the food



        tvWeek.setText("week"+currentDays.getWeek());
        tvGrade.setText(currentDays.getDgGrade());

        // Return the nicely done up View to the ListView
        return rowView;
    }
}
