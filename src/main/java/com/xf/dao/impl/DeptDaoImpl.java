package com.xf.dao.impl;

import com.xf.dao.DeptDao;
import com.xf.entity.Department;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.util.List;

/**
 * DeptDao实现类
 */
public class DeptDaoImpl implements DeptDao {
//  注入hibernateTemplate模板属性
    private HibernateTemplate hibernateTemplate;

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    /**
     * 查询部门信息
     */
    @Override
    public List<Department> selectDept() {
        return (List<Department>) hibernateTemplate.find("from Department ");
    }

    /**
     * 查询部门信息   分页
     */
    @Override
    public List<Department> selectDeptByPage(final int begin, final int limit) {
        List<Department> list = hibernateTemplate.execute(new HibernateCallback<List<Department>>() {
            @Override
            public List<Department> doInHibernate(Session session) throws HibernateException {
                List list = session.createCriteria(Department.class).setFirstResult(begin).setMaxResults(limit).list();
                return list;
            }
        });
        return list;
    }

    /**
     * 删除部门
     */
    @Override
    public void delDept(int did) {
        Department department = hibernateTemplate.get(Department.class,did);
        hibernateTemplate.delete(department);
    }

    /**
     * 添加部门
     */
    @Override
    public void addDept(Department department) {
        hibernateTemplate.save(department);
    }

    /**
     * 通过名称搜索部门
     */
    @Override
    public List<Department> searchDept(String name) {
        String likeName = "%" + name + "%";
        return (List<Department>) hibernateTemplate.find("from Department where name like ?",likeName);
    }

    /**
     * 通过ID搜索部门信息
     */
    @Override
    public Department searchById(Integer did) {
        return hibernateTemplate.get(Department.class,did);
    }

    /**
     * 编辑部门信息
     */
    @Override
    public void editDept(Department department) {

        hibernateTemplate.update(department);
    }
}
