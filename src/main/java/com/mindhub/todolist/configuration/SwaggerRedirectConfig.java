package com.mindhub.todolist.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SwaggerRedirectConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
       registry.addRedirectViewController("/", "/swagger-ui.html");
       registry.addRedirectViewController("/swagger", "/swagger-ui.html");
    }
}