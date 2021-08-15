package com.github.weiranyi.o2o.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author https://github.com/weiranyi/
 * @ClassName: CodeUtil
 * @Description: TODO()
 * @date 2021/8/15
 */
public class CodeUtil {
    public static boolean checkVerifyCode(HttpServletRequest request) {
        // 获取生成的验证码
        String verifyCodeExpected = (String) request.getSession().getAttribute(
                com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        // 输入的验证码
        String verifyCodeActual = HttpServletRequestUtil.getString(request, "verifyCodeActual");
        if (verifyCodeActual == null
                || !verifyCodeActual.equalsIgnoreCase(verifyCodeExpected)) {
            // 不考虑大小写
            return false;
        }
        return true;
    }
}
