package com.quickbite.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Maps URL path /uploads/** to the physical folder uploads/ at project root
        registry
                .addResourceHandler("backend/uploads/**")
                .addResourceLocations("file:backend/uploads/");
    }
}