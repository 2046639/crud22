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
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private UserService userService = UserService.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<User> users = null;

            users = userService.selectAllUsers();

        request.setAttribute("users", users);
        request.getRequestDispatcher("/admin.jsp").forward(request, response);
    }
}

