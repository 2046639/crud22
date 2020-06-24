package com.crud.usermanagement.servlet;

import com.crud.usermanagement.model.User;
import com.crud.usermanagement.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/")
public class LoginServlet extends HttpServlet {
    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String loginUserName = request.getParameter("name");
        String loginUserPassword = request.getParameter("password");
        User loginUser = userService.selectUserByNamePassword(loginUserName, loginUserPassword);
        if (loginUser == null) {
            response.sendRedirect("/loginError.jsp");//
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("loginUser", loginUser);
            response.sendRedirect("/");
        }
    }
}

