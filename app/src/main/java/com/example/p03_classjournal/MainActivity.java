package com.example.p03_classjournal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvModuleCodes;
    ArrayAdapter aa;
    ArrayList<String> ModuleCodesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvModuleCodes = findViewById(R.id.listViewModulesCode);

        ModuleCodesList = new ArrayList<String>();
        ModuleCodesList.add("C347");

        aa = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,ModuleCodesList);
        lvModuleCodes.setAdapter(aa);

        lvModuleCodes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent I = new Intent(MainActivity.this,ModuleGradesActivity.class);
                I.putExtra("number",i);
                startActivity(I);
            }
        });
    }
}
