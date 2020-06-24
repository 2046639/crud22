package com.crud.usermanagement.servlet.filters;


import com.crud.usermanagement.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminFilterServlet implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpRequest.getSession(); //берем текущую сессию, false - если сессия не существует, то ссылка на нее не будет получена
        User currentUser = (User) session.getAttribute("loginUser");//достаем от туда юзера
        if (currentUser == null) {
            //неавторизованный
            httpRequest.getRequestDispatcher("/login.jsp").forward(httpRequest, httpResponse);
//            httpResponse.sendRedirect("/");
            return;
        }
        if ("admin".equals(currentUser.getRole())) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if ("user".equals(currentUser.getRole())) {
            httpResponse.sendRedirect("/user");
            return;
        }
        else {
//            httpRequest.getRequestDispatcher("/user.jsp").forward(httpRequest, httpResponse);
            httpResponse.sendRedirect("/logout");
        }
    }

    @Override
    public void destroy() {
    }
}

