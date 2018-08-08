package com.codestuff.multitenant.config;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Properties;

public class DataSourceBuilder {

    private String url;
    private Properties properties;

    public DataSourceBuilder(String url, Properties properties){
        this.url =  url;
        this.properties = properties;
    }
    public DataSource getDataSource(){
        return new DriverManagerDataSource(this.url, this.properties);
    }
}
