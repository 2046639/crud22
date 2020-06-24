package com.crud.usermanagement.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        final HttpSession session = req.getSession();
//        session.removeAttribute("loginUser");
        session.invalidate();
//        resp.sendRedirect(super.getServletContext().getContextPath());
        resp.sendRedirect("/");
    }
}