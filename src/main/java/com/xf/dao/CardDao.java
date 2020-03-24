package com.xf.dao;

import com.xf.entity.Card;
import com.xf.entity.Leave;

import java.util.List;

/**
 * 部门   数据访问层
 */
public interface CardDao {


    List<Card> addInfo(Card card);

    List<Card> getCardInfo();

    List<Card> selectCardByPage(int begin, int limit);

    void agree(Integer cid);

    void refuse(Integer cid);

    List<Card> detailInfo(Integer cid);
}
