package com.rowa.javalabee;

import com.rowa.javalabee.dao.RoleDAO;
import com.rowa.javalabee.models.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="roles", value="/roles")
public class RoleServlet extends HttpServlet {

    private final RoleDAO roleDAO = new RoleDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String idStr = request.getParameter("id");

        if ("edit".equals(action) && idStr != null) {
            long id = Long.parseLong(idStr);
            Role role = roleDAO.findById(id);
            request.setAttribute("role", role);
            request.getRequestDispatcher("/view/edit_role.jsp").forward(request, response);
            return;
        }

        if ("delete".equals(action) && idStr != null) {
            long id = Long.parseLong(idStr);
            roleDAO.delete(id);
            response.sendRedirect(request.getContextPath() + "/roles");
            return;
        }

        List<Role> roles = roleDAO.findAll();
        request.setAttribute("roles", roles);
        request.getRequestDispatcher("/view/role.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        boolean canEditMovies = "on".equals(request.getParameter("canEditMovies"));

        long id = idStr != null && !idStr.isEmpty() ? Long.parseLong(idStr) : 0;
        Role role = new Role();
        role.setId(id);
        role.setName(name);
        role.setCanEditMovies(canEditMovies);

        if (id > 0) roleDAO.update(role);
        else roleDAO.save(role);

        response.sendRedirect(request.getContextPath() + "/roles");
    }
}

