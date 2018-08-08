package com.codestuff.multitenant.config;

import java.util.Map;
import com.codestuff.multitenant.service.tenant.CurrentTenantIdentifierResolverImpl;
import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.codestuff.multitenant.service.data.DataSourceDiscovery;

import java.util.LinkedHashMap;

@Configuration
public class EntityConfig {

	@Autowired
	private CurrentTenantIdentifierResolverImpl currentTenantIdentifierResolver;

	@Autowired
	private MultiTenantConnectionProvider multiTenantConnectionProvider;

	@Autowired
	private DataSourceDiscovery dataSourceDiscovery;

	@Bean
	@Primary
    @DependsOn("dataSourceMap")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setDatabase(Database.MYSQL);
		emf.setDataSource(dataSourceDiscovery.getDefaultDataSource());
		emf.setJpaVendorAdapter(jpaVendorAdapter);
		emf.setPackagesToScan("com.codestuff.multitenant.entity");
		emf.setJpaPropertyMap(createProps());
		return emf;
	}

	/*@Bean
	public EntityManager entityManager() {
		EntityManagerFactory factory = entityManagerFactoryBean().getObject();
		return factory.createEntityManager();
	}*/

	private Map<String, Object> createProps(){
		Map<String,Object> property= new LinkedHashMap();
		    property.put(Environment.SHOW_SQL,false);
            property.put(Environment.GENERATE_STATISTICS,false);
            property.put(Environment.USE_SECOND_LEVEL_CACHE,false);
            property.put(Environment.USE_QUERY_CACHE,false);
            property.put(Environment.FORMAT_SQL,true);
            property.put(Environment.MULTI_TENANT, MultiTenancyStrategy.DATABASE);
            property.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER,this.multiTenantConnectionProvider);
            property.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER,this.currentTenantIdentifierResolver);
            property.put(Environment.FLUSH_BEFORE_COMPLETION,true);
		return property;
	}
}
