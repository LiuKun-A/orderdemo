package com.hfjy.service.order.wr;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public class DynamicDataSource extends AbstractRoutingDataSource {
    private DataSource writeDataSource;
    private List<DataSource> readDataSource;
    private int readDataSourceSize;
    private Map<String, String> dataSourceMapping = new ConcurrentHashMap<>();

    @Override
    public void afterPropertiesSet() {
        if (this.writeDataSource == null) {
            throw new IllegalArgumentException("Property 'writeDataSource' is required");
        }
        setDefaultTargetDataSource(writeDataSource);
        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put(DataSourceType.WRITE.name(), writeDataSource);
        String poolName = ((HikariDataSource)writeDataSource).getPoolName();
        if (poolName != null && poolName.length() > 0) {
            dataSourceMapping.put(poolName,DataSourceType.WRITE.name());
        }
        if (this.readDataSource == null) {
            readDataSourceSize = 0;
        } else {
            for (int i = 0; i < readDataSource.size(); i++) {
                targetDataSource.put(DataSourceType.READ.name() + i, readDataSource.get(i));
                poolName = ((HikariDataSource)readDataSource.get(i)).getPoolName();
                if (poolName != null && poolName.length() > 0) {
                    dataSourceMapping.put(poolName,DataSourceType.READ.name() + i);
                }
            }
            readDataSourceSize = readDataSource.size();
        }
        setTargetDataSources(targetDataSource);
        super.afterPropertiesSet();
    }


    @Override
    protected Object determineCurrentLookupKey() {
        DataSourceType dataSourceType = DynamicDataSourceHolder.getDataSource();
        String dataSourceName = null;
        if (dataSourceType == null ||dataSourceType == DataSourceType.WRITE || readDataSourceSize == 0) {
            dataSourceName = DataSourceType.WRITE.name();
        } else {
            String poolName = DynamicDataSourceHolder.getPoolName();
            if (poolName == null) {
                int idx = ThreadLocalRandom.current().nextInt(0, readDataSourceSize);
                dataSourceName = DataSourceType.READ.name() + idx;
            } else {
                dataSourceName = dataSourceMapping.get(poolName);
            }
        }
        DynamicDataSourceHolder.clearDataSource();
        return dataSourceName;
    }

    public void setWriteDataSource(DataSource writeDataSource) {
        this.writeDataSource = writeDataSource;
    }

    public void setReadDataSource(List<DataSource> readDataSource) {
        this.readDataSource = readDataSource;
    }
}
