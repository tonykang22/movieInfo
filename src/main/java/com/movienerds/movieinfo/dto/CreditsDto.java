package com.movienerds.movieinfo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@NoArgsConstructor
@Getter @Setter
public class CreditsDto {

    private Integer id;
    private ArrayList<PersonDto> cast;
    private ArrayList<PersonDto> crew;
}
