package com.movieapp.controller;

import com.movieapp.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class UserDaoTest {

    UserDao dao;

    @BeforeEach
    void setUp() {

        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");

        dao = new UserDao();
    }

    @Test
    void getById() {
        User user = dao.getById(1);
        assertEquals(1, user.getId());
    }

    @Test
    void saveOrUpdate() {
        User userToUpdate = dao.getById(1);
        userToUpdate.setUsername("Matt Damon");
        dao.saveOrUpdate(userToUpdate);
        assertEquals("Matt Damon", dao.getById(1).getUsername());
    }

    @Test
    void insert() {
        User user = new User();
        user.setUsername("Matt Damon");
        int id = dao.insert(user);
        assertEquals("Matt Damon", dao.getById(id).getUsername());
    }

    @Test
    void delete() {
        User user = dao.getById(1);
        dao.delete(user);
        assertNull(dao.getById(1));
    }

    @Test
    void getAll() {
        List<User> users = dao.getAll();
        assertEquals(4, users.size());
    }
}
