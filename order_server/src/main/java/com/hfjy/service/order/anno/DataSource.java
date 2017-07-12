package com.hfjy.service.order.anno;

import com.hfjy.service.order.wr.DataSourceType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DataSource {
    /**
     * 类型，代表是使用读还是写
     * @return
     */
    DataSourceType type() default DataSourceType.WRITE;

    /**
     * 指定要使用的DataSource的名称
     * @return
     */
    String name() default "";
}
