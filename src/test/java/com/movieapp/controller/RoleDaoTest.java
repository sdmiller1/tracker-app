package com.movieapp.controller;

import com.movieapp.model.*;
import com.movieapp.model.Collection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class RoleDaoTest {

    GenericDao<Role> dao;
    private final Logger logger = LogManager.getLogger(this.getClass());

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");

        dao = new GenericDao<Role>(Role.class);
    }

    @Test
    void getById() {
        Role role = (Role)dao.getById(1);
        assertEquals(1, role.getId());
        assertEquals("user", role.getRoleName());
    }

    @Test
    void saveOrUpdate() {
        Role role = (Role)dao.getById(1);
        role.setRoleName("admin");
        dao.saveOrUpdate(role);
        Role updatedRole = (Role)dao.getById(1);
        assertEquals(role, updatedRole);
    }

    @Test
    void insert() {
        User user = new GenericDao<>(User.class).getById(1);

        Role role = new Role("Admin", user);

        int id = dao.insert(role);
        assertEquals(role, dao.getById(id));
    }

    @Test
    void delete() {
        Role role = dao.getById(3);
        dao.delete(role);
        assertNull(dao.getById(3));
    }

    @Test
    void getAll() {
        List<Role> roles = dao.getAll();
        assertEquals(5, roles.size());
    }

    @Test
    void getRolesByPropertyEquals() {
        List<Role> roles = dao.findByPropertyEqual("roleName", "user");
        assertEquals(4, roles.size());
    }

    @Test
    void getRolesByPropertyEqualsMap() {
        Map<String, Object> propertiesMap = new HashMap<String, Object>();
        propertiesMap.put("roleName", "admin");

        List<Role> roles = dao.findByPropertyEqual(propertiesMap);
        assertEquals(1, roles.size());
    }
}