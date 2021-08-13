package com.github.weiranyi.o2o.enums;

/**
 * @author https://github.com/weiranyi/
 * @ClassName: ShopStateEnum
 * @Description: TODO()
 * @date 2021/8/13
 */
public enum ShopStateEnum {
    CHECK(0, "审核中"),
    OFFLINE(-1, "非法商铺"),
    SUCCESS(1, "操作成功"),
    PASS(2, "通过认证"),
    INNER_ERROR(-1001, "系统内部错误"),
    NULL_SHOPID(-1002, "ShopId为空");
    private int state;
    private String stateInfo;

    ShopStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }


    /**
     * 传入state返回响应的enum值
     *
     * @param index
     * @return ShopStateEnum
     */
    public static ShopStateEnum stateOf(int index) {
        for (ShopStateEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }

}
