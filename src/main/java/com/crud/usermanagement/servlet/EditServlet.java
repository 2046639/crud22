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

@WebServlet("/admin/edit")
public class EditServlet extends HttpServlet {
    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            User user = userService.selectUser(id);
            if (user != null) {
                request.setAttribute("user", user);
                getServletContext().getRequestDispatcher("/edit.jsp").forward(request, response);
            } else {
                getServletContext().getRequestDispatcher("/notFound.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            request.getRequestDispatcher("/notFound.jsp").forward(request, response);//получать диспетчер из реквеста
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        User user = new User(id, name, password, role);

//        if (!userService.isValidUser(user)) {
//            System.out.println("1 !!!!!!!!!!!!!!!!! "  + role);
//            response.sendRedirect(request.getContextPath() + "/admin");
//            return;
//        }

        if (role.equals("admin") || role.equals("user")) {

            userService.updateUser(user);

            response.sendRedirect("/admin");
            return;
        }

        response.sendRedirect("/admin");
    }
}

