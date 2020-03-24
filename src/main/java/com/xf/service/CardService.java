package com.xf.service;

import com.xf.dao.CardDao;
import com.xf.dao.LeaveDao;
import com.xf.entity.Card;
import com.xf.entity.Leave;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 部门   业务层
 */
@Transactional
public class CardService {

    private CardDao cardDao;
    public void setCardDao(CardDao cardDao) { this.cardDao = cardDao; }

    /**
     * 提交请假信息
     * @param card
     * @return
     */
    public int addInfo(Card card) {
        List<Card> cardList = cardDao.addInfo(card);
        return getSize(cardList);
    }

    /**
     * 查询请假信息
     * @return
     */
    public List<Card> getCardInfo() {
        return cardDao.getCardInfo();
    }

    /**
     * 查询请假信息   分页
     * @return
     */
    public List<Card> getPageInfo(int page, int limit) {
//        计算分页的开始，和每页显示的条数
        int begin = 0;
        if (page > 1){
            begin = (page-1) * limit;
        }
        List<Card> cardList = cardDao.selectCardByPage(begin,limit);
        replaceData(cardList);
        return cardList;
    }

    /**
     * 同意审批
     * @param cid
     */
    public void agree(Integer cid) {
        cardDao.agree(cid);

    }

    /**
     * 拒绝审批
     * @param cid
     */
    public void refuse(Integer cid) {
        cardDao.refuse(cid);
    }

    /**
     * 根据ID查询信息
     * @param cid
     * @return
     */
    public Card detailInfo(Integer cid) {
        List<Card> cardList = cardDao.detailInfo(cid);
        replaceData(cardList);
        return cardList.get(0);
    }

    /**
     * 封装转换数据库数据的方法
     */
    public void replaceData(List<Card> cardList){
        for (Card card : cardList){
//          转换时间格式
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date checkTime = card.getCheckTime();
            Date cardTime = card.getCardTime();
            card.setCheckTimeStr(formatter.format(checkTime));
            card.setCardTimeStr(formatter.format(cardTime));

//            转换审批状态
            if (card.getStatus() == 0){
                card.setStatusText("未审批");
            }else if (card.getStatus() == 1){
                card.setStatusText("已审批");
            }else {
                card.setStatusText("已拒绝");
            }
        }
    }

    /**
     * 获取未审批的个数
     */
    public int getSize(List<Card> cardList){
        int x = 0;
        for (Card card : cardList){
            if (card.getStatus() == 0){
                x++;
            }
        }
        return x;
    }
}
