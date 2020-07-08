package com.merobo.resources.interceptors;

import abm.authenticator.domain.UserRole;
import com.merobo.exceptions.UnAuthorizedAccessException;
import com.merobo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class AdminAccessInterceptor extends HandlerInterceptorAdapter {

    private final UserService userService;

    @Autowired
    public AdminAccessInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        String userId = Optional.ofNullable(request.getHeader("user_id")).orElseThrow(UnAuthorizedAccessException::new);
        return userService.findBy(userId).filter($ -> UserRole.ADMIN.equals($.getRole())).isPresent();
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
