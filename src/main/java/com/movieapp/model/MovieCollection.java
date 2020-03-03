package com.movieapp.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity(name = "MoviesCollections")
@Table(name = "MoviesCollections")
public class MovieCollection {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    @ManyToOne
    private Collection collection;

    @ManyToOne
    private Movie movie;

    @Column(name = "hasDvd")
    private boolean hasDvd;

    @Column(name = "hasBluRay")
    private boolean hasBluRay;

    @Column(name = "has4k")
    private boolean has4k;

}
