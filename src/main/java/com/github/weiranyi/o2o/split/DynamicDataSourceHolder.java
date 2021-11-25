package com.github.weiranyi.o2o.split;

import com.github.weiranyi.o2o.util.OSUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author https://github.com/weiranyi/
 * @ClassName: DynamicDataSourceHolder
 * @Description: TODO()
 * @date 2021/8/15
 */
public class DynamicDataSourceHolder {
    // 配置日志
    private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceHolder.class);
    // 线程安全
    private static final ThreadLocal<String> contextHolder = new ThreadLocal();
    // key
    public static final String DB_MASTER = "master";
    public static final String DB_SLAVE = "slave";
    public static final String DB_TEST = "db-test";

    public static String getDbType() {
        String db = contextHolder.get();
        if (OSUtil.osType.name().equals("OS_TYPE_LINUX")) {
            // 测试库
            db = DB_TEST;
        }else {
            if (db == null) {
                db = DB_MASTER;
            }
        }
        return db;
    }

    public static void setDbType(String str) {
        logger.debug("所使用的数据源为" + str);
        contextHolder.set(str);
    }

    // 清洗数据源
    public static void clearDBType() {
        contextHolder.remove();
    }
}




