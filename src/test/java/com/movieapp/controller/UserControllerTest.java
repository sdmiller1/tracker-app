package com.movieapp.controller;

import com.movieapp.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UserControllerTest {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private GenericDao<User> genericDao;
    private UserController userController = new UserController();

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");

        genericDao = new GenericDao<>(User.class);
    }

    @Test
    void createNewUserSuccess() {
        String username = "Jeff123";
        String password = "password";
        String firstName = "Jeff";
        String lastName = "Smith";

        String response = userController.createNewUser(firstName, lastName, username, password);

        assertEquals("Success", response);

        User user = genericDao.findByPropertyEqual("username", username).get(0);

        assertEquals(firstName, user.getFirstName());
        assertEquals(2, user.getCollections().size());
    }

    @Test
    void createNewUserUsernameTaken() {
        String username = "astrobob";
        String password = "password";
        String firstName = "Jeff";
        String lastName = "Smith";

        String response = userController.createNewUser(firstName, lastName, username, password);

        assertEquals("Username taken", response);

        User user = genericDao.findByPropertyEqual("username", username).get(0);

        assertNotEquals(firstName, user.getFirstName());
    }
}
