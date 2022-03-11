package edu.gatech.seclass.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;


public class SQL_db extends SQLiteOpenHelper {
    //create a SQLite database
    // code source: https://developer.android.com/training/data-storage/sqlite
    // code source: https://youtu.be/aQAIMY-HzL8
    // code source: https://youtu.be/312RhjfetP8

    public static final String TABLE_jobTitle = "jobOffers";
    public static final String Col_jobTitle = "jobTitle";
    public static final String Col_company = "company";
    public static final String Col_locationCity = "locationCity";
    public static final String Col_locationState = "locationState";
    public static final String Col_wtSharesOffered = "wtSharesOffered";
    public static final String Col_yearlySalary = "yearlySalary";
    public static final String Col_yearlyBonus = "yearlyBonus";
    public static final String Col_weeklyTelework  = "weeklyTelework ";
    public static final String Col_leaveTime = "leaveTime";
    public static final String Col_numCompanyShares = "numCompanyShares";
    public static final String Col_wtYearlySalary = "wtYearlySalary";
    public static final String Col_wtYearlyBonus = "wtYearlyBonus";
    public static final String Col_wtAllowedTelework = "wtAllowedTelework";
    public static final String Col_wtLeaveTime = "wtLeaveTime";
    public static final String Col_score = "score";
    ///public static final String Col_wtSharesOffered = "wtSharesOffered";

    public static long insert;

    // This is the constructor that defines the database name. There are 3 types of constructors that can be used...
    public SQL_db(@Nullable Context context) {
        super(context, "jobs_database10", null, 1);//will get "Context" from the code and hard code the other 3 variables
    }


    @Override
    // Called first time to open a database when called
    public void onCreate(SQLiteDatabase db) {

        // The following is similar to SQL commands
        String Command_to_Create_Table = "CREATE TABLE " + TABLE_jobTitle
                                         + " (" + Col_jobTitle + " TEXT, "
                                         + Col_company + " TEXT, "
                                         + Col_locationCity + " TEXT, "
                                         + Col_locationState + " TEXT, "
                                         + Col_wtSharesOffered + " INTEGER, "
                                         + Col_yearlySalary + " INTEGER, "
                                         + Col_yearlyBonus + " INTEGER, "
                                         + Col_weeklyTelework + " INTEGER, "
                                         + Col_leaveTime + " INTEGER, "
                                         + Col_numCompanyShares + " INTEGER, "
                                         + Col_score + " INTEGER"
                                         +")";
/*        String Command_to_Create_Table = "CREATE TABLE " + TABLE_jobTitle
                + " (" + Col_company + " STRING PRIMARY KEY AUTOINCREMENT, "
                + Col_locationCity + " TEXT, "
                + Col_locationState + " TEXT )";*/
        // now generate the database
        db.execSQL(Command_to_Create_Table);

        String Command_to_Create_Table2 = "CREATE TABLE " + "currentJob"
                + " (" + Col_jobTitle + " TEXT, "
                + Col_company + " TEXT, "
                + Col_locationCity + " TEXT, "
                + Col_locationState + " TEXT, "
                + Col_wtSharesOffered + " INTEGER, "
                + Col_yearlySalary + " INTEGER, "
                + Col_yearlyBonus + " INTEGER, "
                + Col_weeklyTelework + " INTEGER, "
                + Col_leaveTime + " INTEGER, "
                + Col_numCompanyShares + " INTEGER, "
                + Col_score + " INTEGER"
                +")";

        db.execSQL(Command_to_Create_Table2);

        String Command_to_Create_Table3 = "CREATE TABLE " + "weights"
                + " ("
                + Col_wtYearlySalary + " INTEGER, "
                + Col_wtYearlyBonus + " INTEGER, "
                + Col_wtAllowedTelework+ " INTEGER, "
                + Col_wtLeaveTime + " INTEGER, "
                + Col_wtSharesOffered + " INTEGER "
                +")";

        db.execSQL(Command_to_Create_Table3);

        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Col_wtYearlySalary, 1);
        cv.put(Col_wtYearlyBonus, 1);
        cv.put(Col_wtAllowedTelework, 1);
        cv.put(Col_wtLeaveTime, 1);
        cv.put(Col_wtSharesOffered , 1);

        long insert = db.insert("weights", null, cv);
    }

    @Override
    // Called when update the database/change the shape
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public boolean addOne (String jobTitle,
                           String company,
                           String locationCity,
                           String locationState,
                           Integer wtSharesOffered,
                           Integer yearlySalary,
                           Integer yearlyBonus,
                           Integer weeklyTelework,
                           Integer leaveTime,
                           Integer numCompanyShares,
                           Integer currentJobFlag){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(Col_jobTitle, jobTitle);
        cv.put(Col_company, company);
        cv.put(Col_locationCity, locationCity);
        cv.put(Col_locationState, locationState);
        cv.put(Col_wtSharesOffered, wtSharesOffered);
        cv.put(Col_yearlySalary, yearlySalary);
        cv.put(Col_yearlyBonus, yearlyBonus);
        cv.put(Col_weeklyTelework, weeklyTelework);
        cv.put(Col_leaveTime, leaveTime);
        cv.put(Col_numCompanyShares, numCompanyShares);
        cv.put(Col_score, -999);


        if (currentJobFlag == 1) {
            // Delete current job info to replace it with the fresh ones
            String Command_to_Create_Table3 = "DELETE FROM currentJob";
            db.execSQL(Command_to_Create_Table3);
            long insert = db.insert("currentJob", null, cv);
        } else {
            long insert = db.insert(TABLE_jobTitle, null, cv);
        }
        //long insert = db.insert(TABLE_jobTitle, null, cv);

        if (insert == -1) {
            return false;
        }else{
            return true;
        }
    }

    public boolean addWeights (Integer wtYearlySalary,
                               Integer wtYearlyBonus,
                               Integer wtAllowedTelework,
                               Integer wtLeaveTime,
                               Integer wtSharesOffered){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(Col_wtYearlySalary, wtYearlySalary);
        cv.put(Col_wtYearlyBonus, wtYearlyBonus);
        cv.put(Col_wtAllowedTelework, wtAllowedTelework);
        cv.put(Col_wtLeaveTime, wtLeaveTime);
        cv.put(Col_wtSharesOffered , wtSharesOffered);

        String Command_to_Create_Table3 = "DELETE FROM weights";
        db.execSQL(Command_to_Create_Table3);
        long insert = db.insert("weights", null, cv);

        if (insert == -1) {
            return false;
        }else{
            return true;
        }
    }

    // Initiate a method that return certain data from a database
    public Cursor extractdata (String job){
        //List<> extractdata (String job){
        //List<> job_info = new ArrayList<>();
        String queryCommandString = "SELECT * from " + TABLE_jobTitle + "WHERE jobTitle = '" + job +"'" ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery (queryCommandString, null);
        //String jobTitle = cursor.getString(0);// jobTitle is the first column in the database
        //String company = cursor.getString(1);

        return cursor;
    }

}





