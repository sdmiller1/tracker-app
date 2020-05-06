package com.movieapp.api;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Sets the base path for all of the services
 */
@ApplicationPath("/services")
public class ServicesRouting extends Application {

    //The method returns a non-empty collection with classes, that must be included in the published JAX-RS application
    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add(MovieService.class );
        h.add(CollectionService.class);
        h.add(RatingService.class);
        return h;
    }
}