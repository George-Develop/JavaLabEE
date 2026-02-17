package com.rowa.javalabee;

import com.rowa.javalabee.dao.MovieDAO;
import com.rowa.javalabee.models.Movie;
import com.rowa.javalabee.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="movies", value="/movies")
public class MovieServlet extends HttpServlet {

    private final MovieDAO movieDAO = new MovieDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String idStr = request.getParameter("id");

        if ("edit".equals(action) && idStr != null) {
            long id = Long.parseLong(idStr);
            Movie movie = movieDAO.findById(id);
            request.setAttribute("movie", movie);
            request.getRequestDispatcher("/view/edit_movie.jsp").forward(request, response);
            return;
        }

        if ("delete".equals(action) && idStr != null) {
            long id = Long.parseLong(idStr);
            movieDAO.delete(id);
            response.sendRedirect(request.getContextPath() + "/movies");
            return;
        }

        List<Movie> movies = movieDAO.findAll();
        request.setAttribute("movies", movies);
        request.getRequestDispatcher("/view/movie.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String title = request.getParameter("title");
        String genre = request.getParameter("genre");
        String releaseYearStr = request.getParameter("releaseYear");
        String description = request.getParameter("description");

        int releaseYear = Integer.parseInt(releaseYearStr);
        long id = idStr != null && !idStr.isEmpty() ? Long.parseLong(idStr) : 0;

        Movie movie = new Movie();
        movie.setId(id);
        movie.setTitle(title);
        movie.setGenre(genre);
        movie.setReleaseYear(releaseYear);
        movie.setDescription(description);
        movie.setAddedBy(new User(1L, "Admin", "User", "", "", null));

        if (id > 0) {
            movieDAO.update(movie);
        } else {
            movieDAO.save(movie);
        }

        response.sendRedirect(request.getContextPath() + "/movies");
    }
}

