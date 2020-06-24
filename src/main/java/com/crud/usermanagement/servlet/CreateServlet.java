package com.crud.usermanagement.servlet;


import com.crud.usermanagement.model.User;
import com.crud.usermanagement.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/create")
public class CreateServlet extends HttpServlet {
    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/create.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");

        String role = request.getParameter("role");
        String password = request.getParameter("password");
        User user = new User(name, password, role);

        if (!userService.isValidUser(user)) {

            response.sendRedirect(request.getContextPath() + "/admin");
            return;
        }

        userService.insertUser(user);

        response.sendRedirect(request.getContextPath() + "/admin");
    }
}