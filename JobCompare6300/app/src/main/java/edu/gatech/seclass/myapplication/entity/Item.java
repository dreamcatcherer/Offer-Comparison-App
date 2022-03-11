package edu.gatech.seclass.myapplication.entity;

import java.util.Objects;

public abstract class Item implements Comparable<Item>{
    private int id;
    private String title;
    private String company;
    private String locationCity;
    private String locationState;
    private Integer cosOfLiving;
    private Integer yearlySalary;
    private Integer yearlyBonus;
    private Integer allowedWeeklyTelework;
    private Integer leaveTime;
    private Integer numberCompanyShares;
    private double score;

    public Item(int id, String title, String company, String locationCity, String locationState, Integer cosOfLiving, Integer yearlySalary, Integer yearlyBonus, Integer allowedWeeklyTelework, Integer leaveTime, Integer numberCompanyShares, double score) {
        this.id = id;
        this.title = title;
        this.company = company;
        this.locationCity = locationCity;
        this.locationState = locationState;
        this.cosOfLiving = cosOfLiving;
        this.yearlySalary = yearlySalary;
        this.yearlyBonus = yearlyBonus;
        this.allowedWeeklyTelework = allowedWeeklyTelework;
        this.leaveTime = leaveTime;
        this.numberCompanyShares = numberCompanyShares;
        this.score = score;
    }
    public int compareTo(Item item2){
        double score2 = item2.getScore();
        double difference = this.score-score2;
        if(difference>0) return 1;
        else if (difference==0) return 0;
        else return -1;
    }
    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", company='" + company + '\'' +
                ", locationCity='" + locationCity + '\'' +
                ", locationState='" + locationState + '\'' +
                ", cosOfLiving=" + cosOfLiving +
                ", yearlySalary=" + yearlySalary +
                ", yearlyBonus=" + yearlyBonus +
                ", allowedWeeklyTelework=" + allowedWeeklyTelework +
                ", leaveTime=" + leaveTime +
                ", numberCompanyShares=" + numberCompanyShares +
                ", score=" + score +
                '}';
    }

    public void save(){

    }

    public void edit(){

    }

    public void cancel(){

    }

    public void enterAnotherOffer(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id &&
                Double.compare(item.score, score) == 0 &&
                Objects.equals(title, item.title) &&
                Objects.equals(company, item.company) &&
                Objects.equals(locationCity, item.locationCity) &&
                Objects.equals(locationState, item.locationState) &&
                Objects.equals(cosOfLiving, item.cosOfLiving) &&
                Objects.equals(yearlySalary, item.yearlySalary) &&
                Objects.equals(yearlyBonus, item.yearlyBonus) &&
                Objects.equals(allowedWeeklyTelework, item.allowedWeeklyTelework) &&
                Objects.equals(leaveTime, item.leaveTime) &&
                Objects.equals(numberCompanyShares, item.numberCompanyShares);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, company, locationCity, locationState, cosOfLiving, yearlySalary, yearlyBonus, allowedWeeklyTelework, leaveTime, numberCompanyShares, score);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocationCity() {
        return locationCity;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    public Integer getCosOfLiving() {
        return cosOfLiving;
    }

    public void setCosOfLiving(Integer cosOfLiving) {
        this.cosOfLiving = cosOfLiving;
    }

    public Integer getYearlySalary() {
        return yearlySalary;
    }

    public void setYearlySalary(Integer yearlySalary) {
        this.yearlySalary = yearlySalary;
    }

    public Integer getYearlyBonus() {
        return yearlyBonus;
    }

    public void setYearlyBonus(Integer yearlyBonus) {
        this.yearlyBonus = yearlyBonus;
    }

    public Integer getAllowedWeeklyTelework() {
        return allowedWeeklyTelework;
    }

    public void setAllowedWeeklyTelework(Integer allowedWeeklyTelework) {
        this.allowedWeeklyTelework = allowedWeeklyTelework;
    }

    public Integer getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Integer leaveTime) {
        this.leaveTime = leaveTime;
    }

    public Integer getNumberCompanySahres() {
        return numberCompanyShares;
    }

    public void setNumberCompanySahres(Integer numberCompanyShares) {
        this.numberCompanyShares = numberCompanyShares;
    }
    public String getLocationState() {
        return locationState;
    }

    public void setLocationState(String locationState) {
        this.locationState = locationState;
    }

    public Integer getNumberCompanyShares() {
        return numberCompanyShares;
    }

    public void setNumberCompanyShares(Integer numberCompanyShares) {
        this.numberCompanyShares = numberCompanyShares;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
