package com.movienerds.movieinfo.popular;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

//    @Transactional
//    public void saveMovie(Movie movie) {
//        movieRepository.save(movie);
//    }

//    @Transactional
//    public Movie updateMovie(Long popularId, String title, String release_date, float popularity, float vote_average, String poster_path, boolean video) {
//        Movie findMovie = movieRepository.findOne(popularId);
//        findMovie.setTitle(title);
//        findMovie.setRelease_date(release_date);
//        findMovie.setPopularity(popularity);
//        findMovie.setVote_average(vote_average);
//        findMovie.setPoster_path(poster_path);
//        findMovie.setVideo(video);
//        return findMovie;
//    }
//
//    public List<Movie> findMovies() {
//        return movieRepository.findAll();
//    }
//
//    public Movie findOne(Long popularId) {
//        return movieRepository.findOne(popularId);
//    }
}
