package com.xf.dao.impl;

import com.xf.dao.PositionDao;
import com.xf.entity.Position;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.util.List;

/**
 * 岗位表  数据持久层实现类
 */
public class PositionDaoImpl implements PositionDao {

    private HibernateTemplate hibernateTemplate;

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    /**
     * 获取岗位信息
     * @return
     */
    @Override
    public List<Position> getPosiInfo() {
       return (List<Position>) hibernateTemplate.find("from Position ");
    }

    /**
     * 获取岗位信息   分页
     * @param begin
     * @param limit
     * @return
     */
    @Override
    public List<Position> selectPosiByPage(final int begin, final int limit) {
        List<Position> list = hibernateTemplate.execute(new HibernateCallback<List<Position>>() {
            @Override
            public List<Position> doInHibernate(Session session) throws HibernateException {
                List list = session.createCriteria(Position.class).setFirstResult(begin).setMaxResults(limit).list();
                return list;
            }
        });
        return list;
    }

    /**
     * 添加岗位信息
     * @param position
     */
    @Override
    public void addPosiInfo(Position position) {
        hibernateTemplate.save(position);
    }

    /**
     * 通过名称搜索岗位信息
     * @param name
     * @return
     */
    @Override
    public List<Position> search(String name) {
        String likeName = "%" + name + "%";
        return (List<Position>) hibernateTemplate.find("from Position where name like ?",likeName);
    }

    /**
     * 删除岗位
     * @param pid
     */
    @Override
    public void delPosi(Integer pid) {
        Position position = hibernateTemplate.get(Position.class,pid);
        hibernateTemplate.delete(position);
    }

    /**
     * 通过ID搜索岗位信息
     * @param pid
     * @return
     */
    @Override
    public Position searchById(Integer pid) {
        return hibernateTemplate.get(Position.class,pid);
    }
}
