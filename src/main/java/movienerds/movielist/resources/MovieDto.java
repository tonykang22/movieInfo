package movienerds.movielist.resources;

import lombok.*;
import org.json.simple.JSONArray;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@ToString
@NoArgsConstructor
@Getter @Setter
public class MovieDto {

    private Integer page;
    private ArrayList<Result> results;
    private Integer total_results;
    private Integer total_pages;

    @Builder
    public MovieDto(Integer page, ArrayList<Result> results, Integer total_results, Integer total_pages) {
        this.page = page;
        this.results = results;
        this.total_results = total_results;
        this.total_pages = total_pages;
    }

    public class Result {

        private String poster_path;
        private boolean adult;
        private String overview;
        private String release_date;
        private ArrayList<GenreId> genre_ids;
        private Integer id;
        private String original_title;
        private String original_language;
        private String title;
        private String backdrop_path;
        private float popularity;
        private Integer vote_count;
        private boolean video;
        private float vote_average;

    }

    public class GenreId {
        private Integer genreId;
    }

    public String toList(JSONArray jsonArray) {
        String result = jsonArray.toJSONString();
        return result;
    }


}
