package com.github.weiranyi.o2o.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author https://github.com/weiranyi/
 * @ClassName: ImageUtil
 * @Description: TODO()
 * @date 2021/8/13
 */
public class ImageUtil {
    public static void main(String[] args) throws IOException {
        String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        Thumbnails.of(new File("/Users/weiranyi/Documents/workplace/java/IdeaProjects/Project-O2O/src/main/webapp/resources/images/weiranyi.jpg"))
                .size(200, 200).watermark(Positions.BOTTOM_RIGHT,
                        ImageIO.read(new File(basePath + "/images/watermark/watermark.png")),
                        0.5f).outputQuality(0.8f)
                .toFile("/Users/weiranyi/Documents/workplace/java/IdeaProjects/Project-O2O/src/main/webapp/resources/images/new_weiranyi.jpg");
    }
}
