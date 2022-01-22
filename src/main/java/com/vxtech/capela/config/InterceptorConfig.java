package com.vxtech.capela.config;

import com.vxtech.capela.logging.LogInterceptor;
import com.vxtech.capela.tenant.TenantInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private final TenantInterceptor tenantInterceptor;
    private final LogInterceptor logInterceptor;

    public InterceptorConfig(TenantInterceptor tenantInterceptor, LogInterceptor logInterceptor) {
        this.tenantInterceptor = tenantInterceptor;
        this.logInterceptor = logInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tenantInterceptor);
        registry.addInterceptor(logInterceptor);
    }

}