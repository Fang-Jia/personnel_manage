package com.xf.entity;

import java.util.Date;

/**
 * 补卡申请实体类  对应数据库补卡信息表  数据持久层
 */
public class Card {
//    ID
    private Integer cid;
//    姓名
    private String name;
//    部门
    private String deptName;

//    申请时间
    private Date checkTime;
    private String checkTimeStr;

//    未打卡日
    private Date cardTime;
    private String cardTimeStr;

//    未打卡原因
    private String reason;

//    申请人
    private String checkName;

//    部门主管
    private String deptLeader;

//    审批状态
    private Integer status;
    private String statusText;

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getCheckTimeStr() {
        return checkTimeStr;
    }

    public void setCheckTimeStr(String checkTimeStr) {
        this.checkTimeStr = checkTimeStr;
    }

    public Date getCardTime() {
        return cardTime;
    }

    public void setCardTime(Date cardTime) {
        this.cardTime = cardTime;
    }

    public String getCardTimeStr() {
        return cardTimeStr;
    }

    public void setCardTimeStr(String cardTimeStr) {
        this.cardTimeStr = cardTimeStr;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    public String getDeptLeader() {
        return deptLeader;
    }

    public void setDeptLeader(String deptLeader) {
        this.deptLeader = deptLeader;
    }
}
