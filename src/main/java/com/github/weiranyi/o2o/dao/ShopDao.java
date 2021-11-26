package com.github.weiranyi.o2o.dao;

import com.github.weiranyi.o2o.entity.Shop;

public interface ShopDao {
    /**
     * 通过shop id 查询店铺 [店铺开发编辑第一步dao层]
     *
     * @param shopId
     * @return shop
     */
    Shop queryByShopId(long shopId);

    /**
     * 通过shop id 查询店铺
     *
     * @param shop
     * @return
     */
    int insertShop(Shop shop);
    /**
     * 更新店铺信息
     *
     * @param shop
     * @return
     */
    int updateShop(Shop shop);
}
