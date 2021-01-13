package com.example.demo0111.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Movie {
    private String title;
    private String link;
    private String actor;
    private String director;
    private float userRating;
}
