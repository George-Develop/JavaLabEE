package com.rowa.javalabee.dao;

import com.rowa.javalabee.models.Movie;
import com.rowa.javalabee.models.User;
import com.rowa.javalabee.models.Role;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MovieDAOTest {

    private MovieDAO movieDAO;

    @BeforeAll
    void setupDatabase() throws Exception {
        movieDAO = new MovieDAO() {
            protected Connection getConnection() throws Exception {
                return TestConnectionFactory.getConnection();
            }
        };

        try (Connection conn = TestConnectionFactory.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("CREATE TABLE roles (id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50), can_edit_movies BOOLEAN)");
            stmt.execute("CREATE TABLE users (id BIGINT AUTO_INCREMENT PRIMARY KEY, first_name VARCHAR(50), last_name VARCHAR(50), phone VARCHAR(20), email VARCHAR(50), role_id BIGINT, FOREIGN KEY(role_id) REFERENCES roles(id))");
            stmt.execute("CREATE TABLE movies (id BIGINT AUTO_INCREMENT PRIMARY KEY, title VARCHAR(100), genre VARCHAR(50), release_year INT, description VARCHAR(255), added_by BIGINT, FOREIGN KEY(added_by) REFERENCES users(id))");

            stmt.execute("INSERT INTO roles(name, can_edit_movies) VALUES('Admin', TRUE)");
            stmt.execute("INSERT INTO users(first_name,last_name,phone,email,role_id) VALUES('John','Doe','123','john@example.com',1)");
        }
    }

    @Test
    void testSaveAndFindById() throws Exception {
        User user = new User(1L, "John", "Doe", "123", "john@example.com", new Role(1L, "Admin", true));
        Movie movie = new Movie();
        movie.setTitle("Test Movie");
        movie.setGenre("Action");
        movie.setReleaseYear(2023);
        movie.setDescription("Test Description");
        movie.setAddedBy(user);

        movieDAO.save(movie);

        List<Movie> all = movieDAO.findAll();
        assertEquals(1, all.size());

        Movie found = all.get(0);
        assertEquals("Test Movie", found.getTitle());
        assertEquals("Action", found.getGenre());
    }

    @Test
    void testUpdate() throws Exception {
        Movie movie = movieDAO.findAll().get(0);
        movie.setTitle("Updated Title");
        movieDAO.update(movie);

        Movie updated = movieDAO.findById(movie.getId());
        assertEquals("Updated Title", updated.getTitle());
    }

    @Test
    void testDelete() throws Exception {
        Movie movie = movieDAO.findAll().get(0);
        movieDAO.delete(movie.getId());

        List<Movie> all = movieDAO.findAll();
        assertTrue(all.isEmpty());
    }
}
