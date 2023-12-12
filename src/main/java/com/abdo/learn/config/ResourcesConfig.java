package com.abdo.learn.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourcesConfig implements WebMvcConfigurer {
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String localFilePath =  "file:///C:\\Users\\ABDO\\Desktop\\spring\\learn\\src\\main\\resources\\static"; 

        registry.addResourceHandler("/data/**")
                .addResourceLocations(localFilePath);
    }
}
