package movienerds.movielist.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.View;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
@RequestMapping("/api/movie")
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
    @JsonIgnore
    @ResponseBody
    @GetMapping("/popular/{page}")
    public MovieDtoV2 popularList(@PathVariable Integer page) throws IOException {

        String urlStr = "https://api.themoviedb.org/3/movie/popular" +
                "?api_key=" +
                apiKey +
                "&page=" + page;
        URL url = new URL(urlStr);

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");

        String messageBody = StreamUtils.copyToString(urlConnection.getInputStream(), StandardCharsets.UTF_8);
        MovieDtoV2 data = objectmapper.readValue(messageBody, MovieDtoV2.class);
        log.info("popular list has been called.");

        return data;
    }


    @JsonIgnore
    @GetMapping("/popular/v2")
    public MovieDto popularListV2() {

        RestTemplate restTemplate = new RestTemplate();

        String urlStr = "https://api.themoviedb.org/3/movie/popular";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlStr)
                .queryParam("api_key", apiKey);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

        log.info("popularListV2 has been called.");

        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, httpEntity, MovieDto.class).getBody();
    }

    @JsonIgnore
    @GetMapping("/popular/v3")
    public String popularListV3() throws IOException {

        String urlStr = "https://api.themoviedb.org/3/movie/popular" +
                "?api_key=" +
                apiKey;
        URL url = new URL(urlStr);

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");

        String messageBody = StreamUtils.copyToString(urlConnection.getInputStream(), StandardCharsets.UTF_8);

        log.info("popularListV3 has been called.");

        return messageBody;
    }
}
