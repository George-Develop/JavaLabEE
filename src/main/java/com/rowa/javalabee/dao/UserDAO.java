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
    public void save(User user) {
        String sql = "INSERT INTO users (first_name, last_name, phone, email, role_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getPhone());
            stmt.setString(4, user.getEmail());
            stmt.setLong(5, user.getRoleId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
