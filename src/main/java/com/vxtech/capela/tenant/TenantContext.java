
package com.vxtech.capela.tenant;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class TenantContext{

     final public static String DEFAULT_TENANT = "public";

     private static final ThreadLocal<String> currentTenant = ThreadLocal.withInitial(() -> DEFAULT_TENANT);

     public static void setCurrentTenant(String tenant) {

          log.info("[TENANT] - Setting tenant {} in context", tenant);
          currentTenant.set(tenant);
     }

     public static String getCurrentTenant() {

          return currentTenant.get();
     }

     public static void clear() {

          currentTenant.remove();
     }
}
