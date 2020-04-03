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
        name = "UserPage",
        urlPatterns = {"/profile"}
)
public class UserPage extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        GenericDao<Movie> movieDao = new GenericDao<>(Movie.class);
        GenericDao<User> userDao = new GenericDao<>(User.class);

//      TODO: replace with user specific movies
        List<Movie> movies = movieDao.getAll();

        String username = request.getRemoteUser();

        User user = userDao.findByPropertyEqual("username", username).get(0);

        request.setAttribute("user", user);

        request.setAttribute("movies", movies);

        String url = "/profile.jsp";

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

}
