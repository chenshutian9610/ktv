package org.tree.ktv.controller;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {

    // 配置可跳过检查的 uri
    // 可以是以 / 结尾的目录，也可以是全路径
    private String[] excludes;

    public String[] getExcludes() {
        return excludes;
    }

    public void setExcludes(String[] excludes) {
        this.excludes = excludes;
    }

    private boolean exclude(String uri) {
        for (String exclude : excludes)
            if ((exclude.endsWith("/") && uri.startsWith(exclude)) || exclude.equals(uri))
                return true;
        return false;
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        // 指定的 uri 将跳过检查
        Object uri = httpServletRequest.getRequestURI();
        if (uri != null && exclude((String) uri))
            return true;

        // 未登录
        if (httpServletRequest.getSession(true).getAttribute("user") == null)
            return false;

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
