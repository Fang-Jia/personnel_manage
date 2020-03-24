package com.xf.dao.impl;

import com.xf.dao.UserDao;
import com.xf.entity.Department;
import com.xf.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {

    private HibernateTemplate hibernateTemplate;

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    /**
     * 添加用户
     */
    @Override
    public boolean addUser(User user) {
//        先向数据库中查询是否已经有该用户，若是有，则返回给用户错误信息
        List<User> list = (List<User>) hibernateTemplate.find("from User where username = ?",user.getUsername());
        if (list.size() == 0){
            hibernateTemplate.save(user);
            return true;
        }else {
            return false;
        }
    }

    /**
     * 检测用户账号和密码
     */
    @Override
    public List<User> getUserInfo(User user) {
        List<User> list = (List<User>) hibernateTemplate.find("from User where username = ?",user.getUsername());
        return list;
    }

    /**
     * 获取所有用户信息
     */
    @Override
    public List<User> getUser() {
        return (List<User>) hibernateTemplate.find("from User ");
    }

    /**
     * 获取所有用户信息   分页
     */
    @Override
    public List<User> selectUserByPage(final int begin, final int limit) {
        List<User> list = hibernateTemplate.execute(new HibernateCallback<List<User>>() {
            @Override
            public List<User> doInHibernate(Session session) throws HibernateException {
                List list = session.createCriteria(User.class).setFirstResult(begin).setMaxResults(limit).list();
                return list;
            }
        });
        return list;
    }

    /**
     * 通过用户名搜索用户信息
     */
    @Override
    public List<User> searchByName(String username) {
        String name = "%" + username + "%";
        return (List<User>) hibernateTemplate.find("from User where username like ?",name);
    }

    /**
     * 通过id删除用户
     */
    @Override
    public void delUser(Integer uid) {
        User user = hibernateTemplate.get(User.class,uid);
        hibernateTemplate.delete(user);
    }

    /**
     * 编辑用户信息
     */
    @Override
    public void editUser(User user) {
        hibernateTemplate.update(user);
    }

    /**
     * 通过id查询用户信息
     */
    @Override
    public User searchById(Integer uid) {
        return hibernateTemplate.get(User.class,uid);
    }

    /**
     * 通过用户名查询用户个人信息
     */
    @Override
    public List<User> selectMyInfoByName(String username) {
        return (List<User>) hibernateTemplate.find("from User where username = ?",username);
    }
}
