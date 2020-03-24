package com.xf.service;

import com.xf.dao.DeptDao;
import com.xf.entity.Department;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 部门   业务层
 */
@Transactional
public class DeptService {

    private DeptDao deptDao;

    public void setDeptDao(DeptDao deptDao) {
        this.deptDao = deptDao;
    }

    /**
     * 获取部门信息
     * @return
     */
    public List<Department> getDeptInfo() {
        return deptDao.selectDept();
    }

    /**
     * 按照分页获取部门信息
     * @param page
     * @param limit
     * @return
     */
    public List<Department> getPageInfo(int page, int limit) {
        //        计算分页的开始，和每页显示的条数
        int begin = 0;
        if (page > 1){
            begin = (page-1) * limit;
        }
        return deptDao.selectDeptByPage(begin,limit);
    }

    /**
     * 删除部门
     * @param department
     */
    public void delDept(int department) {
        deptDao.delDept(department);
    }

    /**
     * 添加部门
     * @param department
     */
    public void addDept(Department department) {
        deptDao.addDept(department);
    }

    /**
     * 按照部门姓名搜索部门
     * @param name
     * @return
     */
    public List<Department> search(String name) {
        return deptDao.searchDept(name);
    }

    /**
     * 按照部门ID搜索信息
     * @param did
     * @return
     */
    public Department searchById(Integer did) {
        return deptDao.searchById(did);
    }

    /**
     * 编辑部门的信息
     * @param department
     */
    public void editDept(Department department) {
        deptDao.editDept(department);
    }

}
