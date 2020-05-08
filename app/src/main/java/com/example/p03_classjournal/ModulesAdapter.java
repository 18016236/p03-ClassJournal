package com.example.p03_classjournal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ModulesAdapter extends ArrayAdapter<Module> {
    private ArrayList<Module> grades;
    private TextView tvname,tvCode;
    private Context context;



    public ModulesAdapter(Context context, int resource, ArrayList<Module> objects) {
        super(context,resource,objects);
        // Store the food that is passed to this adapter
        grades = objects;
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
            View rowView = inflater.inflate(R.layout.info_layout, parent, false);


            // Get the TextView object
            tvname = (TextView) rowView.findViewById(R.id.tvWeek);
            tvCode = (TextView)rowView.findViewById(R.id.tvGrade);



            // The parameter "position" is the index of the
            //  row ListView is requesting.
            //  We get back the food at the same index.
            Module currentDays = grades.get(position);
            // Set the TextView to show the food



            tvname.setText(currentDays.getName());
            tvCode.setText(currentDays.getCode());

            // Return the nicely done up View to the ListView
            return rowView;
    }
}
