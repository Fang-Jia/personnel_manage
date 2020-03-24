package com.xf.service;

import com.xf.dao.LeaveDao;
import com.xf.entity.Leave;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 部门   业务层
 */
@Transactional
public class LeaveService {

    private LeaveDao leaveDao;
    public void setLeaveDao(LeaveDao leaveDao) { this.leaveDao = leaveDao; }

    /**
     * 提交请假信息
     * @param leave
     * @return
     */
    public int addInfo(Leave leave) {
        List<Leave> leaveList = leaveDao.addInfo(leave);
        return getSize(leaveList);
    }

    /**
     * 查询请假信息
     * @return
     */
    public List<Leave> getLeaveInfo() {
        return leaveDao.getLeaveInfo();
    }

    /**
     * 查询请假信息   分页
     * @return
     */
    public List<Leave> getPageInfo(int page, int limit) {
//        计算分页的开始，和每页显示的条数
        int begin = 0;
        if (page > 1){
            begin = (page-1) * limit;
        }
        List<Leave> leaveList = leaveDao.selectLeaveByPage(begin,limit);
        replaceData(leaveList);
        return leaveList;
    }

    /**
     * 同意审批
     * @param lid
     */
    public void agree(Integer lid) {
        leaveDao.agree(lid);

    }

    /**
     * 拒绝审批
     * @param lid
     */
    public void refuse(Integer lid) {
        leaveDao.refuse(lid);
    }

    /**
     * 根据ID查询信息
     * @param lid
     * @return
     */
    public Leave detailInfo(Integer lid) {
        List<Leave> leaveList = leaveDao.detailInfo(lid);
        replaceData(leaveList);
        return leaveList.get(0);
    }

    /**
     * 封装转换数据库数据的方法
     */
    public void replaceData(List<Leave> leaveList){
        for (Leave leave : leaveList){
//          转换时间格式
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date checkTime = leave.getCheckTime();
            Date leaveTime = leave.getLeaveTime();
            Date endTime = leave.getEndTime();
            leave.setCheckTimeStr(formatter.format(checkTime));
            leave.setLeaveTimeStr(formatter.format(leaveTime));
            leave.setEndTimeStr(formatter.format(endTime));

//            转换审批状态
            if (leave.getStatus() == 0){
                leave.setStatusText("未审批");
            }else if (leave.getStatus() == 1){
                leave.setStatusText("已审批");
            }else {
                leave.setStatusText("已拒绝");
            }

//            转换请假类型
            switch (leave.getLeaveType()){
                case 0:
                    leave.setLeaveTypeText("年休假");
                    break;
                case 1:
                    leave.setLeaveTypeText("事假");
                    break;
                case 2:
                    leave.setLeaveTypeText("病假");
                    break;
                case 3:
                    leave.setLeaveTypeText("产假");
                    break;
                case 4:
                    leave.setLeaveTypeText("丧假");
                    break;
                case 5:
                    leave.setLeaveTypeText("婚假");
                    break;
                case 6:
                    leave.setLeaveTypeText("护理假");
                    break;
                case 7:
                    leave.setLeaveTypeText("工伤假");
                    break;
                case 8:
                    leave.setLeaveTypeText("其他");
                    break;
            }
        }
    }

    /**
     * 获取未审批的个数
     */
    public int getSize(List<Leave> leaveList){
        int x = 0;
        for (Leave leave : leaveList){
            if (leave.getStatus() == 0){
                x++;
            }
        }
        return x;
    }
}
