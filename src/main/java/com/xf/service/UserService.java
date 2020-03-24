package com.xf.service;

import com.xf.dao.UserDao;
import com.xf.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class UserService {

//    注入userDao属性
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    public boolean add(User user){
        return userDao.addUser(user);
    }

    /**
     * 判断用户输入的账号和密码是否正确
     * @param user
     * @return
     */
    public boolean loginStatus(User user) {
        List<User> list = userDao.getUserInfo(user);
//        如果数据库中没有该用户，则list的size为0
        if (list.size() == 0){
            return false;
        }

//        判断用户密码是否正确
        User user1 = list.get(0);
        if (user.getPassword().equals(user1.getPassword())){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 获取用户信息
     * @return
     */
    public List<User> getUserInfo() {
        return userDao.getUser();
    }

    /**
     * 获取用户信息   分页
     * @param page
     * @param limit
     * @return
     */
    public List<User> getPageInfo(int page, int limit) {
        //        计算分页的开始，和每页显示的条数
        int begin = 0;
        if (page > 1){
            begin = (page-1) * limit;
        }
        return userDao.selectUserByPage(begin,limit);
    }

    /**
     * 通过用户名搜索用户信息
     * @param username
     * @return
     */
    public List<User> searchByName(String username) {
        return userDao.searchByName(username);
    }

    /**
     * 删除用户
     * @param uid
     */
    public void delUser(Integer uid) {
        userDao.delUser(uid);
    }

    /**
     * 编辑用户信息
     * @param user
     */
    public void editUser(User user) {
        userDao.editUser(user);
    }

    /**
     * 通过ID获取用户信息
     * @param uid
     * @return
     */
    public User searchById(Integer uid) {
        return userDao.searchById(uid);
    }

    /**
     * 通过用户名查询个人账户信息
     */
    public List<User> searchMyInfo(String username) {
        return userDao.selectMyInfoByName(username);
    }
}
