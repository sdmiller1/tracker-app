package com.movieapp.controller;

import com.movieapp.model.*;
import com.movieapp.model.Collection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Collection dao test.
 */
class CollectionDaoTest {

    private GenericDao<Collection> dao;
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");

        dao = new GenericDao<Collection>(Collection.class);
    }

    /**
     * Gets by id.
     */
    @Test
    void getById() {
        Collection collection = dao.getById(1);
        assertEquals(1, collection.getId());
        assertEquals("Personal Collection", collection.getCollectionName());
    }

    /**
     * Save or update.
     */
    @Test
    void saveOrUpdate() {
        Collection collection = (Collection)dao.getById(1);
        collection.setCollectionName("Netflix");
        dao.saveOrUpdate(collection);
        Collection updatedCollection = (Collection)dao.getById(1);
        assertEquals(collection, updatedCollection);
    }

    /**
     * Insert.
     */
    @Test
    void insert() {
        User user = new GenericDao<User>(User.class).getById(1);

        Collection collection = new Collection("Netflix", user);

        int id = dao.insert(collection);
        assertEquals(collection, dao.getById(id));
    }

    /**
     * Delete.
     */
    @Test
    void delete() {
        Collection collection = dao.getById(3);
        dao.delete(collection);
        assertNull(dao.getById(3));
    }

    /**
     * Gets all.
     */
    @Test
    void getAll() {
        List<Collection> collections = dao.getAll();
        assertEquals(8, collections.size());
    }

    /**
     * Gets collections by property equals.
     */
    @Test
    void getCollectionsByPropertyEquals() {
        List<Collection> collections = dao.findByPropertyEqual("collectionName", "Personal Collection");
        assertEquals(4, collections.size());
    }

    /**
     * Gets collections by property equals map.
     */
    @Test
    void getCollectionsByPropertyEqualsMap() {
        Map<String, Object> propertiesMap = new HashMap<String, Object>();
        propertiesMap.put("collectionName", "Personal Collection");

        List<Collection> collections = dao.findByPropertyEqual(propertiesMap);
        assertEquals(4, collections.size());
    }

    /**
     * Gets movies from collection.
     */
    @Test
    void getMoviesFromCollection() {
        Collection collection = dao.getById(1);
        List<Movie> movies =  collection.getMovies();

        assertEquals(3, movies.size());
    }
}