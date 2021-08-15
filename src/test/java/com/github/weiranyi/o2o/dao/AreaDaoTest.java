package com.github.weiranyi.o2o.dao;

import com.github.weiranyi.o2o.BaseTest;
import com.github.weiranyi.o2o.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * @author https://github.com/weiranyi/
 * @ClassName: Area
 * @Description: TODO()
 * @date 2021/8/11
 */

public class AreaDaoTest extends BaseTest {
    @Autowired
    private AreaDao areaDao;


    @Test
    public void testBQueryArea() throws Exception {
        List<Area> areaList = areaDao.queryArea();
        assertEquals(2, areaList.size());
    }

}
