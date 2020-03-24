package com.xf.dao;

import com.xf.entity.Department;

import java.util.List;

/**
 * 部门   数据访问层
 */
public interface DeptDao {

    List<Department> selectDept();

    List<Department> selectDeptByPage(int begin, int limit);

    void delDept(int department);

    void addDept(Department department);

    List<Department> searchDept(String name);

    Department searchById(Integer did);

    void editDept(Department department);
}
