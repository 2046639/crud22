package com.crud.usermanagement.servlet.filters;

import com.crud.usermanagement.model.User;
import com.crud.usermanagement.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter("/")
public class AuthFilter implements Filter {
    private final UserService userService = UserService.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain filterChain)

            throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        final String name = req.getParameter("name");
        final String password = req.getParameter("password");



//        @SuppressWarnings("unchecked")
//        final AtomicReference<UserDAO> dao = (AtomicReference<UserDAO>) req.getServletContext().getAttribute("dao");

        final HttpSession session = req.getSession();

        //Logged user.
        if (session.getAttribute("loginUser") != null ) {
            User user = (User) session.getAttribute("loginUser");

            moveToMenu(req, res, user.getRole());


        } else if (userService.selectUserByNamePassword(name, password) != null) {
            User loginUser = userService.selectUserByNamePassword(name, password);
            final String role = loginUser.getRole();
            req.getSession().setAttribute("loginUser", loginUser);

            moveToMenu(req, res, role);

        } else {
            final String role = "none";

            moveToMenu(req, res, role);
        }
    }

    /**
     * Move user to menu.
     * If access 'admin' move to admin menu.
     * If access 'user' move to user menu.
     */
    private void moveToMenu(final HttpServletRequest req,
                            final HttpServletResponse res,
                            final String role)
            throws ServletException, IOException {

        if (role.equals("admin")) {
//            req.getRequestDispatcher("/admin.jsp").forward(req, res);
//            res.sendRedirect("/admin");
            req.getRequestDispatcher("/admin").forward(req, res);
//            res.sendRedirect("/admin");

        } else if (role.equals("user")) {
            req.getRequestDispatcher("/user").forward(req, res);
//            req.getRequestDispatcher("user").forward(req, res);
//            res.sendRedirect("/user");

        } else {

            req.getRequestDispatcher("/login").forward(req, res);
        }
    }


    @Override
    public void destroy() {
    }

}