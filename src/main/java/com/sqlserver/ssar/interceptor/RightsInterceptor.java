package com.sqlserver.ssar.interceptor;

import com.sqlserver.ssar.model.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Set;

@Component
public class RightsInterceptor implements HandlerInterceptor {

    @Value("${system.super.user.id}")
    private Long superId;

    private PathMatcher matcher = new AntPathMatcher();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        if(!user.getId().equals(superId)){
            Set<String> urls = (Set<String>) session.getAttribute("urls");
            String path = request.getServletPath();
            for (String url: urls) {
                // 判断当前请求是否在权限列表
                if (matcher.match(url, path)){
                    // 能匹配当前url标识已授权
                    return true;
                }
            }

            if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))){
                response.sendError(403);
            } else {
                // 非ajax请求需要提示
                response.sendRedirect(request.getContextPath()+"/reject");
            }
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
