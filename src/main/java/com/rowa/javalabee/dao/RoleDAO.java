package com.rowa.javalabee.dao;

import com.rowa.javalabee.models.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO {

    public List<Role> findAll() {
        List<Role> roles = new ArrayList<>();
        String sql = "SELECT * FROM roles";

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Role role = new Role(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getBoolean("can_edit_movies")
                );
                roles.add(role);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roles;
    }

    public void save(Role role) {
        String sql = "INSERT INTO roles (name, can_edit_movies) VALUES (?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, role.getName());
            stmt.setBoolean(2, role.getCanEditMovies());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
