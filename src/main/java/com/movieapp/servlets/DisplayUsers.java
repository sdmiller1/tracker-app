package com.movieapp.servlets;

import com.movieapp.controller.GenericDao;
import com.movieapp.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet (
        name = "DisplayUsers",
        urlPatterns = {"/displayUsers"}
)
public class DisplayUsers extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        GenericDao<User> dao = new GenericDao<>(User.class);

        List<User> users = dao.getAll();

        request.setAttribute("results", users);

        String url = "/displayUsers.jsp";

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

}
