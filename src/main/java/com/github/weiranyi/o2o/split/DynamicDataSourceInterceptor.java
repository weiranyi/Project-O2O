package com.github.weiranyi.o2o.split;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.SelectKeyGenerator;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Locale;
import java.util.Properties;

/**
 * @author https://github.com/weiranyi/
 * @ClassName: DynamicDataSourceInterceptor
 * @Description: TODO()
 * @date 2021/8/15
 */
@Intercepts({@Signature(type = Executor.class, method = "updata", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class,
                Object.class, RowBounds.class, ResultHandler.class})})
public class DynamicDataSourceInterceptor implements Interceptor {
    // 配置日志
    private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceInterceptor.class);
    //匹配以insert delete update+空格开头
    private static final String REGEX = ".*insert\\u0020.*|.*delete\\u0020.*|.*update\\u0020.*";

    // 主要拦截方法
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //判断是否是事务
        boolean synchronizationActive = TransactionSynchronizationManager.isActualTransactionActive();
        //获取参数
        Object[] args = invocation.getArgs(); // 接收sql变量参数
        MappedStatement ms = (MappedStatement) args[0]; //获取参数第一个，一般是操作名
        String DB = DynamicDataSourceHolder.DB_MASTER;
        if (!synchronizationActive) {
            // 读方法
            if (ms.getSqlCommandType().equals(SqlCommandType.SELECT)) {
                //selectKey 为自增id查询主键（Select LAST_INSERT_ID）方法，使用主库
                if (ms.getId().contains(SelectKeyGenerator.SELECT_KEY_SUFFIX)) {
                    DB = DynamicDataSourceHolder.DB_MASTER;
                } else {
                    BoundSql boundSql = ms.getSqlSource().getBoundSql(args[1]);
                    //将换行、制表符 替换为空格
                    String sql = boundSql.getSql().toLowerCase(Locale.CHINA).replaceAll("[\\t\\n\\r]", " ");
                    if (sql.matches(REGEX)) {
                        //正则匹配增删改
                        DB = DynamicDataSourceHolder.DB_MASTER;
                    } else {
                        DB = DynamicDataSourceHolder.DB_SLAVE;
                    }
                }
            }
        } else {
            //事务
            DB = DynamicDataSourceHolder.DB_MASTER;
        }
        logger.info("设置方法[{}] use [{}] Strategy,SqlCommanType[{}]..", ms.getId(),
                DB, ms.getSqlCommandType().name());
        DynamicDataSourceHolder.setDbType(DB);
        return invocation.proceed();
    }

    //返回拦截对象｜代理对象
    @Override
    public Object plugin(Object target) {
        //如果target是Exector  mybatis的SQL执行器
        if (target instanceof Executor) {
            // 将其交给intercept处理
            return Plugin.wrap(target, this);
        }
        // 不是就放过
        return target;
    }

    // 类初始化时做设置
    @Override
    public void setProperties(Properties properties) {

    }
}
