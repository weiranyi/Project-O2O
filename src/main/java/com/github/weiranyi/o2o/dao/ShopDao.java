package com.github.weiranyi.o2o.dao;

import com.github.weiranyi.o2o.entity.Shop;

public interface ShopDao {
    /**
     * 新增店铺
     *
     * @param shop
     * @return effectedNum
     */
    int insertShop(Shop shop);

}
