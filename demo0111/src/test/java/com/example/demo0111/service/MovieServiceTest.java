package com.example.demo0111.service;

import com.example.demo0111.data.Movie;
import com.example.demo0111.exception.ClientNoContentRuntimeException;
import com.example.demo0111.repository.MovieRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    public List<Movie> getStubMovies(){
        return Arrays.asList(
                Movie.builder().title("영화1").link("http://test").userRating(9.3f).build(),
                Movie.builder().title("영화2").link("http://test").userRating(8.7f).build(),
                Movie.builder().title("영화3").link("http://test").userRating(9.7f).build(),
                Movie.builder().title("영화4").link("http://test").userRating(0.0f).build()
        );
    }

    @Test
    public void 평점순_정렬_검사() throws Exception{
        //given
        String query = "test";
        String expectedTopRankingTitle = "영화3";
        given(movieRepository.findByQuery(anyString())).willReturn(getStubMovies());

        //when
        List<Movie> movieList = movieService.search(query);

        //then
        assertThat(movieList.get(0).getTitle()).isEqualTo(expectedTopRankingTitle);
    }

    @Test
    public void 평점0_제외_검사() throws Exception{
        //given
        String query = "test";
        int expectedSize = 3;
        given(movieRepository.findByQuery(anyString())).willReturn(getStubMovies());

        //when
        List<Movie> movieList = movieService.search(query);

        //then
        assertThat(movieList.size()).isEqualTo(expectedSize);
    }

    @Test
    public void 추천영화로_평점이_제일_높은_영화_검증() throws Exception{
        //given
        String query = "test";
        String expectedRecommendMovie = "영화3";
        given(movieRepository.findByQuery(anyString())).willReturn(getStubMovies());

        //when
        Movie actualRecommendMovie = movieService.recommendTodayMovie();

        //then
        assertThat(actualRecommendMovie.getTitle()).isEqualTo(expectedRecommendMovie);
    }
    


    @Disabled
    @Test
    public void 추천영화_null값인경우_기본영화제공_검증() throws Exception{
        //given
        String query = "test";
        String expectedRecommendMovie = "기본영화";
        given(movieRepository.findByQuery(anyString())).willReturn(Collections.emptyList());

        //when
        Movie actualRecommendMovie = movieService.recommendTodayMovie();

        //then
        assertThat(actualRecommendMovie.getTitle()).isEqualTo(expectedRecommendMovie);
    }

    @Test
    public void 추천영화_null값인경우_예외발생_검증() throws Exception{
        //given
        given(movieRepository.findByQuery(anyString())).willReturn(Collections.emptyList());

        //when,then
        Assertions.assertThrows(ClientNoContentRuntimeException.class,
                () -> movieService.recommendTodayMovie());
    }
}