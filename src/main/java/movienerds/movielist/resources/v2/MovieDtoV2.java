package movienerds.movielist.resources.v2;

import lombok.*;
import org.json.simple.JSONArray;

import java.util.ArrayList;

@ToString
@NoArgsConstructor
@Getter @Setter
public class MovieDtoV2 {

    private Integer page;
    private ArrayList<Object> results;
    private Integer total_results;
    private Integer total_pages;

    @Builder
    public MovieDtoV2(Integer page, ArrayList<Object> results, Integer total_results, Integer total_pages) {
        this.page = page;
        this.results = results;
        this.total_results = total_results;
        this.total_pages = total_pages;
    }


    public String toList(JSONArray jsonArray) {
        String result = jsonArray.toJSONString();
        return result;
    }


}
