package ca.aec2018.project.model;

import java.util.ArrayList;

public class Wind implements Renewable{
    private Integer id;

    private Coordinate coordinate;

    private Double annualAverage;

    private Double january;
    private Double february;
    private Double march;
    private Double april;
    private Double may;
    private Double june;
    private Double july;
    private Double august;
    private Double september;
    private Double october;
    private Double november;
    private Double december;

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Double getJanuary() {
        return january;
    }

    public void setJanuary(Double january) {
        this.january = january;
    }

    public Double getFebruary() {
        return february;
    }

    public void setFebruary(Double february) {
        this.february = february;
    }

    public Double getMarch() {
        return march;
    }

    public void setMarch(Double march) {
        this.march = march;
    }

    public Double getApril() {
        return april;
    }

    public void setApril(Double april) {
        this.april = april;
    }

    public Double getMay() {
        return may;
    }

    public void setMay(Double may) {
        this.may = may;
    }

    public Double getJune() {
        return june;
    }

    public void setJune(Double june) {
        this.june = june;
    }

    public Double getJuly() {
        return july;
    }

    public void setJuly(Double july) {
        this.july = july;
    }

    public Double getAugust() {
        return august;
    }

    public void setAugust(Double august) {
        this.august = august;
    }

    public Double getSeptember() {
        return september;
    }

    public void setSeptember(Double september) {
        this.september = september;
    }

    public Double getOctober() {
        return october;
    }

    public void setOctober(Double october) {
        this.october = october;
    }

    public Double getNovember() {
        return november;
    }

    public void setNovember(Double november) {
        this.november = november;
    }

    public Double getDecember() {
        return december;
    }

    public void setDecember(Double december) {
        this.december = december;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAnnualAverage() {
        return annualAverage;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setAnnualAverage(Double annualAverage) {
        this.annualAverage = annualAverage;
    }

    @Override
    public ArrayList<Double> getMonthValues(){

        ArrayList<Double> monthList = new ArrayList<Double>();
        monthList.add(january);
        monthList.add(february);
        monthList.add(march);
        monthList.add(april);
        monthList.add(june);
        monthList.add(july);
        monthList.add(august);
        monthList.add(september);
        monthList.add(october);
        monthList.add(november);
        monthList.add(december);

        return monthList;
    }
}
