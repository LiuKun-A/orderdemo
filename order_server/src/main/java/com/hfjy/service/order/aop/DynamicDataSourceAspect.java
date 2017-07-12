package com.hfjy.service.order.aop;


import com.hfjy.service.order.anno.DataSource;
import com.hfjy.service.order.wr.DynamicDataSourceHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 使用AOP拦截，对需要特殊方法可以指定要使用的数据源名称(对应为连接池名称）
 */
@Aspect
@Component
public class DynamicDataSourceAspect {

    @Pointcut("execution(public * com.hfjy.service.order.dao.*.*(*))")
    public void dynamic(){}

    @Before(value = "dynamic()")
    public void beforeOpt(JoinPoint point) {
        Object target = point.getTarget();
        String methodName = point.getSignature().getName();
        Class<?>[] clazz = target.getClass().getInterfaces();
        Class<?>[] parameterType = ((MethodSignature)point.getSignature()).getMethod().getParameterTypes();
        try {
            Method method = clazz[0].getMethod(methodName,parameterType);
            if (method != null && method.isAnnotationPresent(DataSource.class)) {
                DataSource datasource = method.getAnnotation(DataSource.class);
                DynamicDataSourceHolder.putDataSource(datasource.type());
                String poolName = datasource.name();
                DynamicDataSourceHolder.putPoolName(poolName);
                DynamicDataSourceHolder.putToCache(clazz[0].getName() + "." + methodName, datasource.type());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After(value = "dynamic()")
    public void afterOpt(JoinPoint point) {
        DynamicDataSourceHolder.clearDataSource();
    }
}
