package com.xf.dao;

import com.xf.entity.Leave;

import java.util.List;

/**
 * 部门   数据访问层
 */
public interface LeaveDao {


    List<Leave> addInfo(Leave leave);

    List<Leave> getLeaveInfo();

    List<Leave> selectLeaveByPage(int begin, int limit);

    void agree(Integer lid);

    void refuse(Integer lid);

    List<Leave> detailInfo(Integer lid);
}
