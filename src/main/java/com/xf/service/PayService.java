package com.xf.service;

import com.xf.dao.PayDao;
import com.xf.entity.Pay;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 薪酬管理     业务层
 */
@Transactional
public class PayService {

    private PayDao payDao;

    public void setPayDao(PayDao payDao) {
        this.payDao = payDao;
    }

    /**
     * 获取薪酬信息
     */
    public List<Pay> getPayInfo() {
        return payDao.selectPay();
    }

    /**
     * 获取薪酬信息   分页
     */
    public List<Pay> getPageInfo(int page, int limit) {
//        计算分页的开始，和每页显示的条数
        int begin = 0;
        if (page > 1){
            begin = (page-1) * limit;
        }

        List<Pay> payList = payDao.selectPayByPage(begin,limit);
        for (Pay pay : payList){
            pay.setPersonnelTax(-getPersonnelTax(pay));
            pay.setTotal(getTotal(pay));
        }
        return payList;
    }

    /**
     * 删除薪酬信息
     */
    public void delPay(Integer pid) {
        payDao.delPay(pid);
    }

    /**
     * 添加薪酬信息
     */
    public void addPayInfo(Pay pay) {
        pay.setCasualReduceAllowance(-pay.getCasualReduceAllowance());
        pay.setPersonnelTax(-getPersonnelTax(pay));
        pay.setTotal(getTotal(pay));
        payDao.addPayInfo(pay);
    }

    /**
     * 封装计算个人所得税的方法
     */
    public Double getPersonnelTax(Pay pay){
        double monthPay = pay.getMonthPay();
        if (monthPay > 5000){
            double money = pay.getMonthPay() - 5000 - 1000;
            double personnelTax = 0;
            if (money <= 3000){
                personnelTax = money * 0.03;
            }else if (money <= 12000){
                personnelTax = money * 0.10;
            }else if (money <= 25000){
                personnelTax = money * 0.20;
            }else if (money <= 35000){
                personnelTax = money * 0.25;
            }else if (money <= 55000){
                personnelTax = money * 0.30;
            }else if (money <= 80000){
                personnelTax = money * 0.35;
            }else {
                personnelTax = money * 0.45;
            }

            return personnelTax;
        }else {
            return 0.00;
        }
    }

    /**
     * 封装合计工资方法
     */
    public Double getTotal(Pay pay){
        Double total = pay.getMonthPay() + pay.getPerformance() + pay.getFoodAllowance() +
                pay.getPhoneAllowance() + pay.getTravelAllowance() + pay.getAllowance() +
                pay.getOvertimeAllowance() + pay.getReduceAllowance() + pay.getCasualReduceAllowance() +
                pay.getPersonnelTax();
        return total;
    }

    /**
     * 通过名称搜索员工薪酬信息
     */
    public List<Pay> search(String name) {
        return payDao.searchByName(name);
    }

    /**
     * 通过ID搜索员工薪酬信息
     */
    public Pay searchById(Integer pid) {
        return payDao.searchById(pid);
    }

    /**
     * 编辑员工薪酬信息
     */
    public void editPay(Pay pay) {
        payDao.editPay(pay);
    }
}
