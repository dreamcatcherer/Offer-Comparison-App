package edu.gatech.seclass.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DisplayCompareItems extends AppCompatActivity {
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
        setContentView(R.layout.activity_display_compare_items);
        Bundle extras = getIntent().getExtras();
        text_jobTitleTXT = findViewById(R.id.jobTitleTXT);
        text_jobTitleTXT.setText(extras.getString("title1"));
        text_jobTitleTXT2 = findViewById(R.id.jobTitleTXT2);
        text_jobTitleTXT2.setText(extras.getString("title2"));

        text_companyTXT = findViewById(R.id.companyTXT);
        text_companyTXT.setText(extras.getString("company1"));
        text_companyTXT2 = findViewById(R.id.companyTXT2);
        text_companyTXT2.setText(extras.getString("company2"));

        text_cityTXT = findViewById(R.id.cityTXT);
        text_cityTXT.setText(extras.getString("city1"));
        text_cityTXT2 = findViewById(R.id.cityTXT2);
        text_cityTXT2.setText(extras.getString("city2"));

        text_stateTXT = findViewById(R.id.weight4);
        text_stateTXT.setText(extras.getString("state1"));
        text_stateTXT2 = findViewById(R.id.weight7);
        text_stateTXT2.setText(extras.getString("state2"));

        text_costOfLivingTXT = findViewById(R.id.costOfLivingTXT);
        text_costOfLivingTXT.setText(extras.getString("costOfLiving1"));
        text_costOfLivingTXT2 = findViewById(R.id.costOfLivingTXT2);
        text_costOfLivingTXT2.setText(extras.getString("costOfLiving2"));

        text_yearlySalaryTXT = findViewById(R.id.yearlySalaryTxt);
        text_yearlySalaryTXT.setText(extras.getString("yearlySalary1"));
        text_yearlySalaryTXT2 = findViewById(R.id.yearlySalaryTxt2);
        text_yearlySalaryTXT2.setText(extras.getString("yearlySalary2"));

        text_yearlyBonusTXT = findViewById(R.id.yearlyBonusTXT);
        text_yearlyBonusTXT.setText(extras.getString("yearlyBonus1"));
        text_yearlyBonusTXT2 = findViewById(R.id.yearlyBonusTXT2);
        text_yearlyBonusTXT2.setText(extras.getString("yearlyBonus2"));

        text_weeklyTeleworkTXT = findViewById(R.id.weeklyTeleworkTXT);
        text_weeklyTeleworkTXT.setText(extras.getString("weeklyTelework1"));
        text_weeklyTeleworkTXT2 = findViewById(R.id.weeklyTeleworkTXT2);
        text_weeklyTeleworkTXT2.setText(extras.getString("weeklyTelework2"));

        text_leaveTimeTXT = findViewById(R.id.leaveTimeTXT);
        text_leaveTimeTXT.setText(extras.getString("daysPTO1"));
        text_leaveTimeTXT2 = findViewById(R.id.leaveTimeTXT2);
        text_leaveTimeTXT2.setText(extras.getString("daysPTO2"));

        text_numCompanySharesTXT = findViewById(R.id.numCompanySharesTXT);
        text_numCompanySharesTXT.setText(extras.getString("companyShares1"));
        text_numCompanySharesTXT2 = findViewById(R.id.numCompanySharesTXT2);
        text_numCompanySharesTXT2.setText(extras.getString("companyShares2"));
    }

    public void back_to_offers(View view) {
        //Intent intent = new Intent(this, DisplayScoringAndRanking.class);
        //startActivity(intent);
        finish();
    }

    public void main_menu(View view) {
        Intent intent = new Intent(this, main_activity.class);
        startActivity(intent);
    }
}