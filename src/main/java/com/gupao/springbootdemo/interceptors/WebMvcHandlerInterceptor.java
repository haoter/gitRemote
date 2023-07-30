package com.gupao.springbootdemo.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Component
public class WebMvcHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (Objects.isNull(request.getHeader("TENANT-ID"))) {
            //设置返回错误信息， 这里使用打印代替
            System.out.println("请求头参数 TENANT-ID 不能为空");
            return false;
        }
        return true;
    }
}
