package com.github.weiranyi.o2o.entity;

import java.util.Date;

/**
 * @author https://github.com/weiranyi/
 * @ClassName: Shop
 * @Description: TODO()
 * @date 2021/8/11
 */
public class Shop {
    private Long shopId;
    private String shopName;
    private String shopDesc;
    private String shopAddr;
    private String phone;
    private String shopImg;
    private Integer priority;
    private Date createTime;
    private Date lastEditTime;
    private Integer enableStatus; //-1、不可用 0、审核中 1、可用
    private String advice; // 超级管理员给商家提醒
    private Area area;
    private PersonInfo owner;
    private ShopCategory parentCategory;
}
