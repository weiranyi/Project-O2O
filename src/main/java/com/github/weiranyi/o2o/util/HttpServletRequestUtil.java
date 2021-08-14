package com.github.weiranyi.o2o.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author https://github.com/weiranyi/
 * @ClassName: HttpServletRequestUtil
 * @Description: TODO()
 * @date 2021/8/14
 */
public class HttpServletRequestUtil {
    /*
     * HttpServiceRequest request代表的客户端的请求。
     * 当客户端通过http协议访问服务器的时候，http请求头中的所有信息，都封装在这个对象中，通过这个对象提供的方法就可以获得客户端请求的所有信息。
     * 以用户注册为例，当用户注册店铺时，用户需要在前端的表格里面，提交店铺相关的信息，这个店铺的信息都会被保存在这个request中，返回值类型是Map类型，用来返回必要的健值对结果。
     */
    // 转为整型
    public static int getInt(HttpServletRequest request, String key) {
        try {
            return Integer.decode(request.getParameter(key));
        } catch (Exception e) {
            return -1;
        }
    }

    // 转为长型
    public static long getLong(HttpServletRequest request, String key) {
        try {
            return Long.valueOf(request.getParameter(key));
        } catch (Exception e) {
            return -1L;
        }
    }

    public static Double getDouble(HttpServletRequest request, String key) {
        try {
            return Double.valueOf(request.getParameter(key));
        } catch (Exception e) {
            return -1d;
        }
    }

    public static boolean getBoolean(HttpServletRequest request, String key) {
        try {
            return Boolean.valueOf(request.getParameter(key));
        } catch (Exception e) {
            return false;
        }
    }

    public static String getString(HttpServletRequest request, String key) {
        try {
            String result = request.getParameter(key);
            if (result != null) {
                result = result.trim();
            }
            if ("".equals(result)) {
                result = null;
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }
}
