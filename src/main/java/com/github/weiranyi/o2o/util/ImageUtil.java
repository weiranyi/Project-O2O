package com.github.weiranyi.o2o.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
     *
     * @param thumbnailInputStream
     * @param targetAddr
     * @param fileName
     * @return String
     */
    public static String generateThumbnail(InputStream thumbnailInputStream, String fileName, String targetAddr) {
        String realFileName = getRandomFileName();
        String extension = getFileExtension(fileName);
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);

        try {
            Thumbnails.of(thumbnailInputStream).size(200, 200).watermark(
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
     * @param fileName
     * @return String
     */
    private static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
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

    /**
     * storePath是文件的路径还是目录的路径
     * 如果storePath是文件路径则删除该文件；
     * 如果storePath是目录路径则删除该目录下的所有文件
     *
     * @param storePath
     */
    public static void deleteFileOfPath(String storePath) {
        File fileOrPath = new File(PathUtil.getImgBasePath() + storePath);
        if (fileOrPath.exists()) {
            if (fileOrPath.isDirectory()) {
                File[] files = fileOrPath.listFiles(); // 下面所有文件都删除
                for (int i = 0; i < files.length; i++) {
                    files[i].delete();
                }
            }
            fileOrPath.delete();
        }
    }
}
