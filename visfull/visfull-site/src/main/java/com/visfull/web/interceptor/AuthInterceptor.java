package com.visfull.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter {

    private  static Logger LOGGER = LoggerFactory.getLogger(AuthInterceptor.class);
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String[] noInterceptors = new String[]{"login"};
        String requestPath  = request.getRequestURI();
        LOGGER.debug("agent request path {}",requestPath);
        boolean skip = true;
        for (String path : noInterceptors) {
            if(requestPath.indexOf(path) != -1){
                skip = false;
                break;
            }
        }
        if(skip&&request.getSession().getAttribute("operator") == null){
             response.sendRedirect(request.getContextPath());
             LOGGER.debug("refuse agent request! {}",request.getSession().getAttribute("operator"));
             return false;
        }
        return super.preHandle(request, response, handler);

    }

}
