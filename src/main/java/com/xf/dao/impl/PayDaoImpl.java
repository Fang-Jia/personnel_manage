package com.xf.dao.impl;

import com.xf.dao.PayDao;
import com.xf.entity.Pay;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.util.List;


/**
 * 薪酬管理     数据持久层实现类
 */
public class PayDaoImpl implements PayDao {

    private HibernateTemplate hibernateTemplate;

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    /**
     * 获取薪酬信息
     */
    @Override
    public List<Pay> selectPay() {
        return (List<Pay>) hibernateTemplate.find("from Pay ");
    }

    /**
     * 获取薪酬信息      分页
     */
    @Override
    public List<Pay> selectPayByPage(final int begin, final int limit) {
        List<Pay> list = hibernateTemplate.execute(new HibernateCallback<List<Pay>>() {
            @Override
            public List<Pay> doInHibernate(Session session) throws HibernateException {
                List list = session.createCriteria(Pay.class).setFirstResult(begin).setMaxResults(limit).addOrder(Order.desc("monthPay")).list();
                return list;
            }
        });
        return list;
    }

    /**
     * 删除薪酬信息
     */
    @Override
    public void delPay(Integer pid) {
        Pay pay = hibernateTemplate.get(Pay.class,pid);
        hibernateTemplate.delete(pay);
    }

    /**
     * 添加薪酬信息
     */
    @Override
    public void addPayInfo(Pay pay) {
        hibernateTemplate.save(pay);
    }

    /**
     * 通过名称搜索薪酬信息
     */
    @Override
    public List<Pay> searchByName(String name) {
        String likeName = "%" + name + "%";
        return (List<Pay>) hibernateTemplate.find("from Pay where name like ?",likeName);
    }

    /**
     * 通过ID搜索薪酬信息
     */
    @Override
    public Pay searchById(Integer pid) {
        return hibernateTemplate.get(Pay.class,pid);
    }

    /**
     * 编辑薪酬信息
     */
    @Override
    public void editPay(Pay pay) {
        hibernateTemplate.update(pay);
    }
}
