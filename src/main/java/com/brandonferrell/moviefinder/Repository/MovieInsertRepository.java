package com.brandonferrell.moviefinder.Repository;

import com.brandonferrell.moviefinder.Model.Movie;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class MovieInsertRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void insertMovie(Movie movie) {
        entityManager.persist(movie);
        entityManager.flush();
    }

}
