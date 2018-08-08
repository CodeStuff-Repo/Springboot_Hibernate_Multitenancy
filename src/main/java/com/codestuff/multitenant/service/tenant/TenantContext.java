package com.codestuff.multitenant.service.tenant;

public class TenantContext {

    private String dbName;

    private static ThreadLocal<TenantContext> currentTenant = new ThreadLocal<TenantContext>(){

        protected TenantContext initialValue(){
            return new TenantContext();
        }
    };

    private static TenantContext get(){
        return currentTenant.get();
    }

    private static void clear(){
        currentTenant.remove();
    }

    public static String getDbName() {
        return get().dbName;
    }

    public static void setDbName(String dbName) {
        get().dbName = dbName;
    }
}
