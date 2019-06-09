package com.brandonferrell.moviefinder.Repository;

import com.brandonferrell.moviefinder.Model.Genre;
import com.brandonferrell.moviefinder.Model.Language;
import com.brandonferrell.moviefinder.Model.Movie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MovieRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void whenFindById_thenReturnMovie() {

        // Given
        Movie movie = Movie.builder()
                .id(100)
                .name("The Hobbit")
                .genres(new ArrayList<>(Arrays.asList(new Genre("Adventure"))))
                .languages(new ArrayList<>(Arrays.asList(new Language("en"))))
                .rating(10)
                .year("1970")
                .build();
        entityManager.persist(movie);
        entityManager.flush();

        // When
        Movie found = movieRepository.getMovieById(100);

        // Then
        assertThat(found.getId()).isEqualTo(movie.getId());
    }

}
