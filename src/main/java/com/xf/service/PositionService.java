package com.xf.service;

import com.xf.dao.PositionDao;
import com.xf.entity.Position;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 岗位表  业务层
 */
@Transactional
public class PositionService {

    private PositionDao positionDao;

    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    /**
     * 获取岗位信息
     * @return
     */
    public List<Position> getPosiInfo() {
        return positionDao.getPosiInfo();
    }

    /**
     * 获取岗位信息   分页
     * @param page
     * @param limit
     * @return
     */
    public List<Position> getPageInfo(int page, int limit) {
        //        计算分页的开始，和每页显示的条数
        int begin = 0;
        if (page > 1){
            begin = (page-1) * limit;
        }
        return positionDao.selectPosiByPage(begin,limit);
    }

    /**
     * 添加岗位信息
     * @param position
     */
    public void addPosiInfo(Position position) {
        positionDao.addPosiInfo(position);
    }

    /**
     * 通过名称搜索岗位信息
     * @param name
     * @return
     */
    public List<Position> search(String name) {
        return positionDao.search(name);
    }

    /**
     * 删除岗位
     * @param pid
     */
    public void delPosi(Integer pid) {
        positionDao.delPosi(pid);
    }

    /**
     * 通过ID搜索岗位信息
     * @param pid
     * @return
     */
    public Position searchById(Integer pid) {
        return positionDao.searchById(pid);
    }
}
