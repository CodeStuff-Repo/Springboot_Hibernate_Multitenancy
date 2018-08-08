package com.codestuff.multitenant.service.tenant;

import com.codestuff.multitenant.service.data.DataSourceDiscovery;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class MultiTenantConnectionProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

    @Autowired
    private DataSourceDiscovery dataSourceDiscovery;

    @Override
    public DataSource selectAnyDataSource(){
        return dataSourceDiscovery.getDefaultDataSource();
    }

    @Override
    public DataSource selectDataSource(String dbName) {
        return dataSourceDiscovery.getDataSource(dbName);
    }
}
