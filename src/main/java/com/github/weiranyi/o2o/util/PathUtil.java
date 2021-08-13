package com.github.weiranyi.o2o.util;

/**
 * @author https://github.com/weiranyi/
 * @ClassName: PathUtil
 * @Description: TODO()
 * @date 2021/8/13
 */
public class PathUtil {
    // 记录系统文件分割符
    private static String separator = System.getProperty("file.separator");

    public static String getImgBasePath() {
        String os = System.getProperty("os.name");
        String basePath;
        if (os.toLowerCase().startsWith("win")) {
            basePath = "D:/projectdev/image/";
        } else {
            basePath = "/Users/weiranyi/Documents/workplace/resources/image";
        }
        return basePath.replace("/", separator);
    }

    public static String getShopImagePath(long shopId) {
        String imagePath = "/upload/item/shop/" + shopId + "/";
        return imagePath.replace("/", separator);
    }
}














