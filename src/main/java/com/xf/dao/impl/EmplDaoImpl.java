package com.xf.dao.impl;

import com.xf.dao.EmplDao;
import com.xf.entity.Department;
import com.xf.entity.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.util.List;

/**
 * 员工管理     数据持久层实现类
 */
public class EmplDaoImpl implements EmplDao {

//    注入HibernateTemplate模板
    private HibernateTemplate hibernateTemplate;
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) { this.hibernateTemplate = hibernateTemplate; }

    /**
     * 查询库中所有员工的信息
     */
    @Override
    public List<Employee> selectEmpl() {
        return (List<Employee>) hibernateTemplate.find("from Employee ");
    }

    /**
     * 根据分页查询数据库员工信息
     */
    @Override
    public List<Employee> selectEmplByPage(final int begin, final int limit) {
        List<Employee> list = hibernateTemplate.execute(new HibernateCallback<List<Employee>>() {
            @Override
            public List<Employee> doInHibernate(Session session) throws HibernateException {
                List list = session.createCriteria(Employee.class).setFirstResult(begin).setMaxResults(limit).list();
                return list;
            }
        });
        return list;
    }

    /**
     * 添加员工信息
     */
    @Override
    public void addEmplInfo(Employee employee) {
//        根据该员工的所属部门获取该部门信息
        List<Department> list = (List<Department>) hibernateTemplate.find("from Department where name=?",employee.getDept_name());
        Department department = list.get(0);
//        向员工所属部门属性中设置部门对象
        employee.setDepartment(department);

        hibernateTemplate.save(employee);
    }

    /**
     * 通过名称搜索员工信息
     */
    @Override
    public List<Employee> searchByName(String name) {
        String likeName = "%" + name + "%";
        return (List<Employee>) hibernateTemplate.find("from Employee where name like ?",likeName);
    }

    /**
     * 删除员工信息
     */
    @Override
    public void delEmpl(Employee employee) {
        hibernateTemplate.delete(employee);
    }

    /**
     * 通过ID搜索员工信息
     */
    @Override
    public Employee searchById(Integer eid) {
        return hibernateTemplate.get(Employee.class,eid);
    }

    /**
     * 编辑员工信息
     */
    @Override
    public void editEmpl(Employee employee) {
        Employee employee1 = hibernateTemplate.get(employee.getClass(),employee.getEid());
        employee.setDepartment(employee1.getDepartment());
        hibernateTemplate.merge(employee);
    }
}
