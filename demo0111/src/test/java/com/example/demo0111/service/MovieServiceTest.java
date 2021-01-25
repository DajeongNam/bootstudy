package com.example.demo0111.service;

import com.example.demo0111.data.Movie;
import com.example.demo0111.repository.MovieRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    public List<Movie> getStubMovies(){
        return Arrays.asList(
                Movie.builder().title("영화1").link("http://test").userRating(9.3f).build(),
                Movie.builder().title("영화2").link("http://test").userRating(8.7f).build(),
                Movie.builder().title("영화3").link("http://test").userRating(9.7f).build()
        );
    }

    @Test
    public void 평점순_정렬_검사() throws Exception{
        //given
        String query = "test";
        String expectedTopRankingTitle = "영화3";
        given(movieRepository.findByQuery(anyString())).willReturn(getStubMovies());
        MovieService movieService = new MovieService(movieRepository);

        //when
        List<Movie> movieList = movieService.search(query);

        //then
        Assertions.assertThat(movieList.get(0).getTitle()).isEqualTo(expectedTopRankingTitle);

    }
}