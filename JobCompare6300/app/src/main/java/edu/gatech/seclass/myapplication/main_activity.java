package edu.gatech.seclass.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import java.io.File;

public class main_activity extends AppCompatActivity {

    int count_offers = 0;
    int count_current = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void go_to_current_job(View view) {
        Intent intent = new Intent(this, DisplayCurrentJob.class);
        startActivity(intent);
    }

    public void go_to_job_offer(View view) {
        Intent intent = new Intent(this, DisplayJobOffer.class);
        startActivity(intent);
    }

    public void go_to_compare_jobs(View view) {
        if(count_current>=1|| count_offers>=2) {

            Intent intent = new Intent(this, DisplayScoringAndRanking.class);
            startActivity(intent);
        }

        else Toast.makeText(getBaseContext(), "Not Enough Job Offers", Toast.LENGTH_LONG).show();

    }

    public void go_to_comparison_settings(View view) {

            Intent intent = new Intent(this, DisplayComparisonSettings.class);
            startActivity(intent);

    }


}