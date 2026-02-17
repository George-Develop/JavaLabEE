package com.rowa.javalabee.dao;

import com.rowa.javalabee.models.Movie;
import com.rowa.javalabee.models.User;
import com.rowa.javalabee.models.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {

    public List<Movie> findAll() {
        List<Movie> movies = new ArrayList<>();

        String sql = """
                SELECT m.*, 
                       u.id as u_id, u.first_name, u.last_name, u.phone, u.email,
                       r.id as r_id, r.name, r.can_edit_movies
                FROM movies m
                JOIN users u ON m.added_by = u.id
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
                        rs.getLong("u_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        role
                );

                Movie movie = new Movie(
                        rs.getLong("id"),
                        rs.getString("title"),
                        user,
                        rs.getString("genre"),
                        rs.getInt("release_year"),
                        rs.getString("description")
                );

                movies.add(movie);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movies;
    }
    public void save(Movie movie) {
        String sql = "INSERT INTO movies (title, genre, release_year, description, added_by) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getGenre());
            stmt.setInt(3, movie.getReleaseYear());
            stmt.setString(4, movie.getDescription());
            stmt.setLong(5, movie.getAddedBy().getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
