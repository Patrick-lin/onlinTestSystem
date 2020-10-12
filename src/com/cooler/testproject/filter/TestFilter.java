package com.cooler.testproject.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class TestFilter implements Filter {
    //过滤器拦截请求对象，获取参数
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //针对所有请求体都是使用utf-8
        servletRequest.setCharacterEncoding("utf-8");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();
        HttpSession session = request.getSession(false);
        //如果请求地址中包含了login的子串，或者默认访问地址，则放行
        if (uri.indexOf("login")!=-1||"/myweb/".equals(uri)){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        //如果本次请求的是其他文件，需要验证是否有令牌session
        if (session!=null){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        request.getRequestDispatcher("/user/loginError.html").forward(servletRequest,servletResponse);
/*        String path = request.getServletPath();
        HttpSession session = request.getSession(false);
        //如果不是请求登录页面或者登录资源文件，则拦截下来
        if (session==null&&!("/user/UserLogin".equals(path))&&!("/login.html".equals(path))) {
            response.sendRedirect("/myweb/loginError.html");
        }
        //放行
        filterChain.doFilter(servletRequest, servletResponse);*/
    }
}
