package com.xf.dao;

import com.xf.entity.Position;

import java.util.List;

/**
 * 岗位表  数据持久层
 */
public interface PositionDao {

    List<Position> getPosiInfo();

    List<Position> selectPosiByPage(int begin, int limit);

    void addPosiInfo(Position position);

    List<Position> search(String name);

    void delPosi(Integer pid);

    Position searchById(Integer pid);
}
