package com.sylarlu.account.config;

import com.sylarlu.account.error.GlobalExceptionTranslator;
import com.sylarlu.account.filter.HealthCheckFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;

/**
 * Use this common config for Rest API
 */
@Configuration
@Import(value = {MicroConfig.class, GlobalExceptionTranslator.class})
public class RestConfig {
    @Bean
    public FilterRegistrationBean<HealthCheckFilter> healthCheckFilterRegistrationBean() {
        FilterRegistrationBean<HealthCheckFilter> registrationBean =
                new FilterRegistrationBean<>(new HealthCheckFilter());
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE + 70); // before faviconFilter
        return registrationBean;
    }
}
