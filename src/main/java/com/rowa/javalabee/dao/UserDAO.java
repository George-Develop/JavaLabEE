package com.rowa.javalabee.dao;

import com.rowa.javalabee.models.Role;
import com.rowa.javalabee.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        String sql = """
                SELECT u.*, r.id as r_id, r.name, r.can_edit_movies
                FROM users u
                JOIN roles r ON u.role_id = r.id
                """;

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                Role role = new Role(
                        rs.getLong("r_id"),
                        rs.getString("name"),
                        rs.getBoolean("can_edit_movies")
                );

                User user = new User(
                        rs.getLong("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        role
                );

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}
