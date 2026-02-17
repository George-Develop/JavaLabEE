package com.rowa.javalabee;

import com.rowa.javalabee.dao.UserDAO;
import com.rowa.javalabee.models.Movie;
import com.rowa.javalabee.models.Role;
import com.rowa.javalabee.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="users",value="/users")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        List<User> users = userDAO.findAll();
        request.setAttribute("users", users);

        request.getRequestDispatcher("/view/user.jsp").forward(request, response);
    }
}
