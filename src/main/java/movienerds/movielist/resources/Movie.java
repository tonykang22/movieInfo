package movienerds.movielist.resources;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter @Setter
public class Movie {

    @Id
    @Column(name = "popular_id")
    private Long id;

    private String title;
    private String release_date;
    private float popularity;
    private float vote_average;
    private String poster_path;
    private boolean video;

}
