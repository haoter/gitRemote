package com.gupao.springbootdemo.config;

import com.gupao.springbootdemo.interceptors.TenantIdInitHandler;
import com.gupao.springbootdemo.interceptors.WebMvcHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

import javax.annotation.Resource;

@Configuration
public class CustomWebMvcConfiguration implements WebMvcConfigurer {

    @Resource
    private WebMvcHandlerInterceptor webMvcHandlerInterceptor;
    @Resource
    private TenantIdInitHandler tenantIdInitHandler;

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer){
        // 配置路径匹配规则, 例如：为所有的接口添加统一前缀
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        configurer.setUrlPathHelper(urlPathHelper);
        configurer.addPathPrefix("api", c -> c.isAnnotationPresent(RestController.class));
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tenantIdInitHandler).addPathPatterns("/**");
        registry.addInterceptor(webMvcHandlerInterceptor).addPathPatterns("/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 设置允许跨域的路径
        registry.addMapping("/**")
                // 设置允许跨域请求的域名
                .allowedOrigins("*")
                // 是否允许证书
                .allowCredentials(false)
                // 设置允许的方法
                .allowedMethods("*")
                // 设置允许的header属性
                .allowedHeaders("*")
                // 跨域允许时间
                .maxAge(1800);
    }
}
