package com.github.weiranyi.o2o.service;

import com.github.weiranyi.o2o.entity.ShopCategory;

import java.util.List;

public interface ShopCategoryService {
    List<ShopCategory> getShopCategoryList(ShopCategory queryShopCategory);
}
