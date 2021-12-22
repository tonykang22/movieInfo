package movienerds.movielist.resources;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MovieRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    private MovieRepository movieRepository;

    @Test
    void 영화저장() {
        //given
        Movie movie = createMovie();

        //when
        movieRepository.save(movie);
        Movie savedMovie = movieRepository.findOne(movie.getId());

        //then
        assertThat(savedMovie.getTitle()).isEqualTo(movie.getTitle());
        assertThat(savedMovie.getPopularity()).isEqualTo(movie.getPopularity());
        assertThat(savedMovie).isEqualTo(movie);
    }

    private Movie createMovie() {
        Movie movie = new Movie();
        movie.setTitle("스파이더맨 : 노웨어홈");
        movie.setRelease_date("2021-12-18");
        movie.setPopularity(8.9f);
        movie.setVote_average(9.0f);
        movie.setPoster_path("home");
        movie.setVideo(true);
        return movie;
    }
}