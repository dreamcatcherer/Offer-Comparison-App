package edu.gatech.seclass.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class DisplayJobOffer extends AppCompatActivity {
    // Resource used: https://youtu.be/312RhjfetP8
    EditText text_jobTitle, text_company, text_locationCity, text_locationState, text_costLivingIndex, text_yearlySalary, text_yearlyBonus, text_weeklyTelework, text_leaveTime,  text_numCompanyShares;
    int count_offers = 0;
    int count_current = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_job_offer);

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
            //jobs_database.execSQL( "DELETE FROM " + "currentJob");
            //jobs_database.execSQL(" DELETE FROM " + "jobOffers");
            String queryCommandString__ = "SELECT * from " + "currentJob";
            Cursor cursor__ = jobs_database.rawQuery(queryCommandString__, null);
            while(cursor__.moveToNext()) count_current++;

            String queryCommandString_job = "SELECT * from " + "JobOffers";
            Cursor cursor_job = jobs_database.rawQuery(queryCommandString_job, null);
            while(cursor_job.moveToNext()) count_offers++;
            System.out.println("HHHHHH"+count_current);
            System.out.println("hhhhhhh"+count_offers);
        }
        catch (Exception e) {
            // database doesn't exist yet.
            File jobs_database10 = new File("/data/data/edu.gatech.seclass.myapplication/databases/");
        }
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

        // Creating a new database
        if(!emptyFlag && !invalidFlag){
            int currentJobFlag = 0;
            SQL_db jobs_database = new SQL_db (DisplayJobOffer.this);
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


        //SQL_db SQL_db = new SQL_db()
        ////// Should now save this info in a database table!!!
    }

    public void cancel(View view) {
        Intent intent = new Intent(this, main_activity.class);
        startActivity(intent);
    }

    public void enter_another(View view) {
        Intent intent = new Intent(this, DisplayJobOffer.class);
        startActivity(intent);
    }

    public void main_menu(View view) {
        Intent intent = new Intent(this, main_activity.class);
        startActivity(intent);
    }

    public void compare_jobs(View view) {

        if(count_current>=1|| count_offers>=2) {
            Intent intent = new Intent(this, DisplayScoringAndRanking.class);
            startActivity(intent);
        }
        else Toast.makeText(getBaseContext(), "Not Enough Job Offers", Toast.LENGTH_LONG).show();


    }
}
