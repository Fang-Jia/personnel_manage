package com.xf.dao;

import com.xf.entity.Employee;

import java.util.List;

/**
 * 员工管理     数据持久层
 */
public interface EmplDao {
    List<Employee> selectEmpl();

    List<Employee> selectEmplByPage(int begin, int limit);

    void addEmplInfo(Employee employee);

    List<Employee> searchByName(String name);

    void delEmpl(Employee employee);

    Employee searchById(Integer eid);

    void editEmpl(Employee employee);
}
