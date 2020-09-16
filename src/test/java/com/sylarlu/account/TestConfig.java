package com.sylarlu.account;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Configuration
public class TestConfig {
    public static String TEST_USER_ID = UUID.randomUUID().toString();

    @Bean
    public RequestInterceptor requestInterceptor(){
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                if(!StringUtils.isEmpty(TEST_USER_ID)){
//                    requestTemplate.header(AuthConstant.CURRENT_USER_HEADER, TEST_USER_ID)
                }
            }
        };
    }
}
