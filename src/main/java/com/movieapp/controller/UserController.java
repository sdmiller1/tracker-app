package com.movieapp.controller;

import com.movieapp.model.Collection;
import com.movieapp.model.Role;
import com.movieapp.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Contains methods for interacting with User objects
 */
public class UserController {

    private final Logger logger = LogManager.getLogger(this.getClass());


    /**
     * Create new user
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @param username  the username
     * @param password  the password
     * @return success or failure message
     */
    public String createNewUser(String firstName, String lastName, String username, String password) {
//TODO: include the disc type booleans
        GenericDao<User> userGenericDao = new GenericDao<>(User.class);
        GenericDao<Role> roleGenericDao = new GenericDao<>(Role.class);
        GenericDao<Collection> collectionGenericDao = new GenericDao<>(Collection.class);

        String message;

        if (userGenericDao.findByPropertyEqual("username", username).isEmpty()) {
            User user = new User(firstName, lastName, username, password, false, false, false);
            Role role = new Role("user", user);

            Collection personalCollection = new Collection("Personal Collection", user);
            Collection watchLater = new Collection("Watch Later", user);

            userGenericDao.insert(user);
            roleGenericDao.insert(role);
            collectionGenericDao.insert(personalCollection);
            collectionGenericDao.insert(watchLater);

            message = "Success";
        } else {
            message = "Username taken";
        }

        return message;
    }
}
