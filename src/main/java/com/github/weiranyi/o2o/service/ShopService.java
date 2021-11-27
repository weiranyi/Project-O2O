package com.github.weiranyi.o2o.service;

import com.github.weiranyi.o2o.dto.ShopExecution;
import com.github.weiranyi.o2o.entity.Shop;
import com.github.weiranyi.o2o.exceptions.ShopOperationException;

import java.io.InputStream;

/**
 * @author https://github.com/weiranyi/
 * @ClassName: ShopService
 * @Description: TODO()
 * @date 2021/8/13
 */
public interface ShopService {
    /**
     * 通过店铺ID获取店铺信息
     *
     * @param shopId
     * @return shop
     */
    Shop getByShopId(long shopId);

    /**
     * 更新店铺信息，包括对图片的处理
     *
     * @param shop
     * @param shopImgInputStream
     * @param fileName
     * @return ShopExecution
     */
    ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException;

    /**
     * 注册店铺信息，包括图片处理
     *
     * @param shop
     * @param shopImgInputStream
     * @param fileName
     * @return ShopExecution
     */
    ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName);
}

