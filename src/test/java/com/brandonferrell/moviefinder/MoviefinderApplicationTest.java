package com.brandonferrell.moviefinder;

import com.brandonferrell.moviefinder.Controller.MovieController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MoviefinderApplicationTest {

	@Autowired
	MovieController movieController;

	@Test
	public void contextLoads() {
		assertThat(movieController).isNotNull();
	}

}
