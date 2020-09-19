package com.sylarlu.account.auth;

public class AuthConstant {
    public static final String COOKIE_NAME = "micro-account";
    public static final String CURRENT_USER_HEADER = "micro-current-user-id";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String AUTHORIZATION_ANONYMOUS_WEB="anonymous";
    public static final String AUTHORIZATION_ACCOUNT_SERVICE = "account-service";
    public static final String AUTHORIZATION_AUTHENTICATED_USER = "faraday-authenticated";
    // AUTH ERROR Messages
    public static final String ERROR_MSG_DO_NOT_HAVE_ACCESS = "You do not have access to this service";
    public static final String ERROR_MSG_MISSING_AUTH_HEADER = "Missing Authorization http header";

}
