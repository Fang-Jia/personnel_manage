package com.xf.dao.impl;

import com.xf.dao.LeaveDao;
import com.xf.entity.Leave;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.util.List;

/**
 * DeptDao实现类
 */
public class LeaveDaoImpl implements LeaveDao {
//  注入hibernateTemplate模板属性
    private HibernateTemplate hibernateTemplate;

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    /**
     * 添加请假信息
     */
    @Override
    public List<Leave> addInfo(Leave leave) {
        hibernateTemplate.save(leave);
        return (List<Leave>) hibernateTemplate.find("from Leave ");
    }

    /**
     * 查询请假信息
     */
    @Override
    public List<Leave> getLeaveInfo() {
        return (List<Leave>) hibernateTemplate.find("from Leave");
    }

    /**
     * 查询请假信息   分页
     */
    @Override
    public List<Leave> selectLeaveByPage(final int begin, final int limit) {
        List<Leave> list = hibernateTemplate.execute(new HibernateCallback<List<Leave>>() {
            @Override
            public List<Leave> doInHibernate(Session session) throws HibernateException {
                List list = session.createCriteria(Leave.class).setFirstResult(begin).setMaxResults(limit).addOrder(Order.asc("status")).addOrder(Order.desc("checkTime")).list();
                return list;
            }
        });
        return list;
    }

    /**
     * 同意审批
     */
    @Override
    public void agree(Integer lid) {
        Leave leave = hibernateTemplate.get(Leave.class,lid);
        leave.setStatus(1);
        hibernateTemplate.update(leave);
    }

    /**
     * 拒绝审批
     */
    @Override
    public void refuse(Integer lid) {
        Leave leave = hibernateTemplate.get(Leave.class,lid);
        leave.setStatus(2);
        hibernateTemplate.update(leave);
    }

    /**
     * 根据ID查询信息
     */
    @Override
    public List<Leave> detailInfo(Integer lid) {
        return (List<Leave>) hibernateTemplate.find("from Leave where lid = ?",lid);
    }
}
