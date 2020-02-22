package com.movieapp.controller;

import com.movieapp.model.Rating;
import com.movieapp.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


public class RatingDao {

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * Get rating by id
     */
    public Rating getById(int id) {

        Session session = sessionFactory.openSession();
        Rating rating = session.get(Rating.class, id);
        session.close();

        return rating;
    }

    /**
     * update rating
     * @param rating Rating to be inserted or updated
     */
    public void saveOrUpdate(Rating rating) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(rating);
        transaction.commit();
        session.close();
    }

    /**
     * update Rating
     * @param rating Rating to be inserted or updated
     */
    public int insert(Rating rating) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(rating);
        transaction.commit();
        session.close();
        return id;
    }

    /**
     * Delete a rating
     * @param rating Rating to be deleted
     */
    public void delete(Rating rating) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(rating);
        transaction.commit();
        session.close();
    }


    /** Return a list of all ratings
     *
     * @return All Ratings
     */
    public List<Rating> getAll() {

        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Rating> query = builder.createQuery( Rating.class );
        Root<Rating> root = query.from( Rating.class );
        List<Rating> rating = session.createQuery( query ).getResultList();

        session.close();

        return rating;
    }



}