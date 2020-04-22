package com.movieapp.servlets;

import com.movieapp.controller.GenericDao;
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
        name = "Signup",
        urlPatterns = {"/signup"}
)
public class Signup extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = "signup.jsp";

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        GenericDao<User> userGenericDao = new GenericDao<>(User.class);

        if (userGenericDao.findByPropertyEqual("username", username).size() == 0) {
            User user = new User(firstName, lastName, username, password, false, false, false);
            userGenericDao.insert(user);

            url = "profile";
        } else {
//            TODO: error message that username was taken
        }

        response.sendRedirect(url);

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = "/signup.jsp";

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

}
