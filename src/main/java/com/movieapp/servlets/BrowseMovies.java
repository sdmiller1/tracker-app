package com.movieapp.servlets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieapp.controller.GenericDao;
import com.movieapp.controller.MovieSearcher;
import com.movieapp.controller.UserController;
import com.movieapp.model.Collection;
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
import java.util.Set;

@WebServlet (
        name = "BrowseMovies",
        urlPatterns = {"/browse"}
)
public class BrowseMovies extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        GenericDao<User> userGenericDao = new GenericDao<>(User.class);

        String username = request.getRemoteUser();

        if (username != null && username.length() != 0) {
            User user = userGenericDao.findByPropertyEqual("username", username).get(0);

            request.setAttribute("user", user);
        }

        List<Movie> movies;
        String collectionId = request.getParameter("collection");

        if (collectionId != null && collectionId.length() != 0) {
            MovieSearcher movieSearcher = new MovieSearcher();
            GenericDao<Collection> dao = new GenericDao<>(Collection.class);

            Collection collection = dao.getById(Integer.parseInt(collectionId));

            movies = movieSearcher.findByCollectionId(Integer.parseInt(collectionId));

            String collectionName = collection.getUser().getUsername() + "'s " + collection.getCollectionName() + " movies";

            request.setAttribute("collectionName", collectionName);
            request.setAttribute("collectionId", collection.getId());
        } else {
            GenericDao<Movie> dao = new GenericDao<>(Movie.class);

            movies = dao.getAll();
            request.setAttribute("collectionName", "All Movies");
        }


        request.setAttribute("movies", movies);

        String url = "/browse.jsp";

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

}
