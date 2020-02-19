package com.movieapp.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
@Entity(name = "movie_app")
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    @Column(name = "username")
    private String username;

    /**
     * empty constructor
     */
    public User() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}