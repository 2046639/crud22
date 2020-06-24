package com.crud.usermanagement.servlet.filters;


import com.crud.usermanagement.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/")
public class AnonymousFilterServlet implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpRequest.getSession();
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            filterChain.doFilter(httpRequest, httpResponse);
        } else if ("admin".equals(user.getRole())) {
            httpResponse.sendRedirect("/admin");//
        } else if ("user".equals(user.getRole())) {
            httpResponse.sendRedirect("/user");
        }
    }

    @Override
    public void destroy() {
    }
}
