package com.example.demo0111.data;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieGroupTest {

    @Test
    public void 평점순_정렬() throws Exception{
        //given
        MovieGroup movieGroup = new MovieGroup(Arrays.asList(
                Movie.builder().title("영화1").link("http://test").userRating(9.3f).build(),
                Movie.builder().title("영화2").link("http://test").userRating(8.7f).build(),
                Movie.builder().title("영화3").link("http://test").userRating(9.7f).build()
        ));

        //when
        List<Movie> orderedList = movieGroup.getListOrderRating();

        //then
        Assertions.assertThat("영화3").isEqualTo(orderedList.get(0).getTitle());

    }
}