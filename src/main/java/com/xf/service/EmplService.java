package com.xf.service;

import com.xf.dao.EmplDao;
import com.xf.entity.Department;
import com.xf.entity.Employee;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 员工管理     业务层
 */
@Transactional
public class EmplService {

//    注入EmplDao属性
    private EmplDao emplDao;
    public void setEmplDao(EmplDao emplDao) { this.emplDao = emplDao; }


    /**
     * 获取员工信息
     */
    public List<Employee> getEmplInfo() {
        List<Employee> employeeList = emplDao.selectEmpl();
        replaceData(employeeList);
        return employeeList;
    }

    /**
     * 通过分页获取一个信息
     */
    public List<Employee> getPageInfo(int page, int limit) {
//        计算分页的开始，和每页显示的条数
        int begin = 0;
        if (page > 1){
            begin = (page-1) * limit;
        }
        List<Employee> employeeList = emplDao.selectEmplByPage(begin,limit);
        replaceData(employeeList);
        return employeeList;
    }

    /**
     * 添加员工信息
     */
    public void addEmplInfo(Employee employee) {
        emplDao.addEmplInfo(employee);
    }

    /**
     * 通过员工姓名搜索信息
     */
    public List<Employee> search(String name) {
        List<Employee> employeeList = emplDao.searchByName(name);
        replaceData(employeeList);
        return employeeList;
    }

    /**
     * 删除员工信息
     */
    public void delEmpl(Employee employee) {
        emplDao.delEmpl(employee);
    }

    /**
     * 通过ID搜索一个信息
     */
    public Employee searchById(Integer eid) {
        Employee employee = emplDao.searchById(eid);

        Department department = employee.getDepartment();
        employee.setDept_name(department.getName());
        employee.setDept_leader(department.getLeader());

        return employee;
    }

    /**
     * 封装一个用来转化数据库传递的数据
     */
    public static void replaceData(List<Employee> employeeList){
        for (Employee employee : employeeList){
//             转换时间格式
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            Date entry = employee.getEntryTime();
            Date graduate = employee.getGraduateTime();
            Date birthday = employee.getDateOfBirth();

            employee.setEntryTimeStr(formatter.format(entry));
            employee.setGraduateTimeStr(formatter.format(graduate));
            employee.setDateOfBirthStr(formatter.format(birthday));

//            转换性别数据
            if (employee.getSex() == 0){
                employee.setSexStr("男");
            }else {
                employee.setSexStr("女");
            }

            if (employee.getMarry() == 0){
                employee.setMarryStr("已婚");
            }else {
                employee.setMarryStr("未婚");
            }

            if (employee.getChildToStudy() == 0){
                employee.setChildToStudyStr("是");
            }else {
                employee.setChildToStudyStr("否");
            }

//            添加对应部门信息
            Department department = employee.getDepartment();
            employee.setDept_name(department.getName());
            employee.setDept_leader(department.getLeader());
        }

    }

    /**
     * 编辑员工信息
     */
    public void editEmpl(Employee employee) {
        
        emplDao.editEmpl(employee);
    }
}
