package edu.gatech.seclass.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.File;

public class DisplayCurrentJob extends AppCompatActivity {
    // Resource used: https://youtu.be/312RhjfetP8
    // Resource used: https://stackoverflow.com/questions/3386667/query-if-android-database-exists/12025733
    EditText text_jobTitle, text_company, text_locationCity, text_locationState, text_costLivingIndex, text_yearlySalary, text_yearlyBonus, text_weeklyTelework, text_leaveTime,  text_numCompanyShares;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_current_job);

        text_jobTitle = findViewById(R.id.jobTitle);
        text_company = findViewById(R.id.company);
        text_locationCity = findViewById(R.id.locationCity);
        text_locationState = findViewById(R.id.locationState);
        text_costLivingIndex = findViewById(R.id.wtSharesOffered);
        text_yearlySalary = findViewById(R.id.yearlySalary);
        text_yearlyBonus = findViewById(R.id.yearlyBonus);
        text_weeklyTelework = findViewById(R.id.weeklyTelework);
        text_leaveTime = findViewById(R.id.leaveTime);
        text_numCompanyShares = findViewById(R.id.numCompanyShares);

        SQLiteDatabase jobs_database = null;
        try {
            jobs_database = SQLiteDatabase.openDatabase("/data/data/edu.gatech.seclass.myapplication/databases/jobs_database10", null,
                    SQLiteDatabase.OPEN_READWRITE);
            //jobs_database.close();

            //File jobs_database10 = new File("/data/data/edu.gatech.seclass.myapplication/databases/");
            //if(jobs_database10.exists()){
                //update text based on what is in the database
                //SQLiteDatabase jobs_database = SQLiteDatabase.openDatabase ("/data/data/edu.gatech.seclass.myapplication/databases/jobs_database10", null, SQLiteDatabase.OPEN_READWRITE);
                String queryCommandString__ = "SELECT * from " + "currentJob" ;
                Cursor cursor__ = jobs_database.rawQuery (queryCommandString__, null);
                //        List returnList__= new ArrayList();
                while(cursor__.moveToNext()) {
                    String jobTitle = cursor__.getString(0);
                    String company = cursor__.getString(1);
                    String locationCity = cursor__.getString(2);
                    String locationState = cursor__.getString(3);
                    String costLivingIndex = cursor__.getString(4);
                    String yearlySalary = cursor__.getString(5);
                    String yearlyBonus = cursor__.getString(6);
                    String weeklyTelework = cursor__.getString(7);
                    String leaveTime = cursor__.getString(8);
                    String numCompanyShares = cursor__.getString(9);

                    // text_wtYearlySalary.setText(wtYearlySalary);
                    text_jobTitle.setText(jobTitle);
                    text_company.setText(company);
                    text_locationCity.setText(locationCity);
                    text_locationState.setText(locationState);
                    text_costLivingIndex.setText(costLivingIndex);
                    text_yearlySalary.setText(yearlySalary);
                    text_yearlyBonus.setText(yearlyBonus);
                    text_weeklyTelework.setText(weeklyTelework);
                    text_leaveTime.setText(leaveTime);
                    text_numCompanyShares.setText(numCompanyShares);
                    //text_wtYearlyBonus.setText(wtYearlyBonus);
                    ///text_wtAllowedTelework.setText(wtAllowedTelework);
                    //text_wtLeaveTime.setText(wtLeaveTime);
                    //text_wtSharesOffered.setText(wtSharesOffered);
                }
            //}

        } catch (Exception e) {
            // database doesn't exist yet.
            File jobs_database10 = new File("/data/data/edu.gatech.seclass.myapplication/databases/");
        }

/*        File jobs_database10 = new File("/data/data/edu.gatech.seclass.myapplication/databases/");
        if(jobs_database10.exists()){
            //update text based on what is in the database
            SQLiteDatabase jobs_database = SQLiteDatabase.openDatabase ("/data/data/edu.gatech.seclass.myapplication/databases/jobs_database10", null, SQLiteDatabase.OPEN_READWRITE);
            String queryCommandString__ = "SELECT * from " + "currentJob" ;
            Cursor cursor__ = jobs_database.rawQuery (queryCommandString__, null);
//        List returnList__= new ArrayList();
            while(cursor__.moveToNext()) {
                String jobTitle = cursor__.getString(0);
                String company = cursor__.getString(1);
                String locationCity = cursor__.getString(2);
                String locationState = cursor__.getString(3);
                String costLivingIndex = cursor__.getString(4);
                String yearlySalary = cursor__.getString(5);
                String yearlyBonus = cursor__.getString(6);
                String weeklyTelework = cursor__.getString(7);
                String leaveTime = cursor__.getString(8);
                String numCompanyShares = cursor__.getString(9);

               // text_wtYearlySalary.setText(wtYearlySalary);
                text_jobTitle.setText(jobTitle);
                text_company.setText(company);
                text_locationCity.setText(locationCity);
                text_locationState.setText(locationState);
                text_costLivingIndex.setText(costLivingIndex);
                text_yearlySalary.setText(yearlySalary);
                text_yearlyBonus.setText(yearlyBonus);
                text_weeklyTelework.setText(weeklyTelework);
                text_leaveTime.setText(leaveTime);
                text_numCompanyShares.setText(numCompanyShares);
                //text_wtYearlyBonus.setText(wtYearlyBonus);
                ///text_wtAllowedTelework.setText(wtAllowedTelework);
                //text_wtLeaveTime.setText(wtLeaveTime);
                //text_wtSharesOffered.setText(wtSharesOffered);
            }
        }*/
    }

    public void save(View view) {

        boolean invalidFlag = false;
        boolean emptyFlag = false;
        String jobTitle = "";
        String company = "";
        String locationCity = "";
        String locationState = "";
        int costLivingIndex = 0;
        int yearlySalary = 0;
        int yearlyBonus = 0;
        int weeklyTelework = 0;
        int leaveTime = 0;
        int numCompanyShares = 0;

        jobTitle = text_jobTitle.getText().toString();
        if(jobTitle.isEmpty()){
            emptyFlag = true;
            text_jobTitle.setError("Job Title is missing");
        }
        company = text_company.getText().toString();
        if(company.isEmpty()){
            emptyFlag = true;
            text_company.setError("Company is missing");
        }
        locationCity = text_locationCity.getText().toString();
        if(locationCity.isEmpty()){
            emptyFlag = true;
            text_locationCity.setError("City is Missing");
        }
        locationState = text_locationState.getText().toString();
        if(locationState.isEmpty()){
            emptyFlag = true;
            text_locationState.setError("State is missing");
        }

        String tmp1 = text_costLivingIndex.getText().toString();
        if(tmp1.isEmpty()) {
            emptyFlag = true;
            text_costLivingIndex.setError("Cost of Living is missing");
        }
        else{
            costLivingIndex = Integer.parseInt(tmp1);
            if(costLivingIndex<0){
                invalidFlag = true;
                text_costLivingIndex.setError("invalid input");
            }
        }

        String tmp2 = text_yearlySalary.getText().toString();
        if(tmp2.isEmpty()) {
            emptyFlag = true;
            text_yearlySalary.setError("Yearly Salary is missing");
        }
        else{
            yearlySalary = Integer.parseInt(tmp2);
            if(yearlySalary<0){
                invalidFlag = true;
                text_yearlySalary.setError("invalid input");
            }
        }


        String tmp3 = text_yearlyBonus.getText().toString();
        if(tmp3.isEmpty()) {
            emptyFlag = true;
            text_yearlyBonus.setError("Yearly Bonus is missing");
        }
        else{
            yearlyBonus = Integer.parseInt(tmp3);
            if(yearlyBonus<0){
                invalidFlag = true;
                text_yearlyBonus.setError("invalid input");
            }
        }

        String tmp4 = text_weeklyTelework.getText().toString();
        if(tmp4.isEmpty()) {
            emptyFlag = true;
            text_weeklyTelework.setError("Weekly Telework is missing");
        }
        else{
            weeklyTelework = Integer.parseInt(tmp4);
            if(weeklyTelework<0 || weeklyTelework>5){
                invalidFlag = true;
                text_weeklyTelework.setError("invalid input");
            }
        }

        String tmp5 = text_leaveTime.getText().toString();
        if(tmp5.isEmpty()) {
            emptyFlag = true;
            text_leaveTime.setError("Days PTO is missing");
        }
        else{
            leaveTime = Integer.parseInt(tmp5);
            if(leaveTime<0){
                invalidFlag = true;
                text_leaveTime.setError("invalid input");
            }
        }

        String tmp6 = text_numCompanyShares.getText().toString();
        if(tmp6.isEmpty()) {
            emptyFlag = true;
            text_numCompanyShares.setError("Company Shares is missing");
        }
        else{
            numCompanyShares = Integer.parseInt(tmp6);
            if(numCompanyShares<0){
                invalidFlag = true;
                text_numCompanyShares.setError("invalid input");
            }
        }

        if(!emptyFlag && !invalidFlag){
            // Saving info in the database
            int currentJobFlag = 1;
            SQL_db jobs_database = new SQL_db (DisplayCurrentJob.this);
            jobs_database.addOne(jobTitle,
                    company,
                    locationCity,
                    locationState,
                    costLivingIndex,
                    yearlySalary,
                    yearlyBonus,
                    weeklyTelework,
                    leaveTime,
                    numCompanyShares,
                    currentJobFlag);

            Intent intent = new Intent(this, main_activity.class);
            startActivity(intent);

        }

    }

    public void cancel(View view) {
        Intent intent = new Intent(this, main_activity.class);
        startActivity(intent);
    }

}
