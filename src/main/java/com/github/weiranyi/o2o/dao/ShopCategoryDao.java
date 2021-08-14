package com.github.weiranyi.o2o.dao;

import com.github.weiranyi.o2o.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author https://github.com/weiranyi/
 * @ClassName: ShopCategoryDao
 * @Description: TODO()
 * @date 2021/8/14
 */
public interface ShopCategoryDao {

    List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition") ShopCategory shopCategoryCondition);

    List<ShopCategory> queryAllShopCategory();
}
