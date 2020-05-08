package com.example.p03_classjournal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ModuleGradesActivity extends AppCompatActivity {
    ListView lv;
    ArrayAdapter aa;
    ArrayList<DailyCA> ModuleCodes,C347,C302;
    Button btnInfo, btnSend, btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_layout);

        lv = (ListView) this.findViewById(R.id.InfoListView);
        btnInfo = findViewById(R.id.btnInfo);
        btnAdd = findViewById(R.id.btnAdd);
        btnSend = findViewById(R.id.btnEmail);

        ActionBar ab = getSupportActionBar();

        ModuleCodes = new ArrayList<DailyCA>();

        Intent i = getIntent();
        final int number = i.getIntExtra("number", 0);

        if (number == 0){
            ab.setTitle("Info For C347");
            ModuleCodes.add(new DailyCA("B","C347",1));
            ModuleCodes.add(new DailyCA("C","C347",2));
            ModuleCodes.add(new DailyCA("A","C347",3));
        }else if(number == 1){
            ab.setTitle("Info For C302");
            ModuleCodes.add(new DailyCA("B","C302",1));
            ModuleCodes.add(new DailyCA("C","C302",2));
            ModuleCodes.add(new DailyCA("A","C302",3));
        }
        aa = new layout_adapter(this, R.layout.module_info_layout, ModuleCodes);
        lv.setAdapter(aa);

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                if (number == 0){
                    i.setData(Uri.parse("https://www.rp.edu.sg/schools-courses/courses/full-time-diplomas/full-time-courses/modules/index/C347"));
                }else{
                    i.setData(Uri.parse("https://www.rp.edu.sg/schools-courses/courses/full-time-diplomas/full-time-courses/modules/index/C302"));
                }
                startActivity(i);
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent email = new Intent(Intent.ACTION_SEND);
                // Put essentials like email address, subject & body text
                email.putExtra(Intent.EXTRA_EMAIL,
                        new String[]{"jason_lim@rp.edu.sg"});
                email.putExtra(Intent.EXTRA_SUBJECT,
                        "Test Email from C347");
                email.putExtra(Intent.EXTRA_TEXT,
                        "");
                String statement = "Hi faci, \n\n I am....\nPlease see my remarks so far, thank you ! \n\n";
                for(int i = 0;i<ModuleCodes.size();i++){
                    statement +="Week "+ModuleCodes.get(i).getWeek()+": DG: "+ ModuleCodes.get(i).getDgGrade()+"\n";
                }
                email.putExtra(Intent.EXTRA_TEXT,statement);
                // This MIME type indicates email
                email.setType("message/rfc822");
                // createChooser shows user a list of app that can handle
                // this MIME type, which is, email
                startActivity(Intent.createChooser(email,
                        "Choose an Email client :"));

            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ModuleGradesActivity.this,AddWeek.class);

                i.putExtra("week",ModuleCodes.size());
                startActivityForResult(i,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Only handle when 2nd activity closed normally
        //  and data contains something
        if(resultCode == RESULT_OK){
            if (data != null) {
                // Get data passed back from 2nd activity
                DailyCA newCA = (DailyCA)data.getSerializableExtra("newCa");
                ModuleCodes.add(newCA);
                // If it is activity started by clicking
                Log.d("123123123",ModuleCodes.size()+"");
                aa.notifyDataSetChanged();;
                Toast.makeText(ModuleGradesActivity.this, newCA.getDgGrade(),
                        Toast.LENGTH_LONG).show();
            }
        }
    }

}
