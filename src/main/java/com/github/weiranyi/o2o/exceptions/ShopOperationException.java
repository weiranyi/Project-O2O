package com.github.weiranyi.o2o.exceptions;

/**
 * @author https://github.com/weiranyi/
 * @ClassName: ShopOperationException
 * @Description: TODO()
 * @date 2021/8/13
 */
public class ShopOperationException extends RuntimeException{
    private static final long serialVersionUID = -6138086005915596252L;
    public ShopOperationException(String msg){
        super(msg);
    }
}
