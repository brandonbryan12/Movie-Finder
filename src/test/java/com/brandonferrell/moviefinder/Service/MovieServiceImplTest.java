package com.brandonferrell.moviefinder.Service;

import com.brandonferrell.moviefinder.Client.MovieClient;
import com.brandonferrell.moviefinder.Model.Movie;
import com.brandonferrell.moviefinder.Model.Response.MovieDBMultiResponse;
import com.brandonferrell.moviefinder.Model.Response.MovieDBResponse;
import com.brandonferrell.moviefinder.Repository.MovieInsertRepository;
import com.brandonferrell.moviefinder.Repository.MovieRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class MovieServiceImplTest {

    @TestConfiguration
    static class MovieServiceImplTestContextConfiguration {

        @Bean
        public MovieService movieService() {
            return new MovieServiceImpl();
        }
    }

    @Autowired
    MovieService movieService;

    @MockBean
    private MovieRepository movieRepository;

    @MockBean
    private MovieClient movieClient;

    @MockBean
    private MovieInsertRepository movieInsertRepository;

    @Before
    public void setUp() {
        int id = 100;
        String query = "The+Hobbit";
        Movie hobbit = Movie.builder().id(id).build();

        // Contents of Response Entities
        MovieDBResponse movieDBResponse = new MovieDBResponse();
        movieDBResponse.setId(id);
        MovieDBMultiResponse movieDBMultiResponse = new MovieDBMultiResponse();
        movieDBMultiResponse.setResults(new ArrayList<MovieDBResponse>(){{
            add(movieDBResponse);
        }});

        // Response Entities
        ResponseEntity<MovieDBResponse> idResponseEntity = new ResponseEntity<>(movieDBResponse, HttpStatus.OK);
        ResponseEntity<MovieDBMultiResponse> queryResponseEntity = new ResponseEntity<>(movieDBMultiResponse, HttpStatus.OK);

        // Mock
        Mockito.when(movieClient.getMovieById(id)).thenReturn(idResponseEntity);
        Mockito.when(movieClient.getMoviesByQuery(query)).thenReturn(queryResponseEntity);
        Mockito.when(movieRepository.findById(hobbit.getId())).thenReturn(Optional.of(hobbit));
    }

    @Test
    public void whenValidId_thenMovieShouldBeFound() {
        int id = 100;
        Movie found = movieService.getMovieById(id);

        assertThat(found.getId()).isEqualTo(id);
    }

    @Test
    public void whenValidQuery_thenMoviesShouldBeFound() {
        String query = "The+Hobbit";
        List<Movie> found = movieService.getMoviesByQuery(query);

        assertThat(found.get(0).getId()).isEqualTo(100);
    }

    @Test
    public void whenMovieResponse_thenShouldTranslate() {

        // Given
        MovieDBResponse movieDBResponse = new MovieDBResponse();
        movieDBResponse.setId(101);
        movieDBResponse.setGenres(new ArrayList<MovieDBResponse.Genre>() {{
            add(new MovieDBResponse.Genre());
        }});
        movieDBResponse.setRelease_date("1987");
        movieDBResponse.setSpoken_languages(new ArrayList<MovieDBResponse.Language>() {{
            add(new MovieDBResponse.Language());
        }});
        movieDBResponse.setVote_average(5);
        movieDBResponse.setTitle("WIP");

        // When
        Movie translated = movieService.translateMovie(movieDBResponse);

        // Then
        assertEquals(translated.getId(), movieDBResponse.getId());
        assertEquals(translated.getGenres().size(), movieDBResponse.getGenres().size());
        assertEquals(translated.getYear(), movieDBResponse.getRelease_date());
        assertEquals(translated.getLanguages().size(), movieDBResponse.getSpoken_languages().size());
        assertEquals(translated.getRating(), movieDBResponse.getVote_average(), .001);
        assertEquals(translated.getName(), movieDBResponse.getTitle());
    }
}
