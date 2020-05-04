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
        name = "BrowseCollection",
        urlPatterns = {"/collection"}
)
public class BrowseCollection extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

// TODO: this page requires login and should redirct to browse if the collection isn't theirs. the collection functionallity should be removed from the browse page

        GenericDao<User> userGenericDao = new GenericDao<>(User.class);

        List<Movie> movies;

        String username = request.getRemoteUser();
        String collectionId = request.getParameter("id");

        // Send a user object to the jsp
        User user = userGenericDao.findByPropertyEqual("username", username).get(0);
        request.setAttribute("user", user);


        if (collectionId != null && collectionId.length() != 0) {
            MovieSearcher movieSearcher = new MovieSearcher();
            GenericDao<Collection> dao = new GenericDao<>(Collection.class);

            Collection collection = dao.getById(Integer.parseInt(collectionId));

            if (collection.getUser().getUsername().equals(username)) {
                movies = movieSearcher.findByCollectionId(Integer.parseInt(collectionId));

                request.setAttribute("collectionName", collection.getCollectionName());
                request.setAttribute("collectionId", collection.getId());

                request.setAttribute("movies", movies);
            } else {
                request.setAttribute("collectionName", "You do not have access to this collection.");
            }

            String url = "/browse.jsp";

            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("/browse");
        }


    }

}
