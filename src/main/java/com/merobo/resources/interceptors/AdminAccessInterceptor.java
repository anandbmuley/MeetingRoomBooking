package com.merobo.resources.interceptors;

import abm.authenticator.domain.UserRole;
import com.merobo.exceptions.UnAuthorizedAccessException;
import com.merobo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class AdminAccessInterceptor implements HandlerInterceptor {

    private final UserService userService;

    @Autowired
    public AdminAccessInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userId = Optional.ofNullable(request.getHeader("auth-id")).orElseThrow(UnAuthorizedAccessException::new);
        return userService.findBy(userId).filter($ -> UserRole.ADMIN.equals($.getRole())).isPresent();
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
