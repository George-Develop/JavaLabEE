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

@WebServlet(name="users", value="/users")
public class UserServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();
    private final RoleDAO roleDAO = new RoleDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String idStr = request.getParameter("id");

        if ("edit".equals(action) && idStr != null) {
            long id = Long.parseLong(idStr);
            User user = userDAO.findById(id);
            List<Role> roles = roleDAO.findAll();
            request.setAttribute("user", user);
            request.setAttribute("roles", roles);
            request.getRequestDispatcher("/view/edit_user.jsp").forward(request, response);
            return;
        }

        if ("delete".equals(action) && idStr != null) {
            long id = Long.parseLong(idStr);
            userDAO.delete(id);
            response.sendRedirect(request.getContextPath() + "/users");
            return;
        }

        List<User> users = userDAO.findAll();
        List<Role> roles = roleDAO.findAll();
        request.setAttribute("users", users);
        request.setAttribute("roles", roles);
        request.getRequestDispatcher("/view/user.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        long roleId = Long.parseLong(request.getParameter("roleId"));

        Role role = roleDAO.findById(roleId);

        User user = new User();
        if (idStr != null && !idStr.isEmpty()) user.setId(Long.parseLong(idStr));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhone(phone);
        user.setEmail(email);
        user.setRole(role);
        user.setRoleId(role.getId());

        if (user.getId() > 0) userDAO.update(user);
        else userDAO.save(user);

        response.sendRedirect(request.getContextPath() + "/users");
    }
}


