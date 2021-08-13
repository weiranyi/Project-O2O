package com.github.weiranyi.o2o.dto;

import com.github.weiranyi.o2o.entity.Shop;
import com.github.weiranyi.o2o.enums.ShopStateEnum;

import java.util.List;

/**
 * @author https://github.com/weiranyi/
 * @ClassName: ShopExecution
 * @Description: TODO()
 * @date 2021/8/13
 */
public class ShopExecution {
    // 结果状态
    private int state;
    // 状态标识（结果状态的解释）
    private String stateInfo;
    // 店铺数量
    private int count;
    // 操作的shop（增删改店铺的时候用）
    private Shop shop;
    // 获取的shop列表(查询店铺列表的时候用)
    private List<Shop> shopList;

    public ShopExecution() {
    }

    // 店铺操作失败的构造器
    public ShopExecution(ShopStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    // 店铺操作成功的构造器
    public ShopExecution(ShopStateEnum stateEnum, Shop shop) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shop = shop;
    }

    // 店铺操作成功的构造器
    public ShopExecution(ShopStateEnum stateEnum, List<Shop> shopList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shopList = shopList;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public List<Shop> getShopList() {
        return shopList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }
}
