package com.github.weiranyi.o2o.service;

import com.github.weiranyi.o2o.dto.ShopExecution;
import com.github.weiranyi.o2o.entity.Shop;

import java.io.File;

/**
 * @author https://github.com/weiranyi/
 * @ClassName: ShopService
 * @Description: TODO()
 * @date 2021/8/13
 */
public interface ShopService {
    ShopExecution addShop(Shop shop, File shopImg);
}

