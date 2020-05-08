package com.movieapp.servlets;

import com.movieapp.controller.GenericDao;
import com.movieapp.controller.MovieSearcher;
import com.movieapp.model.Movie;
import com.movieapp.model.User;
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

        GenericDao<Movie> movieGenericDao = new GenericDao<>(Movie.class);
//      TODO: replace with related/similar movies
        List<Movie> movies = movieGenericDao.getAll();


        MovieSearcher searcher = new MovieSearcher();

        // display movies based on title search query
        if (request.getParameter("search") != null && request.getParameter("search").length() != 0) {
            String title = request.getParameter("search");

            List<Movie> searchResults = searcher.findByTitle(title);
// TODO: what to display if no search result was found
            request.setAttribute("searchResults", searchResults);
            request.setAttribute("searchTerm", title);
        }

        // Display a movie based on IMDB ID
        if (request.getParameter("id") != null && request.getParameter("id").length() != 0) {
            String id = request.getParameter("id");

            Movie movie = searcher.findById(id);

            request.setAttribute("movie", movie);
        }

        request.setAttribute("movies", movies);

        String url = "/movie.jsp";

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

}
