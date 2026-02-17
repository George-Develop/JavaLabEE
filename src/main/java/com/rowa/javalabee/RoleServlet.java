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

@WebServlet(name = "roles", value = "/roles")
public class RoleServlet extends HttpServlet {
    private final RoleDAO roleDAO = new RoleDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RoleDAO roleDAO = new RoleDAO();
        List<Role> roles = roleDAO.findAll();
        request.setAttribute("roles", roles);

        request.getRequestDispatcher("/view/role.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Получаем параметры из формы
        String name = request.getParameter("name");
        String canEditMoviesStr = request.getParameter("canEditMovies");

        boolean canEditMovies = "on".equals(canEditMoviesStr); // checkbox возвращает "on", если выбран

        Role role = new Role();
        role.setName(name);
        role.setCanEditMovies(canEditMovies);

        roleDAO.save(role); // Метод save должен быть реализован в RoleDAO

        // После добавления перенаправляем обратно на список ролей
        response.sendRedirect(request.getContextPath() + "/roles");
    }
}
