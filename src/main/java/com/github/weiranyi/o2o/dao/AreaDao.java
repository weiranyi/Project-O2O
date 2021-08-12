package com.github.weiranyi.o2o.dao;

import com.github.weiranyi.o2o.entity.Area;

import java.util.List;

/**
 * @author https://github.com/weiranyi/
 * @ClassName: AreaDao
 * @Description: TODO()
 * @date 2021/8/11
 */
public interface AreaDao {
    /**
     * 列出地域列表
     * @return areaList
     */
    List<Area> queryArea();

}
