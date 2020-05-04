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

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("j_username");
        String password = request.getParameter("j_password");
//        TODO: record bluray dvd 4k stuff

        if (firstName != null && lastName != null && username != null && password != null) {
            String successMessage = userController.createNewUser(firstName, lastName, username, password);

            if (successMessage.equals("Success")) {
                request.login(username, password);

                String url = "browse";
                response.sendRedirect(url);
            } else if (successMessage.equals("Username taken")) {
                // returns the user to the sign up page and persists their form data
                request.setAttribute("errorMessage", "That username was already taken");
                request.setAttribute("username", username);
                request.setAttribute("firstName", firstName);
                request.setAttribute("lastName", lastName);

                String url = "signup.jsp";
                RequestDispatcher dispatcher = request.getRequestDispatcher(url);
                dispatcher.forward(request, response);
            }
        } else {
            String url = "signup.jsp";

            request.setAttribute("errorMessage", "There was an error submitting the form");

            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }



    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = "/signup.jsp";

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

}
