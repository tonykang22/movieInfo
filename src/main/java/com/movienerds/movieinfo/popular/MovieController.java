package com.movienerds.movieinfo.popular;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movienerds.movieinfo.popular.dto.MoviesDto;
import com.movienerds.movieinfo.popular.dto.PopularDto;
import com.movienerds.movieinfo.popular.dto.ResultsDto;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@Slf4j
@Controller
@NoArgsConstructor
public class MovieController {

    @Value("${api.key}")
    private String apiKey;

    private ObjectMapper objectmapper = new ObjectMapper();

    @ResponseBody
    @GetMapping("/movies")
    public MoviesDto movieList() {

        RestTemplate restTemplate = new RestTemplate();

        String urlStr = "https://api.themoviedb.org/3/movie/now_playing";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlStr)
                .queryParam("api_key", apiKey);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

        log.info("movieList has been called.");

        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, httpEntity, MoviesDto.class).getBody();
    }

    @ResponseBody
    @GetMapping("/movies/{movieId}")
    public PopularDto movieInfo(@PathVariable Integer movieId) throws IOException {
        StringBuilder result = new StringBuilder();
        String urlStr = "http://api.themoviedb.org/3/movie/" +
                movieId +
                "?api_key=" +
                apiKey;
        URL url = new URL(urlStr);

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");

        String messageBody = StreamUtils.copyToString(urlConnection.getInputStream(), StandardCharsets.UTF_8);
        PopularDto data = objectmapper.readValue(messageBody, PopularDto.class);
        log.info("moive info(id = {}) has been called.", movieId);

        return data;
    }

    @ResponseBody
    @GetMapping("/movies/populars")
    public PopularDto popularList(@RequestParam Integer page) throws IOException {

        String urlStr = "https://api.themoviedb.org/3/movie/popular" +
                "?api_key=" +
                apiKey +
                "&page=" + page;
        URL url = new URL(urlStr);

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");

        String messageBody = StreamUtils.copyToString(urlConnection.getInputStream(), StandardCharsets.UTF_8);
        PopularDto data = objectmapper.readValue(messageBody, PopularDto.class);
        log.info("popular list(page = {}) has been called.", page);

        return data;
    }

    @ResponseBody
    @GetMapping("/movies/populars/notdecidedyet")
    private ResultsDto getPopularMovieInfo(@RequestParam Integer page, @RequestParam Integer number) throws IOException {
        String urlStr = "https://api.themoviedb.org/3/movie/popular" +
                "?api_key=" +
                apiKey +
                "&page=" + page;
        URL url = new URL(urlStr);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        String messageBody = StreamUtils.copyToString(conn.getInputStream(), StandardCharsets.UTF_8);
        PopularDto movie = objectmapper.readValue(messageBody, PopularDto.class);
        ArrayList<ResultsDto> results = movie.getResults();

        ResultsDto resultsDto = results.get(number);
        String backdrop_path = resultsDto.getBackdrop_path();
        String title = resultsDto.getTitle();
        Integer popularity = resultsDto.getPopularity();

        log.info("popular movie (page = {}, whichOne = {}) was called.", page, number);

        return resultsDto;
    }

    @ResponseBody
    @GetMapping("/movies/{movieId}/images")
    public ImagesDto getImages(@PathVariable Integer movieId) throws IOException {
        String urlStr = "https://api.themoviedb.org/3/movie/" +
                movieId +
                "images?api_key=" +
                apiKey;
        URL url = new URL(urlStr);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        String messageBody = StreamUtils.copyToString(conn.getInputStream(), StandardCharsets.UTF_8);
        ImagesDto images = objectmapper.readValue(messageBody, ImageDto.class);

        return images;
    }

    @GetMapping("/")
    public String welcomePage() {
        return "movies/welcome";
    }
}

