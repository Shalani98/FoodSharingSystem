package com.example.foodsharing.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // Get project uploads folder
        String uploadPath = System.getProperty("user.dir") + "/uploads/";

        // Fix Windows backslashes
        uploadPath = uploadPath.replace("\\", "/");

        System.out.println("📁 Serving uploads from: " + uploadPath);

        // Make sure to use triple slash for Windows
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:///" + uploadPath);
    }
}