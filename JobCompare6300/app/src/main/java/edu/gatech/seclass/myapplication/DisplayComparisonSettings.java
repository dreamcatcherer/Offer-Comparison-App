package edu.gatech.seclass.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.File;

public class DisplayComparisonSettings extends AppCompatActivity {
    EditText text_wtYearlySalary, text_wtYearlyBonus,text_wtAllowedTelework,text_wtLeaveTime,text_wtSharesOffered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_comparison_settings);

        text_wtYearlySalary = findViewById(R.id.wtYearlySalary);
        text_wtYearlyBonus = findViewById(R.id.wtYearlyBonus);
        text_wtAllowedTelework = findViewById(R.id.wtAllowedTelework);
        text_wtLeaveTime = findViewById(R.id.wtLeaveTime);
        text_wtSharesOffered = findViewById(R.id.wtSharesOffered);

        File jobs_database10 = new File("/data/data/edu.gatech.seclass.myapplication/databases/");
        if(jobs_database10.exists()){
            //update text based on what is in the database
            SQLiteDatabase jobs_database = SQLiteDatabase.openDatabase ("/data/data/edu.gatech.seclass.myapplication/databases/jobs_database10", null, SQLiteDatabase.OPEN_READWRITE);
            String queryCommandString__ = "SELECT * from " + "weights" ;
            Cursor cursor__ = jobs_database.rawQuery (queryCommandString__, null);
//        List returnList__= new ArrayList();
            while(cursor__.moveToNext()) {
                String wtYearlySalary = cursor__.getString(0);
                String wtYearlyBonus = cursor__.getString(1);
                String wtAllowedTelework = cursor__.getString(2);
                String wtLeaveTime = cursor__.getString(3);
                String wtSharesOffered = cursor__.getString(4);

                text_wtYearlySalary.setText(wtYearlySalary);
                text_wtYearlyBonus.setText(wtYearlyBonus);
                text_wtAllowedTelework.setText(wtAllowedTelework);
                text_wtLeaveTime.setText(wtLeaveTime);
                text_wtSharesOffered.setText(wtSharesOffered);
            }
        }




    }

    public void save(View view) {


        boolean invalidFlag = false;
        boolean emptyFlag = false;

        int wtYearlySalary=1;
        int wtYearlyBonus=1;
        int wtAllowedTelework=1;
        int wtLeaveTime=1;
        int wtSharesOffered=1;

        String tmp1 = text_wtYearlySalary.getText().toString();
        if(tmp1.isEmpty()) {
            emptyFlag = true;
            text_wtYearlySalary.setError("invalid input");
        }
        else{
            wtYearlySalary = Integer.parseInt(tmp1);
            if(wtYearlySalary<0) {
                invalidFlag = true;
                text_wtYearlySalary.setError("invalid negative input");
            }
        }


        String tmp2 = text_wtYearlyBonus.getText().toString();
        if(tmp2.isEmpty()) {
            emptyFlag = true;
            text_wtYearlyBonus.setError("invalid input");
        }
        else{
            wtYearlyBonus  = Integer.parseInt(tmp2);
            if(!emptyFlag && wtYearlyBonus<0) {
                invalidFlag = true;
                text_wtYearlyBonus.setError("invalid negative input");
            }
        }



        String tmp3 = text_wtAllowedTelework.getText().toString();
        if(tmp3.isEmpty()) {
            emptyFlag = true;
            text_wtAllowedTelework.setError("invalid input");
        }
        else{
            wtAllowedTelework = Integer.parseInt(tmp3);
            if(wtAllowedTelework<0){
                invalidFlag = true;
                text_wtAllowedTelework.setError("input between 0-5");
            }
        }


        String tmp4 = text_wtLeaveTime.getText().toString();
        if(tmp4.isEmpty()) {
            emptyFlag = true;
            text_wtLeaveTime.setError("invalid input");
        }
        else{
            wtLeaveTime = Integer.parseInt(tmp4);
            if(!emptyFlag && wtLeaveTime<0) {
                invalidFlag = true;
                text_wtLeaveTime.setError("invalid negative input");
            }
        }



        String tmp5 = text_wtSharesOffered.getText().toString();
        if(tmp5.isEmpty()) {
            emptyFlag = true;
            text_wtSharesOffered.setError("invalid input");
        }
        else{
            wtSharesOffered = Integer.parseInt(tmp5);
            if(!emptyFlag && wtSharesOffered<0) {
                invalidFlag = true;
                text_wtSharesOffered.setError("invalid negative input");
            }
        }


        if(!emptyFlag && !invalidFlag){
            // Saving info in the database
            int currentJobFlag = 1;
            SQL_db jobs_database = new SQL_db (DisplayComparisonSettings.this);
            jobs_database.addWeights(wtYearlySalary,
                    wtYearlyBonus,
                    wtAllowedTelework,
                    wtLeaveTime,
                    wtSharesOffered );

            text_wtYearlySalary.setText(String.valueOf(wtYearlySalary));
            text_wtYearlyBonus.setText(String.valueOf(wtYearlyBonus));
            text_wtAllowedTelework.setText(String.valueOf(wtAllowedTelework));
            text_wtLeaveTime.setText(String.valueOf(wtLeaveTime));
            text_wtSharesOffered.setText(String.valueOf(wtSharesOffered ));

            Intent intent = new Intent(this, main_activity.class);
            startActivity(intent);

            setContentView(R.layout.activity_display_comparison_settings);

        }



    }

    public void cancel(View view) {
        Intent intent = new Intent(this, main_activity.class);
        startActivity(intent);
    }
}