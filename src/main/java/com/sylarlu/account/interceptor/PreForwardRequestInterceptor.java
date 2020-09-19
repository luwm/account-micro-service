package com.sylarlu.account.interceptor;

import com.sylarlu.account.config.MappingProperties;
import com.sylarlu.account.http.RequestData;

public interface PreForwardRequestInterceptor {
    void intercept(RequestData requestData, MappingProperties mapping);
}
