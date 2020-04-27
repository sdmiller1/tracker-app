package com.movieapp.controller;

import com.movieapp.model.Collection;
import com.movieapp.model.Role;
import com.movieapp.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserController {

    private final Logger logger = LogManager.getLogger(this.getClass());


    public String createNewUser(String firstName, String lastName, String username, String password) {

        GenericDao<User> userGenericDao = new GenericDao<>(User.class);
        GenericDao<Role> roleGenericDao = new GenericDao<>(Role.class);
        GenericDao<Collection> collectionGenericDao = new GenericDao<>(Collection.class);

        String message;

        if (userGenericDao.findByPropertyEqual("username", username).size() == 0) {
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
