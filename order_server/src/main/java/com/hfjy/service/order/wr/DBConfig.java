package com.hfjy.service.order.wr;

import com.zaxxer.hikari.HikariConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(locations = "classpath:datasource.yaml", prefix = "dds")
public class DBConfig {
    private List<HikariConfig> read;
    private HikariConfig write;

    public List<HikariConfig> getRead() {
        return read;
    }

    public void setRead(List<HikariConfig> read) {
        this.read = read;
    }

    public HikariConfig getWrite() {
        return write;
    }

    public void setWrite(HikariConfig write) {
        this.write = write;
    }
}
