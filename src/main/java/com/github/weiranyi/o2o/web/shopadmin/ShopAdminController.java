package com.github.weiranyi.o2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author https://github.com/weiranyi/
 * @ClassName: ShopAdminController
 * @Description: TODO()
 * @date 2021/8/14
 */
@Controller
@RequestMapping(value="shopadmin", method= RequestMethod.GET)
public class ShopAdminController {
    @RequestMapping(value="/shopoperation")
    public String shopOperation() {
        return "shop/shopoperation"; //返回中间路径
    }
}
