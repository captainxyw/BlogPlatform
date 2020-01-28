package com.itshidu.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Package:com.itshidu.web.filter
 * Description:
 *
 * @Date:2020/1/28 20:09
 * @Author:xuyewei
 */
@WebFilter("/*")
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        req.setAttribute("servletPath", req.getServletPath());

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
