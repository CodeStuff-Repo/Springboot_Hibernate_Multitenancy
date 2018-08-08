package com.codestuff.multitenant.service.data;

import javax.sql.DataSource;

import com.codestuff.multitenant.config.DataSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DataSourceDiscovery {

	@Autowired
	private Map<String, DataSourceBuilder> dataSourceMap;

	@Autowired
	private DataSource dataSource;
	
	public DataSource getDataSource(String dbName) {
		return this.dataSourceMap.get(dbName).getDataSource();
	}

	public DataSource getDefaultDataSource(){
		return this.dataSource;
	}
}
