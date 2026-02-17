package com.rowa.javalabee;

import com.rowa.javalabee.dao.RoleDAO;
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

    private final UserDAO userDAO = new UserDAO();
    private final RoleDAO roleDAO = new RoleDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userDAO.findAll();
        List<Role> roles = roleDAO.findAll(); // для формы добавления
        request.setAttribute("users", users);
        request.setAttribute("roles", roles);

        request.getRequestDispatcher("/view/user.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Получаем параметры из формы
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String roleIdStr = request.getParameter("roleId");

        long roleId = 0;
        try {
            roleId = Long.parseLong(roleIdStr);
        } catch (NumberFormatException e) {
            e.printStackTrace(); // или обработка ошибки
        }

        Role role = roleDAO.findById(roleId); // метод должен возвращать Role по ID

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhone(phone);
        user.setEmail(email);
        user.setRole(role);
        user.setRoleId(role.getId());

        userDAO.save(user); // метод save должен быть реализован

        response.sendRedirect(request.getContextPath() + "/users");
    }
}

