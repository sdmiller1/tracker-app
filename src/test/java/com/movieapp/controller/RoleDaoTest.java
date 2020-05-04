package com.movieapp.controller;

import com.movieapp.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Role class
 */
class RoleDaoTest {

    private GenericDao<Role> dao;
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");

        dao = new GenericDao<Role>(Role.class);
    }

    /**
     * Gets by id.
     */
    @Test
    void getById() {
        Role role = dao.getById(1);
        assertEquals(1, role.getId());
        assertEquals("user", role.getRoleName());
    }

    /**
     * Save or update.
     */
    @Test
    void saveOrUpdate() {
        Role role = (Role)dao.getById(1);
        role.setRoleName("admin");
        dao.saveOrUpdate(role);
        Role updatedRole = dao.getById(1);
        assertEquals(role, updatedRole);
    }

    /**
     * Insert.
     */
    @Test
    void insert() {
        User user = new GenericDao<>(User.class).getById(1);

        Role role = new Role("Admin", user);

        int id = dao.insert(role);
        assertEquals(role, dao.getById(id));
    }

    /**
     * Delete.
     */
    @Test
    void delete() {
        Role role = dao.getById(3);
        dao.delete(role);
        assertNull(dao.getById(3));
    }

    /**
     * Gets all.
     */
    @Test
    void getAll() {
        List<Role> roles = dao.getAll();
        assertEquals(5, roles.size());
    }

    /**
     * Gets roles by property equals.
     */
    @Test
    void getRolesByPropertyEquals() {
        List<Role> roles = dao.findByPropertyEqual("roleName", "user");
        assertEquals(4, roles.size());
    }

    /**
     * Gets roles by property equals map.
     */
    @Test
    void getRolesByPropertyEqualsMap() {
        Map<String, Object> propertiesMap = new HashMap<String, Object>();
        propertiesMap.put("roleName", "admin");

        List<Role> roles = dao.findByPropertyEqual(propertiesMap);
        assertEquals(1, roles.size());
    }
}