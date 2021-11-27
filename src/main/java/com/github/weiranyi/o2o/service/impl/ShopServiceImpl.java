package com.github.weiranyi.o2o.service.impl;

import com.github.weiranyi.o2o.dao.ShopDao;
import com.github.weiranyi.o2o.dto.ShopExecution;
import com.github.weiranyi.o2o.entity.Shop;
import com.github.weiranyi.o2o.enums.ShopStateEnum;
import com.github.weiranyi.o2o.exceptions.ShopOperationException;
import com.github.weiranyi.o2o.service.ShopService;
import com.github.weiranyi.o2o.util.ImageUtil;
import com.github.weiranyi.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Date;

/**
 * @author https://github.com/weiranyi/
 * @ClassName: ShopServiceImpl
 * @Description: TODO()
 * @date 2021/8/13
 */
@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao;

    @Override
    public Shop getByShopId(long shopId) {
        return shopDao.queryByShopId(shopId);
    }

    @Override
    public ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName)
            throws ShopOperationException {
        if (shop == null || shop.getShopId() == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        } else {
            try {
                // 1.判断是否需要处理图片
                if (shopImgInputStream != null && fileName != null && !"".equals(fileName)) {
                    Shop tempShop = shopDao.queryByShopId(shop.getShopId());
                    if (tempShop.getShopImg() != null) {
                        //编写工具类，删除图片信息
                        ImageUtil.deleteFileOfPath(tempShop.getShopImg());
                    }
                    addShopImg(shop, shopImgInputStream, fileName);

                }
                // 2.更新店铺信息
                shop.setLastEditTime(new Date());
                int effectedNum = shopDao.updateShop(shop);
                if (effectedNum <= 0) {
                    return new ShopExecution(ShopStateEnum.INNER_ERROR);
                } else {
                    shop = shopDao.queryByShopId(shop.getShopId());
                    return new ShopExecution(ShopStateEnum.SUCCESS, shop);
                }
            } catch (Exception e) {
                throw new ShopOperationException("modifyShop error:" + e.getMessage());
            }
        }
    }

    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) {
        if (shop == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        // 注册逻辑
        try {
            // 店铺信息初始化
            shop.setEnableStatus(0); // 未上线
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            // 添加店铺信息
            int effectedNum = shopDao.insertShop(shop);
            if (effectedNum <= 0) {
                // RuntimeException 事务可终止回滚
                throw new ShopOperationException("店铺创建失败");
            } else {
                try {
                    if (shopImgInputStream != null) {
                        addShopImg(shop, shopImgInputStream, fileName);
                        effectedNum = shopDao.updateShop(shop);
                        if (effectedNum <= 0) {
                            throw new ShopOperationException("更新图片地址失败");
                        }
                    }
                } catch (Exception e) {
                    throw new ShopOperationException("addShopImg error: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            throw new ShopOperationException("addShop error" + e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK, shop);
    }

    private void addShopImg(Shop shop, InputStream shopImgInputStream, String fileName) {
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(shopImgInputStream, fileName, dest);
        shop.setShopImg(shopImgAddr);
    }
}
