package com.movieapp.servlets;

import com.movieapp.controller.GenericDao;
import com.movieapp.controller.MovieSearcher;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet (
        name = "BrowseCollection",
        urlPatterns = {"/collection"}
)
public class BrowseCollection extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        GenericDao<User> userGenericDao = new GenericDao<>(User.class);

        List<Movie> movies;

        String username = request.getRemoteUser();
        int collectionId = 0;

        try {
            collectionId = Integer.parseInt(request.getParameter("id"));
        } catch (Exception e) {
            logger.error(e);
        }

        // Put the user object in the session
        HttpSession session = request.getSession();
        User user = userGenericDao.findByPropertyEqual("username", username).get(0);
        session.setAttribute("user", user);

        if (collectionId != 0) {
            MovieSearcher movieSearcher = new MovieSearcher();
            GenericDao<Collection> dao = new GenericDao<>(Collection.class);

            Collection collection = dao.getById(collectionId);

            if (collection.getUser().getUsername().equals(username)) {
                movies = movieSearcher.findByCollectionId(collectionId);

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
            response.sendRedirect("browse");
        }


    }

}
