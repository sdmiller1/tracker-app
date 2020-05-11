package com.movieapp.servlets;

import com.movieapp.controller.GenericDao;
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
        name = "BrowseMovies",
        urlPatterns = {"/browse", "/"}
)
public class BrowseMovies extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Movie> movies;

        GenericDao<Movie> dao = new GenericDao<>(Movie.class);

        movies = dao.getAll();
        request.setAttribute("collectionName", "All Movies");


        request.setAttribute("movies", movies);

        String url = "/browse.jsp";

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

}
