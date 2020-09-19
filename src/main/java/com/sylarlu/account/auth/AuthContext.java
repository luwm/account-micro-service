package com.sylarlu.account.auth;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class AuthContext {
    private static String getRequestHeader(String headerName){
        RequestAttributes requestAttributes= RequestContextHolder.getRequestAttributes();
        if(requestAttributes instanceof ServletRequestAttributes){
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            String value = request.getHeader(headerName);
            return value;
        }
        return null;
    }

    public static String getUserId(){
        return getRequestHeader(AuthConstant.CURRENT_USER_HEADER);
    }

    public static String getAuthz(){
        return getRequestHeader(AuthConstant.AUTHORIZATION_HEADER);
    }
}
