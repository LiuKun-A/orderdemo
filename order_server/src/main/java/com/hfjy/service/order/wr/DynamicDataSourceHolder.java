package com.hfjy.service.order.wr;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DynamicDataSourceHolder {
    private static final Map<String, DataSourceType> cache = new ConcurrentHashMap<>();
    private static final ThreadLocal<DataSourceType> holder = new ThreadLocal<>();
    private static final ThreadLocal<String> pool = new ThreadLocal<>();

    public static void putToCache(String key, DataSourceType dataSourceType) {
        cache.put(key,dataSourceType);
    }

    public static DataSourceType getFromCach(String key) {
        return cache.get(key);
    }

    public static void putDataSource(DataSourceType dataSourceType) {
        holder.set(dataSourceType);
    }

    public static DataSourceType getDataSource() {
        return holder.get();
    }

    public static void putPoolName(String name) {
        if (name != null && name.length() > 0) {
            pool.set(name);
        }
    }

    public static String getPoolName() {
        return pool.get();
    }

    public static void clearDataSource() {
        holder.remove();
        pool.remove();
    }
}
