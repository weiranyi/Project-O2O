package com.github.weiranyi.o2o.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author https://github.com/weiranyi/
 * @ClassName: ImageUtil
 * @Description: TODO()
 * @date 2021/8/13
 */
public class ImageUtil {
    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyyMMddHmmss");
    private static final Random RANDOM = new Random();
    /**
     * 创建目标路径设计的目录
     * @param thumbnail
     * @param targetAddr
     * @return String
     */
    public static String generateThumbnail(File thumbnail, String targetAddr) {
        String realFileName = getRandomFileName();
        String extension = getFileExtension(thumbnail);
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);

        try {
            Thumbnails.of(thumbnail).size(200, 200).watermark(
                    Positions.BOTTOM_RIGHT,  // place
                    ImageIO.read(new File(basePath + "/images/watermark/watermark.png")), // watermark
                    0.5f // pellucidity
            ).outputQuality(0.8f).toFile(dest);
        } catch (IOException e) {
            throw new RuntimeException("创建缩略图失败：" + e.toString());
        }
        return relativeAddr;
    }

    /**
     * 创建目标路径设计的目录
     *
     * @param targetAddr
     */
    private static void makeDirPath(String targetAddr) {
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
    }

    /**
     * 获取输入文件流的扩展名
     *
     * @param cFile
     * @return String
     */
    private static String getFileExtension(File cFile) {
        String originalFileName = cFile.getName();
        return originalFileName.substring(originalFileName.lastIndexOf("."));
    }

    /**
     * 生成随机文件名：年月日时分秒+五位随机数
     *
     * @return String
     */
    public static String getRandomFileName() {
        // 10000-99999
        int rannum = RANDOM.nextInt(89999) + 10000;
        String nowTimStr = SIMPLE_DATE_FORMAT.format(new Date());
        return nowTimStr + rannum;
    }


    public static void main(String[] args) throws IOException {
        Thumbnails.of(new File("/Users/weiranyi/Documents/workplace/java/IdeaProjects/Project-O2O/src/main/webapp/resources/images/weiranyi.jpg"))
                .size(200, 200).watermark(
                        Positions.BOTTOM_RIGHT,  // place
                        ImageIO.read(new File(basePath + "/images/watermark/watermark.png")), // watermark
                        0.5f // pellucidity
                ).outputQuality(0.8f) // 压缩
                .toFile("/Users/weiranyi/Documents/workplace/java/IdeaProjects/Project-O2O/src/main/webapp/resources/images/new_weiranyi.jpg");
    }
}
