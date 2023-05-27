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
        if ("GET".equals(request.getMethod())) {
            //判断是否为后端页面
            if (request.getServletPath().contains("backend") &&
                    request.getSession().getAttribute("employee") == null) {
                //String xRequestedWith = request.getHeader("x-requested-with");
                log.info("后端重定向url:" + request.getServletPath());
                response.sendRedirect("/backend/page/login/login.html");
                return false;
                //前端页面
            } else if (request.getServletPath().contains("front") &&
                    request.getSession().getAttribute("user") == null) {
                log.info("前端重定向url:" + request.getServletPath());
                response.sendRedirect("/front/page/login.html");
                return false;
            }
            else if (request.getServletPath().contains("shoppingCart")||
                    request.getServletPath().contains("category")||
                    request.getServletPath().contains("dish")
            ){}

        } else {
            if (request.getSession().getAttribute("employee") == null &&
                    request.getSession().getAttribute("user") == null) {
                log.info("未登录的请求" + request.getServletPath());
                response.getOutputStream().write(JSON.toJSONString(R.error("未登录")).getBytes());
                return false;
            }
        }
        log.info("未定向" + request.getServletPath());
        return true;
    }

}
