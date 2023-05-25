package com.reggie.controller.lnterceptor;

import com.alibaba.fastjson2.JSON;
import com.reggie.common.R;
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
            String xRequestedWith = request.getHeader("x-requested-with");
            if ("GET".equals(request.getMethod())) {
                log.info("重定向url"+request.getServletPath());
                response.sendRedirect("/backend/page/login/login.html");
                return false;
            }
            response.getOutputStream().write(JSON.toJSONString(R.error("未登录")).getBytes());
            return false;

        }
        log.info("未定向"+request.getServletPath());
        return true;
    }

}
