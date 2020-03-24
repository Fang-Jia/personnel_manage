package com.xf.dao.impl;

import com.xf.dao.CardDao;
import com.xf.entity.Card;
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
public class CardDaoImpl implements CardDao {
//  注入hibernateTemplate模板属性
    private HibernateTemplate hibernateTemplate;

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    /**
     * 添加请假信息
     * @param card
     * @return
     */
    @Override
    public List<Card> addInfo(Card card) {
        hibernateTemplate.save(card);
        return (List<Card>) hibernateTemplate.find("from Card ");
    }

    /**
     * 查询请假信息
     * @return
     */
    @Override
    public List<Card> getCardInfo() {
        return (List<Card>) hibernateTemplate.find("from Card ");
    }

    /**
     * 查询请假信息   分页
     * @param begin
     * @param limit
     * @return
     */
    @Override
    public List<Card> selectCardByPage(final int begin, final int limit) {
        List<Card> list = hibernateTemplate.execute(new HibernateCallback<List<Card>>() {
            @Override
            public List<Card> doInHibernate(Session session) throws HibernateException {
                List list = session.createCriteria(Card.class).setFirstResult(begin).setMaxResults(limit).addOrder(Order.asc("status")).list();
                return list;
            }
        });
        return list;
    }

    /**
     * 同意审批
     * @param cid
     */
    @Override
    public void agree(Integer cid) {
        Card card = hibernateTemplate.get(Card.class,cid);
        card.setStatus(1);
        hibernateTemplate.update(card);
    }

    /**
     * 拒绝审批
     * @param cid
     */
    @Override
    public void refuse(Integer cid) {
        Card card = hibernateTemplate.get(Card.class,cid);
        card.setStatus(2);
        hibernateTemplate.update(card);
    }

    /**
     * 根据ID查询信息
     * @param cid
     * @return
     */
    @Override
    public List<Card> detailInfo(Integer cid) {
        return (List<Card>) hibernateTemplate.find("from Card where cid = ?",cid);
    }
}
