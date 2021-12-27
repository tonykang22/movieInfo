package com.movienerds.movieinfo.popular;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movienerds.movieinfo.popular.dto.MovieDto;
import com.movienerds.movieinfo.popular.dto.ResultsDto;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@Slf4j
@Service
@RequestMapping("/api")
@NoArgsConstructor
public class MovieController {

    @Value("${api.key}")
    private String apiKey;

    private ObjectMapper objectmapper = new ObjectMapper();

    @ResponseBody
    @GetMapping("/movie/{movieId}")
    public String callApi(@PathVariable String movieId) throws IOException {
        StringBuilder result = new StringBuilder();
        String urlStr = "http://api.themoviedb.org/3/movie/" +
                movieId +
                "?api_key=" +
                apiKey;
        URL url = new URL(urlStr);

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");

        BufferedReader br;
        br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

        String returnLine;

        while ((returnLine = br.readLine()) != null) {
            result.append(returnLine + "\n\r");
        }

        urlConnection.disconnect();

        return result.toString();
    }

        /*
        JSONObject obj = new JSONObject();
        try {
            JSONArray jArray = new JSONArray();
            for (int i = 0; i < data.getResults().size(); i++) {
                JSONObject jObject = new JSONObject();
                jObject.put("adult", data.getResults().get(i).getAdult());
                .
                .
                .

            }
        }
        */

    @GetMapping("/popular")
    public String popularList() {
        return "api/movie/popular";
    }

    //String version
    @ResponseBody
    @GetMapping("/popular/{page}")
    public MovieDto popularList(@PathVariable Integer page) throws IOException {

        String urlStr = "https://api.themoviedb.org/3/movie/popular" +
                "?api_key=" +
                apiKey +
                "&page=" + page;
        URL url = new URL(urlStr);

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");

        String messageBody = StreamUtils.copyToString(urlConnection.getInputStream(), StandardCharsets.UTF_8);
        MovieDto data = objectmapper.readValue(messageBody, MovieDto.class);
        log.info("popular list(page = {}) has been called.", page);

        return data;
    }

    @ResponseBody
    @GetMapping("/popular/v2/{page}")
    public MovieDto popularListV2(@PathVariable Integer page) {

        RestTemplate restTemplate = new RestTemplate();

        String urlStr = "https://api.themoviedb.org/3/movie/popular";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlStr)
                .queryParam("api_key", apiKey).query("&" + page);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

        log.info("popularListV2 has been called.");

        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, httpEntity, MovieDto.class).getBody();
    }

    @ResponseBody
    @GetMapping("/popular/v3/{page}")
    public String popularListV3(@PathVariable Integer page) throws IOException {

        String urlStr = "https://api.themoviedb.org/3/movie/popular" +
                "?api_key=" +
                apiKey +
                "&" + page;
        URL url = new URL(urlStr);

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");

        String messageBody = StreamUtils.copyToString(urlConnection.getInputStream(), StandardCharsets.UTF_8);

        log.info("popularListV3 has been called.");

        return messageBody;
    }

    //현재는 1페이지 것만 챙긴,, => 모든 영화 db를 미리 다 받아놓아야 하는가? -> 비효율적일것.
    //=> 어차피 popularId 를 안다는 것이, 이미 그 영화가 View의 목록 중에 있기에 보고 눌렀다고 가정, -> 그 페이지의 것만 챙기면 된다.
    @GetMapping("/popular/{page}/movie/{whichOne}")
    @ResponseBody
    private ResultsDto getPopularMovieInfo(@PathVariable Integer page, @PathVariable Integer whichOne) throws IOException {
        String urlStr = "https://api.themoviedb.org/3/movie/popular" +
                "?api_key=" +
                apiKey +
                "&page=" + page;
        URL url = new URL(urlStr);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        String messageBody = StreamUtils.copyToString(conn.getInputStream(), StandardCharsets.UTF_8);
        MovieDto movie = objectmapper.readValue(messageBody, MovieDto.class);
        ArrayList<ResultsDto> results = movie.getResults();

        ResultsDto resultsDto = results.get(whichOne);
        String backdrop_path = resultsDto.getBackdrop_path();
        String title = resultsDto.getTitle();
        Integer popularity = resultsDto.getPopularity();

        log.info("title = {}, popularity = {}", title, popularity);
        log.info("resultsDto = {}", resultsDto);

        return resultsDto;
    }
}

