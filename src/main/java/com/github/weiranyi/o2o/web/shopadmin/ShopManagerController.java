package com.github.weiranyi.o2o.web.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.weiranyi.o2o.dto.ShopExecution;
import com.github.weiranyi.o2o.entity.Area;
import com.github.weiranyi.o2o.entity.PersonInfo;
import com.github.weiranyi.o2o.entity.Shop;
import com.github.weiranyi.o2o.entity.ShopCategory;
import com.github.weiranyi.o2o.enums.ShopStateEnum;
import com.github.weiranyi.o2o.exceptions.ShopOperationException;
import com.github.weiranyi.o2o.service.AreaService;
import com.github.weiranyi.o2o.service.ShopCategoryService;
import com.github.weiranyi.o2o.service.ShopService;
import com.github.weiranyi.o2o.util.HttpServletRequestUtil;
import com.github.weiranyi.o2o.util.ImageUtil;
import com.github.weiranyi.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/weiranyi/
 * @ClassName: ShopManagerController
 * @Description: TODO()
 * @date 2021/8/14
 */
@Controller
@RequestMapping("/shopadmin")
public class ShopManagerController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "getshopinitinfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopInitInfo(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
        List<Area> areaList = new ArrayList<Area>();
        try {
            shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
            areaList = areaService.getAreaList();
            modelMap.put("shopCategoryList", shopCategoryList);
            modelMap.put("areaList", areaList);
            modelMap.put("success", true);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }
        System.out.println(modelMap);
        return modelMap;
    }

    @RequestMapping(value = "/registershop", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> registerShop(HttpServletRequest request) {

        //定义一个返回值
        Map<String, Object> modelMap = new HashMap<String, Object>();
        //1.接收并转换相应的参数，包括店铺信息以及图片信息
        //获取请求头的店铺信息
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        //将json转换为Shop实例
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try {
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMeg", e.getMessage());
            return modelMap;
        }
        //将请求中的文件流剥离出来，通过CommonsMultipartFile去接收
        CommonsMultipartFile shopImg = null;
        // 解析
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
                // 获取上传文件内容
                request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            // 类型转化
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            // 提取文件流
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "上传图片不能为空");
            return modelMap;
        }
        //2.注册店铺
        if (shop != null && shopImg != null) {
            PersonInfo owner = new PersonInfo();
            //预期从Session获取，目前自定义，以后完善
            owner.setUserId(1L);
            shop.setOwner(owner);
            //由于addShop的第二个参数是File类型的，而传入的ShopImg是CommonsMultipartFile这样的一个类型，因此需要将CommonsMultipartFile转换成File类型
            File shopImgFile = new File(PathUtil.getImgBasePath() + ImageUtil.getRandomFileName());
            //注册店铺
            ShopExecution se = null;
            try {
                se = shopService.addShop(shop, shopImg.getInputStream(), shopImg.getOriginalFilename());
                if (se.getState() == ShopStateEnum.CHECK.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", se.getStateInfo());
                }
            } catch (ShopOperationException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
            } catch (IOException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
            }
            return modelMap;
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入店铺信息");
            return modelMap;
        }
        //3、返回结果
    }
}
