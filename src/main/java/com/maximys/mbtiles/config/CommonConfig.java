package com.maximys.mbtiles.config;

import jakarta.servlet.MultipartConfigElement;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

@Configuration
public class CommonConfig {
    @Bean
        //Метод для установления размера файла и размера запроса
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();

        factory.setMaxFileSize(DataSize.parse("100MB"));
        factory.setMaxRequestSize(DataSize.parse("100MB"));

        return factory.createMultipartConfig();
    }
}
