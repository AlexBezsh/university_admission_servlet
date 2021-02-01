package com.bezshtanko.university_admission_servlet.dao;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class DataSourceHolder {

    private static final Logger log = LoggerFactory.getLogger(DataSourceHolder.class);

    private static volatile DataSource dataSource;

    private DataSourceHolder() {
    }

    public static DataSource getDataSource() {
        DataSource result = dataSource;
        if (dataSource != null) {
            return result;
        }

        synchronized (DataSourceHolder.class) {
            if (dataSource == null) {
                log.info("initializing data source");
                Properties properties = getProperties();
                BasicDataSource ds = new BasicDataSource();
                ds.setDriverClassName(properties.getProperty("datasource.driverClassName"));
                ds.setUrl(properties.getProperty("datasource.url"));
                ds.setUsername(properties.getProperty("datasource.username"));
                ds.setPassword(properties.getProperty("datasource.password"));
                ds.setMinIdle(5);
                ds.setMaxIdle(10);
                ds.setMaxOpenPreparedStatements(100);
                dataSource = ds;
                log.info("data source initialized");
            }
        }
        return dataSource;
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        InputStream inputStream = DataSourceHolder.class.getClassLoader().getResourceAsStream("application.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            log.error("properties initialization error");
            throw new RuntimeException(e);
        }
        return properties;
    }

}
