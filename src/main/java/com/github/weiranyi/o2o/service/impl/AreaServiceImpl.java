package com.github.weiranyi.o2o.service.impl;

import com.github.weiranyi.o2o.dao.AreaDao;
import com.github.weiranyi.o2o.entity.Area;
import com.github.weiranyi.o2o.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author https://github.com/weiranyi/
 * @ClassName: AreaServiceImpl
 * @Description: TODO()
 * @date 2021/8/12
 */

@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaDao areaDao;

    @Override
    public List<Area> getAreaList() {
        return areaDao.queryArea();
    }
}
