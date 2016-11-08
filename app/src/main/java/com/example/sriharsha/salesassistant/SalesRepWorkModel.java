package com.example.sriharsha.salesassistant;

public class SalesRepWorkModel {
    int _id;
    int salesRepId;
    double salesTargetAmount;
    int targetMonth;
    int targetYear;
    double salesAchievedAmount;

    public SalesRepWorkModel() {
    }

    public SalesRepWorkModel(int _id, int salesRepId, double salesTargetAmount, int targetMonth, int targetYear, double salesAchievedAmount) {
        this._id = _id;
        this.salesRepId = salesRepId;
        this.salesTargetAmount = salesTargetAmount;
        this.targetMonth = targetMonth;
        this.targetYear = targetYear;
        this.salesAchievedAmount = salesAchievedAmount;
    }

    public SalesRepWorkModel(int salesRepId, double salesTargetAmount, int targetMonth, int targetYear, double salesAchievedAmount) {
        this.salesRepId = salesRepId;
        this.salesTargetAmount = salesTargetAmount;
        this.targetMonth = targetMonth;
        this.targetYear = targetYear;
        this.salesAchievedAmount = salesAchievedAmount;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getSalesRepId() {
        return salesRepId;
    }

    public void setSalesRepId(int salesRepId) {
        this.salesRepId = salesRepId;
    }

    public double getSalesTargetAmount() {
        return salesTargetAmount;
    }

    public void setSalesTargetAmount(double salesTargetAmount) {
        this.salesTargetAmount = salesTargetAmount;
    }

    public int getTargetMonth() {
        return targetMonth;
    }

    public void setTargetMonth(int targetMonth) {
        this.targetMonth = targetMonth;
    }

    public int getTargetYear() {
        return targetYear;
    }

    public void setTargetYear(int targetYear) {
        this.targetYear = targetYear;
    }

    public double getsalesAchievedAmount() {
        return salesAchievedAmount;
    }

    public void setsalesAchievedAmount(double salesAchievedAmount) {
        this.salesAchievedAmount = salesAchievedAmount;
    }
}
