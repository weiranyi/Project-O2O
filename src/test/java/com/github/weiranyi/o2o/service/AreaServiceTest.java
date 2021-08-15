package com.github.weiranyi.o2o.service;

import com.github.weiranyi.o2o.BaseTest;
import com.github.weiranyi.o2o.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author https://github.com/weiranyi/
 * @ClassName: AreaServiceTest 单元
 * @Description: TODO()
 * @date 2021/8/12
 */

public class AreaServiceTest extends BaseTest {
    @Autowired
    private AreaService areaService;

    @Test
    public void testGetAreaList() {
        List<Area> areaList = areaService.getAreaList();
        assertEquals("理工东苑", areaList.get(0).getAreaName());
    }

}
