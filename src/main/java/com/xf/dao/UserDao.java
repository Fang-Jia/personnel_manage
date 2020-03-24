package com.xf.dao;

import com.xf.entity.User;

import java.util.List;

public interface UserDao {

    public boolean addUser(User user);

    List<User> getUserInfo(User user);

    List<User> getUser();

    List<User> selectUserByPage(int begin, int limit);

    List<User> searchByName(String username);

    void delUser(Integer uid);

    void editUser(User user);

    User searchById(Integer uid);

    List<User> selectMyInfoByName(String username);
}
