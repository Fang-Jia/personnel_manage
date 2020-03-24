package com.xf.dao;

import com.xf.entity.Pay;

import java.util.List;

/**
 * 薪酬管理     数据持久层
 */
public interface PayDao {
    List<Pay> selectPay();

    List<Pay> selectPayByPage(int begin, int limit);

    void delPay(Integer pid);

    void addPayInfo(Pay pay);

    List<Pay> searchByName(String name);

    Pay searchById(Integer pid);

    void editPay(Pay pay);
}
