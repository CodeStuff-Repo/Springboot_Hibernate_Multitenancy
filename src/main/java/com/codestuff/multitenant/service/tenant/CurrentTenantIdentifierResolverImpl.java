package com.codestuff.multitenant.service.tenant;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

@Component
public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver {

    private static final String DEFAULT_DB = "DEFAULT";

    @Override
    public String resolveCurrentTenantIdentifier() {
        if(StringUtils.isEmpty(TenantContext.getDbName())){
            return DEFAULT_DB;
        } else{
            return TenantContext.getDbName();
        }
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
