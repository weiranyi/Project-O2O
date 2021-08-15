package com.github.weiranyi.o2o.split;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author https://github.com/weiranyi/
 * @ClassName: DynamicDataSource
 * @Description: TODO()
 * @date 2021/8/15
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHolder.getDbType();
    }
}
