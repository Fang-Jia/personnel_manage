package com.xf.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 部门实体类    对应部门表
 */
public class Department {
//    部门Id
    private Integer did;

//    部门名称
    private String name;

//    部门描述
    private String description;

//    部门领导
    private String leader;

//    部门员工
    private Set<Employee> employeeSet = new HashSet<>();

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public Set<Employee> getEmployeeSet() {
        return employeeSet;
    }

    public void setEmployeeSet(Set<Employee> employeeSet) {
        this.employeeSet = employeeSet;
    }
}
