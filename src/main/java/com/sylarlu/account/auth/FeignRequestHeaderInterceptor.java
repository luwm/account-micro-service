package com.sylarlu.account.auth;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.util.StringUtils;

public class FeignRequestHeaderInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        String userId = AuthContext.getUserId();
        if(!StringUtils.isEmpty(userId)){
            requestTemplate.header(AuthConstant.CURRENT_USER_HEADER, userId);
        }
    }
}
