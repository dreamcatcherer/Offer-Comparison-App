package edu.gatech.seclass.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.gatech.seclass.myapplication.entity.CurrentJob;
import edu.gatech.seclass.myapplication.entity.Item;
import edu.gatech.seclass.myapplication.entity.JobOffer;

public class DisplayScoringAndRanking extends AppCompatActivity {
    private ListView jobs_listview2;//text_prioritizedJobList;
    private int clickcount=0;
    String title1 , title2 ;
    String company1 , company2 ;
    String city1 , city2 ;
    String state1 , state2 ;
    String costOfLiving1 , costOfLiving2 ;
    String yearlySalary1 , yearlySalary2 ;
    String yearlyBonus1 , yearlyBonus2 ;
    String weeklyTelework1 , weeklyTelework2 ;
    String daysPTO1 , daysPTO2 ;
    String companyShares1 , companyShares2 ;
    List<Item> jobOffersList;
    List<String> returnList;
    TextView text_jobTitleTXT, text_jobTitleTXT2;
    TextView text_companyTXT, text_companyTXT2;
    TextView text_cityTXT, text_cityTXT2;
    TextView text_stateTXT, text_stateTXT2;
    TextView text_costOfLivingTXT, text_costOfLivingTXT2;
    TextView text_yearlySalaryTXT, text_yearlySalaryTXT2;
    TextView text_yearlyBonusTXT, text_yearlyBonusTXT2;
    TextView text_weeklyTeleworkTXT, text_weeklyTeleworkTXT2;
    TextView text_leaveTimeTXT, text_leaveTimeTXT2;
    TextView text_numCompanySharesTXT, text_numCompanySharesTXT2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_scoring_and_ranking);

        // here we need to print the list of job offers available

        ///////////////////////////////////////////////////////////////////////
        //Step01: read/open an existing database
        SQLiteDatabase jobs_database = SQLiteDatabase.openDatabase ("/data/data/edu.gatech.seclass.myapplication/databases/jobs_database10", null, SQLiteDatabase.OPEN_READWRITE);
/*        String queryCommandString = "SELECT * from " + TABLE_jobTitle + "WHERE jobTitle = '" + job +"'" ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery (queryCommandString, null);*/

        ///////////////////////////////////////////////////////////////////////
        //Step02: we need to display the data we have (job offer and company name)
        //read weights from the database:
        String queryCommandString__ = "SELECT * from " + "weights" ;
        Cursor cursor__ = jobs_database.rawQuery (queryCommandString__, null);
//        List returnList__= new ArrayList();
        double wt_AYS = 1.0;
        double wt_AYB = 1.0;
        double wt_RWT = 1.0;
        double wt_LT = 1.0;
        double wt_CSO = 1.0;
        double wt_total = 1.0;
        double score;//ToDo: Check if it should be a long
        while(cursor__.moveToNext()) {
            String tmp1 = cursor__.getString(0);
            wt_AYS = Integer.parseInt(tmp1);
            String tmp2 = cursor__.getString(1);
            wt_AYB = Integer.parseInt(tmp2);
            String tmp3 = cursor__.getString(2);
            wt_RWT = Integer.parseInt(tmp3);
            String tmp4 = cursor__.getString(3);
            wt_LT = Integer.parseInt(tmp4);
            String tmp5 = cursor__.getString(4);
            wt_CSO = Integer.parseInt(tmp5);
            wt_total = wt_AYS + wt_AYB + wt_RWT + wt_LT + wt_CSO;
        }
        jobOffersList = new ArrayList<>();
        returnList = new ArrayList<>();
        //first the current job:
        String queryCommandString_ = "SELECT * from " + "currentJob" ;
        Cursor cursor_ = jobs_database.rawQuery (queryCommandString_, null);
//        List returnList = new ArrayList();
//        List titleList = new ArrayList();
//        List companyList = new ArrayList();
//        List cityList = new ArrayList();
//        List stateList = new ArrayList();
//        List costOfLivingList = new ArrayList();
//        List yearlySalaryList = new ArrayList();
//        List yearlyBonusList = new ArrayList();
//        List weeklyTeleworkList = new ArrayList();
//        List daysPTOList = new ArrayList();
//        List companySharesList = new ArrayList();
        while(cursor_.moveToNext()) {
            String j_ = cursor_.getString(0);
            String c_ = cursor_.getString(1);
            String city_ = cursor_.getString(2);
            String state_ = cursor_.getString(3);
            String tmp1 = cursor_.getString(4);
            float costLivingIndex = Integer.parseInt(tmp1);
            String tmp2 = cursor_.getString(5);
            float yearlySalary = Integer.parseInt(tmp2);
            String tmp3 = cursor_.getString(6);
            float yearlyBonus = Integer.parseInt(tmp3);
            String tmp4 = cursor_.getString(7);
            float weeklyTelework = Integer.parseInt(tmp4);
            String tmp5 = cursor_.getString(8);
            float leaveTime = Integer.parseInt(tmp5);
            String tmp6 = cursor_.getString(9);
            float numCompanyShares = Integer.parseInt(tmp6);
            //https://gatech.instructure.com/courses/189462/external_tools/81

            float AYS = yearlySalary / costLivingIndex * 100;//ToDo: to be checked
            float AYB = yearlyBonus / costLivingIndex * 100;//ToDo: to be checked
            float CSO = numCompanyShares;
            float LT = leaveTime;
            float RWT = weeklyTelework;
            score =  wt_AYS/wt_total* AYS +
                        wt_AYB/wt_total* AYB +
                        wt_CSO/wt_total*CSO/4 +
                        wt_LT/wt_total*(LT*AYS/260) -
                        wt_RWT/wt_total*((260-52 * RWT)*(AYS/260)/8);

            CurrentJob currJob = new CurrentJob(-1, j_,c_,city_,state_,(int)costLivingIndex,(int)yearlySalary, (int)yearlyBonus, (int)weeklyTelework,(int)leaveTime,(int)numCompanyShares,score);
            jobOffersList.add(currJob);
//            String queryCommandString5 = "REPLACE * from " + "currentJob" ;
//            ContentValues cv = new ContentValues();
//            cv.put("score", score);
//            long insert = jobs_database.insert("currentJob", null, cv);

//            returnList.add("CURRENT JOB: "+j_ + '\t' + '\t' + '\t' + '\t' + '\t' + '\t' + c_ );//+ "(Score=" + score + ")");
//            returnList.add("CURRENT JOB: "+j_ + '\n' + c_ );//+ "(Score=" + score + ")");
            //returnList.add("CURRENT JOB: "+j_ + "   (Company:" + c_ +")");//+ "(Score=" + score + ")");
//            titleList.add(j_);
//            companyList.add(c_);
//            cityList.add(city_);
//            stateList.add(state_);
//            costOfLivingList.add(costLivingIndex);
//            yearlySalaryList.add(yearlySalary);
//            yearlyBonusList.add(yearlyBonus);
//            weeklyTeleworkList.add(weeklyTelework);
//            daysPTOList.add(leaveTime);
//            companySharesList.add(numCompanyShares);

        }

        //then the offers
        String queryCommandString = "SELECT * from " + "jobOffers" ;
        Cursor cursor = jobs_database.rawQuery (queryCommandString, null);
        //List returnList = new ArrayList();
        while(cursor.moveToNext()) {
            String j = cursor.getString(0);
            String c = cursor.getString(1);
            String city = cursor.getString(2);
            String state = cursor.getString(3);
            String tmp1 = cursor.getString(4);
            float costLivingIndex = Integer.parseInt(tmp1);
            String tmp2 = cursor.getString(5);
            float yearlySalary = Integer.parseInt(tmp2);
            String tmp3 = cursor.getString(6);
            float yearlyBonus = Integer.parseInt(tmp3);
            String tmp4 = cursor.getString(7);
            float weeklyTelework = Integer.parseInt(tmp4);
            String tmp5 = cursor.getString(8);
            float leaveTime = Integer.parseInt(tmp5);
            String tmp6 = cursor.getString(9);
            float numCompanyShares = Integer.parseInt(tmp6);

            float AYS = yearlySalary / costLivingIndex * 100;//ToDo: to be checked
            float AYB = yearlyBonus / costLivingIndex * 100;//ToDo: to be checked
            float CSO = numCompanyShares;
            float LT = leaveTime;
            float RWT = weeklyTelework;
            double score1 =  wt_AYS/wt_total* AYS +
                    wt_AYB/wt_total* AYB +
                    wt_CSO/wt_total*CSO/4 +
                    wt_LT/wt_total*(LT*AYS/260) -
                    wt_RWT/wt_total*((260-52 * RWT)*(AYS/260)/8);
            //System.out.println(j);
//            List comb = ;
/*            returnList.add(j);
            returnList.add(c);*/
//            returnList.add( j + '\t' + '\t' + '\t' + '\t' + '\t' + '\t' + '\t' + '\t' + '\t' + '\t'+
//                                '\t' + '\t' + '\t' + '\t' + '\t' + '\t' + '\t' + '\t' + '\t' + '\t' + c);//+ "(Score=" + score + ")");
//            returnList.add("JOB OFFER: "+j + "   (Company:" + c +")");//+ "(Score=" + score + ")");
//            titleList.add(j);
//            companyList.add(c);
//            cityList.add(city);
//            stateList.add(state);
//            costOfLivingList.add(costLivingIndex);
//            yearlySalaryList.add(yearlySalary);
//            yearlyBonusList.add(yearlyBonus);
//            weeklyTeleworkList.add(weeklyTelework);
//            daysPTOList.add(leaveTime);
//            companySharesList.add(numCompanyShares);
            JobOffer jobOffer = new JobOffer(-1, j,c,city,state,(int)costLivingIndex,(int)yearlySalary, (int)yearlyBonus, (int)weeklyTelework,(int)leaveTime,(int)numCompanyShares,score1);
            jobOffersList.add(jobOffer);
        }
        Collections.sort(jobOffersList, Collections.reverseOrder());
        for(int i=0; i<jobOffersList.size();i++){
            Item job = jobOffersList.get(i);
            if(job.getClass() == CurrentJob.class){
                returnList.add("Current Job: "+job.getTitle() + " \t\t\t\t\t\t   (Company:" + job.getCompany() +")");//+ "(Score=" + job.getScore() + ")");
            }
            else{
                returnList.add("JOB OFFER: "+job.getTitle() + " \t\t\t\t\t\t   (Company:" + job.getCompany() +")");//+ "(Score=" + job.getScore() + ")");
            }
        }
        ArrayAdapter jobsArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, returnList);

        jobs_listview2 = findViewById(R.id.jobs_listview);////
        jobs_listview2.setAdapter(jobsArrayAdapter);
        //
        //////////Toast.makeText(DisplayScoringAndRanking.this, j,Toast.LENGTH_SHORT).show();

        /*        Context context = getBaseContext();//getApplicationContext();
        Toast toast = Toast.makeText(context, "here we go!",Toast.LENGTH_LONG)//.show();
        //toast.show();*/
        ///Toast.makeText(getBaseContext(), "Reason can not be blank", Toast.LENGTH_LONG).show();
        Toast.makeText(getBaseContext(), returnList.toString(), Toast.LENGTH_LONG).show();
        System.out.println(returnList.toString());

        ///////////////////////////////////////////////////////////////////////
        //Step03: Allow user to select two from the list and extract the info.
        //data = jobs_database.extractdata (String jobTitle);
        // We will get the "location" of the line that was "clicked"
        // Source: https://youtu.be/312RhjfetP8
        jobs_listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            //int i_jobs = 0;
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Note that only the first two "jobs" that you click on are used for comparison...
/*                i_jobs += 1;
                if (i_jobs ==1) {
                    int position1 = position;
                }else if (i_jobs ==2) {
                    int position2 = position;
                }*/
                clickcount += 1;
//                setContentView(R.layout.activity_display_compare_items);
//                setContentView(R.layout.activity_display_compare_items);

                if (clickcount ==1) {
                    Item job1 = jobOffersList.get(position);
                    title1 = job1.getTitle();
                    company1 = job1.getCompany();
                    city1 = job1.getLocationCity();
                    state1 = job1.getLocationState();
                    costOfLiving1 = job1.getCosOfLiving().toString();
                    yearlySalary1 = job1.getYearlySalary().toString();
                    yearlyBonus1 = job1.getYearlyBonus().toString();
                    weeklyTelework1 = job1.getAllowedWeeklyTelework().toString();
                    daysPTO1 = job1.getLeaveTime().toString();
                    companyShares1 = job1.getNumberCompanyShares().toString();
                }else if (clickcount ==2) {
                    Item job2 = jobOffersList.get(position);
                    title2 = job2.getTitle();
                    company2 = job2.getCompany();
                    city2 = job2.getLocationCity();
                    state2 = job2.getLocationState();
                    costOfLiving2 = job2.getCosOfLiving().toString();
                    yearlySalary2 = job2.getYearlySalary().toString();
                    yearlyBonus2 = job2.getYearlyBonus().toString();
                    weeklyTelework2 = job2.getAllowedWeeklyTelework().toString();
                    daysPTO2 = job2.getLeaveTime().toString();
                    companyShares2 = job2.getNumberCompanyShares().toString();
                }
/*                if (clickcount ==1) {
                    text_jobTitleTXT = findViewById(R.id.jobTitleTXT);
                    System.out.println(titleList.get(position).toString());
                    text_jobTitleTXT.setText(titleList.get(position).toString());//
                    System.out.println(titleList.get(position));*/
/*                }else if (clickcount ==2){
                    System.out.println(titleList.get(position).toString());
                    text_jobTitleTXT2 = findViewById(R.id.jobTitleTXT2);
                    text_jobTitleTXT2.setText(titleList.get(position).toString());//
                }*/
                System.out.println(parent.getItemAtPosition(position));
                System.out.println("position=0 --> means current job");
                System.out.println(position);
                //int clickedJob = (int) parent.getItemAtPosition(position);
                //System.out.println(clickedJob);
            }
        });

    }

    public void compare_jobs(View view) {
        Intent intent = new Intent(this, DisplayCompareItems.class);
        intent.putExtra("title1", title1);
        intent.putExtra("title2", title2);
        intent.putExtra("company1", company1);
        intent.putExtra("company2", company2);
        intent.putExtra("city1", city1);
        intent.putExtra("city2", city2);
        intent.putExtra("state1", state1);
        intent.putExtra("state2", state2);
        intent.putExtra("costOfLiving1", costOfLiving1);
        intent.putExtra("costOfLiving2", costOfLiving2);
        intent.putExtra("yearlySalary1", yearlySalary1);
        intent.putExtra("yearlySalary2", yearlySalary2);
        intent.putExtra("yearlyBonus1", yearlyBonus1);
        intent.putExtra("yearlyBonus2", yearlyBonus2);
        intent.putExtra("weeklyTelework1", weeklyTelework1);
        intent.putExtra("weeklyTelework2", weeklyTelework2);
        intent.putExtra("daysPTO1", daysPTO1);
        intent.putExtra("daysPTO2", daysPTO2);
        intent.putExtra("companyShares1", companyShares1);
        intent.putExtra("companyShares2", companyShares2);
        if(clickcount>=2) startActivity(intent);
        clickcount=0;


//        View newView = View.inflate(this, R.layout.activity_display_compare_items, null);
//        //setContentView(R.layout.activity_display_compare_items);
//        setContentView(newView);
//        text_jobTitleTXT = findViewById(R.id.jobTitleTXT);
//        text_jobTitleTXT.setText(title1);
//        text_jobTitleTXT2 = findViewById(R.id.jobTitleTXT2);
//        text_jobTitleTXT2.setText(title2);
//
//        text_companyTXT = findViewById(R.id.companyTXT);
//        text_companyTXT.setText(company1);
//        text_companyTXT2 = findViewById(R.id.companyTXT2);
//        text_companyTXT2.setText(company2);
//
//        text_cityTXT = findViewById(R.id.cityTXT);
//        text_cityTXT.setText(city1);
//        text_cityTXT2 = findViewById(R.id.cityTXT2);
//        text_cityTXT2.setText(city2);
//
//        text_stateTXT = findViewById(R.id.weight4);
//        text_stateTXT.setText(state1);
//        text_stateTXT2 = findViewById(R.id.weight7);
//        text_stateTXT2.setText(state2);
//
//        text_costOfLivingTXT = findViewById(R.id.costOfLivingTXT);
//        text_costOfLivingTXT.setText(costOfLiving1);
//        text_costOfLivingTXT2 = findViewById(R.id.costOfLivingTXT2);
//        text_costOfLivingTXT2.setText(costOfLiving2);
//
//        text_yearlySalaryTXT = findViewById(R.id.yearlySalaryTxt);
//        text_yearlySalaryTXT.setText(yearlySalary1);
//        text_yearlySalaryTXT2 = findViewById(R.id.yearlySalaryTxt2);
//        text_yearlySalaryTXT2.setText(yearlySalary2);
//
//        text_yearlyBonusTXT = findViewById(R.id.yearlyBonusTXT);
//        text_yearlyBonusTXT.setText(yearlyBonus1);
//        text_yearlyBonusTXT2 = findViewById(R.id.yearlyBonusTXT2);
//        text_yearlyBonusTXT2.setText(yearlyBonus2);
//
//        text_weeklyTeleworkTXT = findViewById(R.id.weeklyTeleworkTXT);
//        text_weeklyTeleworkTXT.setText(weeklyTelework1);
//        text_weeklyTeleworkTXT2 = findViewById(R.id.weeklyTeleworkTXT2);
//        text_weeklyTeleworkTXT2.setText(weeklyTelework2);
//
//        text_leaveTimeTXT = findViewById(R.id.leaveTimeTXT);
//        text_leaveTimeTXT.setText(daysPTO1);
//        text_leaveTimeTXT2 = findViewById(R.id.leaveTimeTXT2);
//        text_leaveTimeTXT2.setText(daysPTO2);
//
//        text_numCompanySharesTXT = findViewById(R.id.numCompanySharesTXT);
//        text_numCompanySharesTXT.setText(companyShares1);
//        text_numCompanySharesTXT2 = findViewById(R.id.numCompanySharesTXT2);
//        text_numCompanySharesTXT2.setText(companyShares2);

    }

    public void main_menu(View view) {
        Intent intent = new Intent(this, main_activity.class);
        startActivity(intent);
    }
}