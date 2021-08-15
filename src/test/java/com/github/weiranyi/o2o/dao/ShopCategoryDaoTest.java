package com.github.weiranyi.o2o.dao;

import com.github.weiranyi.o2o.BaseTest;
import com.github.weiranyi.o2o.entity.ShopCategory;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShopCategoryDaoTest extends BaseTest {
    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Test
    @Ignore // 暂时跳过
    public void testQueryShopCategory() {
        List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(new ShopCategory());
        System.out.println(shopCategoryList.size());
        ShopCategory parent = new ShopCategory();
        ShopCategory son = new ShopCategory();
        parent.setShopCategoryId(1L);
        son.setParent(parent);
        List<ShopCategory> shopCategoryList2 = shopCategoryDao.queryShopCategory(son);
        assertEquals(1, shopCategoryList2.size());
        System.out.println(shopCategoryList2.get(0).getShopCategoryName());
    }

}
