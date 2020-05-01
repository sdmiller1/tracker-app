package com.movieapp.servlets;

import com.movieapp.controller.GenericDao;
import com.movieapp.controller.UserController;
import com.movieapp.model.Collection;
import com.movieapp.model.Role;
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

        UserController userController = new UserController();

        String url = "signup.jsp";

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
//        TODO: record bluray dvd 4k stuff
        String successMessage = userController.createNewUser(firstName, lastName, username, password);

        if (successMessage.equals("Success")) {
            url = "profile";
        } else if (successMessage.equals("Username taken")) {
            request.setAttribute("errorMessage", "That username was already taken");
//            TODO: need to re-display the user data if the username was taken
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
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
