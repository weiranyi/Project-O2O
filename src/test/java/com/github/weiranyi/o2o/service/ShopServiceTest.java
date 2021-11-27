package com.github.weiranyi.o2o.service;

import com.github.weiranyi.o2o.BaseTest;
import com.github.weiranyi.o2o.dto.ShopExecution;
import com.github.weiranyi.o2o.entity.Area;
import com.github.weiranyi.o2o.entity.PersonInfo;
import com.github.weiranyi.o2o.entity.Shop;
import com.github.weiranyi.o2o.entity.ShopCategory;
import com.github.weiranyi.o2o.enums.ShopStateEnum;
import com.github.weiranyi.o2o.exceptions.ShopOperationException;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ShopServiceTest extends BaseTest {
    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    @Autowired
    private ShopService shopService;

    @Test
    @Ignore
    public void testModifyShop() throws ShopOperationException, FileNotFoundException {
        Shop shop = new Shop();
        shop.setShopId(1L);
        shop.setShopName("修改后的店铺名称");
        InputStream is = new FileInputStream("E:\\cdut\\ssm\\Project-O2O\\src\\main\\resources\\images\\watermark\\watermark.png");
        ShopExecution shopExecution = shopService.modifyShop(shop, is, "watermark.png");
        System.out.println("新的图片地址：" + shopExecution.getShop().getShopImg());
    }

    @Test
    @Ignore
    public void testAddShop() throws FileNotFoundException {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试店铺2");
        shop.setShopDesc("test2");
        shop.setShopAddr("testaddr2");
        shop.setPhone("181801881912");
        shop.setCreateTime(new Date());
        shop.setLastEditTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");
        File shopImg = new File(basePath + "/images/imgInput/weiranyi.jpg");
        InputStream is = new FileInputStream(shopImg);
        ShopExecution shopExecution = shopService.addShop(shop, is, shopImg.getName());
        assertEquals(ShopStateEnum.CHECK.getState(), shopExecution.getState());
    }
}






