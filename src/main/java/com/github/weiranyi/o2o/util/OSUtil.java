package com.github.weiranyi.o2o.util;

/**
 * @author https://github.com/weiranyi/
 * @ClassName: OSUtils
 * @Description: TODO()
 * @date 2021/8/15
 */
public class OSUtil {
    public enum OSType {
        OS_TYPE_WIN, OS_TYPE_MAC, OS_TYPE_LINUX
    }

    private static OSType getOSType() {
        String osName = System.getProperty("os.name");
        if (osName.startsWith("Windows")) {
            return OSType.OS_TYPE_WIN;
        } else if (osName.contains("Mac")) {
            return OSType.OS_TYPE_MAC;
        } else {
            return OSType.OS_TYPE_LINUX;
        }
    }

    public static final OSType osType = getOSType();
    public static final boolean WINDOWS = (osType == OSType.OS_TYPE_WIN);
    public static final boolean MAC = (osType == OSType.OS_TYPE_MAC);
    public static final boolean LINUX = (osType == OSType.OS_TYPE_LINUX);

}
