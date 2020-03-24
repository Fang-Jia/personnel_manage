package com.xf.entity;

import java.util.Date;

/**
 * 请假表  对应数据库信息 数据持久层
 */
public class Leave {
//    ID
    private Integer lid;
//    部门
    private String deptName;
//    姓名
    private String name;
//    申请日期
    private Date checkTime;
    private String checkTimeStr;

//    请假缘由
    private String reason;
//    起始请假时间
    private Date leaveTime;
    private String leaveTimeStr;

//    结束时间
    private Date endTime;
    private String endTimeStr;

//    累计天数
    private Integer totalDay;
//    请假类型
    private Integer leaveType;

    private String leaveTypeText;

//    审批状态  0--未审批  1--已审批  2--已拒绝
    private Integer status;

    private String statusText;

    public String getCheckTimeStr() {
        return checkTimeStr;
    }

    public void setCheckTimeStr(String checkTimeStr) {
        this.checkTimeStr = checkTimeStr;
    }

    public String getLeaveTimeStr() {
        return leaveTimeStr;
    }

    public void setLeaveTimeStr(String leaveTimeStr) {
        this.leaveTimeStr = leaveTimeStr;
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getLeaveTypeText() {
        return leaveTypeText;
    }

    public void setLeaveTypeText(String leaveTypeText) {
        this.leaveTypeText = leaveTypeText;
    }

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getTotalDay() {
        return totalDay;
    }

    public void setTotalDay(Integer totalDay) {
        this.totalDay = totalDay;
    }

    public Integer getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(Integer leaveType) {
        this.leaveType = leaveType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
