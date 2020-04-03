package com.movieapp.controller;

import com.movieapp.model.*;
import com.movieapp.model.Collection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CollectionDaoTest {

    GenericDao<Collection> dao;
    private final Logger logger = LogManager.getLogger(this.getClass());

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");

        dao = new GenericDao<Collection>(Collection.class);
    }

    @Test
    void getById() {
        Collection collection = (Collection)dao.getById(1);
        assertEquals(1, collection.getId());
        assertEquals("personal", collection.getCollectionName());
    }

    @Test
    void saveOrUpdate() {
        Collection collection = (Collection)dao.getById(1);
        collection.setCollectionName("Netflix");
        dao.saveOrUpdate(collection);
        Collection updatedCollection = (Collection)dao.getById(1);
        assertEquals(collection, updatedCollection);
    }

    @Test
    void insert() {
        Set<User> users = new HashSet<>();
        Set<MovieCollection> moviesCollections = new HashSet<>();
        users.add(new GenericDao<User>(User.class).getById(1));
        Collection collection = new Collection("Netflix", users);

        int id = dao.insert(collection);
        assertEquals(collection, dao.getById(id));
    }

    @Test
    void delete() {
        Collection collection = dao.getById(3);
        dao.delete(collection);
        assertNull(dao.getById(3));
    }

    @Test
    void getAll() {
        List<Collection> collections = dao.getAll();
        assertEquals(4, collections.size());
    }

    @Test
    void getRatingsByPropertyEquals() {
        List<Collection> collections = dao.findByPropertyEqual("collectionType", "personal");
        assertEquals(4, collections.size());
    }

    @Test
    void getRatingsByPropertyEqualsMap() {
        Map<String, Object> propertiesMap = new HashMap<String, Object>();
        propertiesMap.put("collectionType", "personal");

        List<Collection> collections = dao.findByPropertyEqual(propertiesMap);
        assertEquals(4, collections.size());
    }
}