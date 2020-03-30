package com.movieapp.servlets;

import com.movieapp.controller.GenericDao;
import com.movieapp.controller.MovieSearcher;
import com.movieapp.model.Movie;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet (
        name = "MoviePage",
        urlPatterns = {"/movie"}
)
public class MoviePage extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        GenericDao<Movie> dao = new GenericDao<>(Movie.class);
//      TODO: replace with related/similar movies
        List<Movie> movies = dao.getAll();

        MovieSearcher searcher = new MovieSearcher();

        if (request.getParameter("search") != null && request.getParameter("search").length() != 0) {
            String title = request.getParameter("search");

            List<Movie> searchResults = searcher.findByTitle(title);

            request.setAttribute("searchResults", searchResults);
        }

        request.setAttribute("movies", movies);

        String url = "/movie.jsp";

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

}
