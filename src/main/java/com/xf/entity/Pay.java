package com.xf.entity;

/**
 * 薪酬管理     对应数据库数据实体类
 */
public class Pay {
//    薪酬ID
    private Integer pid;
//    员工姓名
    private String name;
//    基本工资
    private int monthPay;
//    绩效工资
    private double performance;
//    伙食补贴
    private double foodAllowance ;
//    通讯补贴
    private double phoneAllowance;
//    交通补贴
    private  double travelAllowance;
//    津贴
    private double allowance;
//    加班工资
    private double overtimeAllowance;
//    迟到早退扣款
    private double reduceAllowance;
//    事假扣款
    private double casualReduceAllowance;
//    个人所得税
    private double personnelTax;
//    合计
    private double total;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getPersonnelTax() {
        return personnelTax;
    }

    public void setPersonnelTax(double personnelTax) {
        this.personnelTax = personnelTax;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMonthPay() {
        return monthPay;
    }

    public void setMonthPay(int monthPay) {
        this.monthPay = monthPay;
    }

    public double getPerformance() {
        return performance;
    }

    public void setPerformance(double performance) {
        this.performance = performance;
    }

    public double getFoodAllowance() {
        return foodAllowance;
    }

    public void setFoodAllowance(double foodAllowance) {
        this.foodAllowance = foodAllowance;
    }

    public double getPhoneAllowance() {
        return phoneAllowance;
    }

    public void setPhoneAllowance(double phoneAllowance) {
        this.phoneAllowance = phoneAllowance;
    }

    public double getTravelAllowance() {
        return travelAllowance;
    }

    public void setTravelAllowance(double travelAllowance) {
        this.travelAllowance = travelAllowance;
    }

    public double getAllowance() {
        return allowance;
    }

    public void setAllowance(double allowance) {
        this.allowance = allowance;
    }

    public double getOvertimeAllowance() {
        return overtimeAllowance;
    }

    public void setOvertimeAllowance(double overtimeAllowance) {
        this.overtimeAllowance = overtimeAllowance;
    }

    public double getReduceAllowance() {
        return reduceAllowance;
    }

    public void setReduceAllowance(double reduceAllowance) {
        this.reduceAllowance = reduceAllowance;
    }

    public double getCasualReduceAllowance() {
        return casualReduceAllowance;
    }

    public void setCasualReduceAllowance(double casualReduceAllowance) {
        this.casualReduceAllowance = casualReduceAllowance;
    }
}
