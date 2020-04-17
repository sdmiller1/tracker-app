package com.movieapp.servlets;

import com.movieapp.controller.GenericDao;
import com.movieapp.model.Collection;
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
import java.util.Set;

@WebServlet (
        name = "UserPage",
        urlPatterns = {"/profile"}
)
public class UserPage extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        GenericDao<User> userGenericDao = new GenericDao<>(User.class);


        String username = request.getRemoteUser();

        User user = userGenericDao.findByPropertyEqual("username", username).get(0);

        Set<Collection> collections = user.getCollections();

        request.setAttribute("user", user);

        request.setAttribute("collections", collections);

        String url = "/profile.jsp";

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

}
