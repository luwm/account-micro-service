package com.sylarlu.account.config;

import com.sylarlu.account.error.GlobalExceptionTranslator;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Use this common config for Rest API
 */
@Configuration
@Import(value = {MicroConfig.class, GlobalExceptionTranslator.class})
public class RestConfig {
}
