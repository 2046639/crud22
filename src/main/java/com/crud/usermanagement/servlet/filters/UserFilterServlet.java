package com.crud.usermanagement.servlet.filters;


import com.crud.usermanagement.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/user")
public class UserFilterServlet implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpRequest.getSession();
        User loginUser = (User) session.getAttribute("loginUser");//достаем от туда юзера
        if (loginUser == null) {
            httpResponse.sendRedirect("/");
            return;
        }
        if ("admin".equals(loginUser.getRole()) || "user".equals(loginUser.getRole())) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        } else {
            httpResponse.sendRedirect("/");
        }
    }

    @Override
    public void destroy() {
    }
}
