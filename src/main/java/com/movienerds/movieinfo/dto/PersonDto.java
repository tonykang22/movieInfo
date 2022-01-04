package com.movienerds.movieinfo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class PersonDto {

    private boolean adult;
    private Integer gender;
    private Integer id;
    private String known_for_department;
    private String name;
    private String original_name;
    private Integer popularity;
    private String profile_path;
    private Integer cast_id;
    private String character;
    private String credit_id;
    @JsonIgnore
    private String department;
    @JsonIgnore
    private String job;
    @JsonIgnore
    private Integer order;

}
