package edu.nus.microservice.event_manager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration  // 标记为配置类
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 对所有路径启用跨域
                .allowedOrigins("https://d1sv4fxemqhi2y.cloudfront.net")  // 允许的前端源，替换为CloudFront的域名
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // 允许的HTTP方法，增加OPTIONS
                .allowedHeaders("*")  // 允许的请求头
                .allowCredentials(true);  // 是否允许携带Cookie
    }
}
