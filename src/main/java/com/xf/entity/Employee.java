package com.xf.entity;

import java.util.Date;

/**
 * 员工实体类    对应员工表
 */
public class Employee {
//    员工ID
    private Integer eid;

//    员工姓名
    private String name;

//    员工性别
    private Integer sex;
    private String sexStr;

//    员工年龄
    private Integer age;

//    员工职位
    private String position;

//    入职时间
    private Date entryTime;
    private String entryTimeStr;

//    出生年月
    private Date dateOfBirth;
    private String dateOfBirthStr;

//    学历
    private String education;

//    毕业院校
    private String graduateSchool;

//    专业
    private String specialty;

//    毕业时间
    private Date graduateTime;
    private String graduateTimeStr;

//    证件信息
    private String idCard;

//    联系方式
    private String phone;

//    邮箱
    private String email;

//    省份
    private String province;

//    地市
    private String cities;

//    县区
    private String county;

//    是否已婚
    private Integer marry;
    private String marryStr;

//    子女是否上学
    private Integer childToStudy;
    private String childToStudyStr;

//    员工所属部门
    private Department department;

//    员工所属部门名称
    private String dept_name;

//    员工所属部门的领导
    private String dept_leader;

    public String getSexStr() {
        return sexStr;
    }

    public void setSexStr(String sexStr) {
        this.sexStr = sexStr;
    }

    public String getMarryStr() {
        return marryStr;
    }

    public void setMarryStr(String marryStr) {
        this.marryStr = marryStr;
    }

    public String getChildToStudyStr() {
        return childToStudyStr;
    }

    public void setChildToStudyStr(String childToStudyStr) {
        this.childToStudyStr = childToStudyStr;
    }

    public String getEntryTimeStr() {
        return entryTimeStr;
    }

    public void setEntryTimeStr(String entryTimeStr) {
        this.entryTimeStr = entryTimeStr;
    }

    public String getDateOfBirthStr() {
        return dateOfBirthStr;
    }

    public void setDateOfBirthStr(String dateOfBirthStr) {
        this.dateOfBirthStr = dateOfBirthStr;
    }

    public String getGraduateTimeStr() {
        return graduateTimeStr;
    }

    public void setGraduateTimeStr(String graduateTimeStr) {
        this.graduateTimeStr = graduateTimeStr;
    }

    public Integer getMarry() {
        return marry;
    }

    public void setMarry(Integer marry) {
        this.marry = marry;
    }

    public Integer getChildToStudy() {
        return childToStudy;
    }

    public void setChildToStudy(Integer childToStudy) {
        this.childToStudy = childToStudy;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getDept_leader() {
        return dept_leader;
    }

    public void setDept_leader(String dept_leader) {
        this.dept_leader = dept_leader;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getGraduateSchool() {
        return graduateSchool;
    }

    public void setGraduateSchool(String graduateSchool) {
        this.graduateSchool = graduateSchool;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Date getGraduateTime() {
        return graduateTime;
    }

    public void setGraduateTime(Date graduateTime) {
        this.graduateTime = graduateTime;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCities() {
        return cities;
    }

    public void setCities(String cities) {
        this.cities = cities;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
