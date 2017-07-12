package com.hfjy.service.order.wr;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class,
                RowBounds.class, ResultHandler.class})
})
public class DynamicDataSourcePlugin implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement ms = (MappedStatement)invocation.getArgs()[0];
        DataSourceType dataSourceType = null;
        if ((dataSourceType = DynamicDataSourceHolder.getFromCach(ms.getId())) == null) {
            if (ms.getSqlCommandType().equals(SqlCommandType.SELECT)) {
                dataSourceType = DataSourceType.READ;
            } else {
                dataSourceType = DataSourceType.WRITE;
            }
            DynamicDataSourceHolder.putToCache(ms.getId(), dataSourceType);
        }
        DynamicDataSourceHolder.putDataSource(dataSourceType);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
