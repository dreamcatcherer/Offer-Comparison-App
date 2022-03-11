package edu.gatech.seclass.myapplication.entity;

public class CurrentJob extends Item{
    public CurrentJob(int id, String title, String company, String locationCity, String locationState, Integer cosOfLiving, Integer yearlySalary, Integer yearlyBonus, Integer allowedWeeklyTelework, Integer leaveTime, Integer numberCompanyShares, double score) {
        super(id, title, company, locationCity, locationState, cosOfLiving, yearlySalary, yearlyBonus, allowedWeeklyTelework, leaveTime, numberCompanyShares, score);
    }

    @Override
    public int compareTo(Item item2){
        double score2 = item2.getScore();
        double difference = this.getScore()-score2;
        if(difference>0) return 1;
        else if (difference==0) return 0;
        else return -1;
    }
}
