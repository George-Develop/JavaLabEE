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

@WebServlet(name="movies",value="/movies")
public class MovieServlet extends HttpServlet {

    private final MovieDAO movieDAO = new MovieDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MovieDAO movieDAO = new MovieDAO();
        List<Movie> movies = movieDAO.findAll();
        request.setAttribute("movies", movies);

        request.getRequestDispatcher("view/movie.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Получаем параметры из формы
        String title = request.getParameter("title");
        String genre = request.getParameter("genre");
        String releaseYearStr = request.getParameter("releaseYear");
        String description = request.getParameter("description");

        int releaseYear = 0;
        try {
            releaseYear = Integer.parseInt(releaseYearStr);
        } catch (NumberFormatException e) {
            e.printStackTrace(); // или логика обработки ошибки
        }

        // Здесь временно добавим "пользователя по умолчанию"
        User defaultUser = new User(1L, "Admin", "User", "", "", null);

        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setGenre(genre);
        movie.setReleaseYear(releaseYear);
        movie.setDescription(description);
        movie.setAddedBy(defaultUser);

        movieDAO.save(movie); // Метод save должен быть реализован в MovieDAO

        // После добавления перенаправляем обратно на список
        response.sendRedirect(request.getContextPath() + "/movies");
    }
}
