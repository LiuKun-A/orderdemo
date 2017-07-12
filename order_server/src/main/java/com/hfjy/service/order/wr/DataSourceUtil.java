package com.hfjy.service.order.wr;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class DataSourceUtil {
    public static DataSource getDataSource(HikariConfig config) {
        return new HikariDataSource(config);
    }

    public static List<DataSource> getDataSource(List<HikariConfig> configs) {
        List<DataSource> result = null;
        if (configs != null && configs.size() > 0) {
            result = new ArrayList<>(configs.size());
            for (HikariConfig config : configs) {
                result.add(getDataSource(config));
            }
        } else {
            result = new ArrayList<>(0);
        }

        return result;
    }
}
