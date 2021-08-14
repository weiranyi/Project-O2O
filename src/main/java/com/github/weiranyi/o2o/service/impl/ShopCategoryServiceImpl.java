package com.github.weiranyi.o2o.service.impl;

import com.github.weiranyi.o2o.dao.ShopCategoryDao;
import com.github.weiranyi.o2o.entity.ShopCategory;
import com.github.weiranyi.o2o.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author https://github.com/weiranyi/
 * @ClassName: ShopCategoryServiceImpl
 * @Description: TODO()
 * @date 2021/8/14
 */
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
        // TODO Auto-generated method stub
        return shopCategoryDao.queryShopCategory(shopCategoryCondition);
    }

}
