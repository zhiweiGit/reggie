package com.reggie.controller.lnterceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class LoginRequiredInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getSession().getAttribute("employee")==null){
            log.info("重定向url"+request.getServletPath());
            response.sendRedirect("/backend/page/login/login.html");
            return false;

        }
        log.info("未定向"+request.getServletPath());
        return true;
    }

}
