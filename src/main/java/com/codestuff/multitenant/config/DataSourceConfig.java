package com.codestuff.multitenant.config;


import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class DataSourceConfig {
	
	private List<Map<String,String>> dataSources;
	
	public List<Map<String, String>> getDataSources() {
		return dataSources;
	}

	public void setDataSources(List<Map<String, String>> dataSources) {
		this.dataSources = dataSources;
	}

	private DataSource defaultDataSource;

	@Bean
	@Primary
	@DependsOn("dataSourceMap")
	public DataSource dataSource() {
		return this.defaultDataSource;
	}

	@Bean(name = "dataSourceMap")
    @Primary
    public Map<String, DataSourceBuilder> dataSourceMap(){
	    Map<String, DataSourceBuilder> dsMap = new HashMap<>();
	    for(Map<String, String> dsDetails: dataSources){
            String url = dsDetails.get("url");
            Properties properties = new Properties();
            properties.setProperty("driverClassName", "com.mysql.jdbc.driver");
            properties.setProperty("user", dsDetails.get("user"));
            properties.setProperty("password", dsDetails.get("pass"));
            DataSourceBuilder dsBuilder = new DataSourceBuilder(url, properties);
            dsMap.put(dsDetails.get("name"), dsBuilder);
            if(StringUtils.equals("CS_DEFAULT",dsDetails.get("name"))){
            	this.defaultDataSource = dsBuilder.getDataSource();
			}
        }
        return dsMap;
    }

}
