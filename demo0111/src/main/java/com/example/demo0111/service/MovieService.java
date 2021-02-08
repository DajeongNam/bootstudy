package com.example.demo0111.service;

import com.example.demo0111.aop.cache.LookAsideCaching;
import com.example.demo0111.aop.timer.PerformanceTimeRecord;
import com.example.demo0111.data.Movie;
import com.example.demo0111.data.MovieGroup;
import com.example.demo0111.exception.ClientNoContentRuntimeException;
import com.example.demo0111.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    @PerformanceTimeRecord
    @LookAsideCaching(value = "cache::search-movies", key = "query")
    public List<Movie> search(final String query) {
        MovieGroup movieGroup = new MovieGroup(movieRepository.findByQuery(query));
        return movieGroup.getListOrderRating();
    }


    @LookAsideCaching(value = "cache::recommend-movie")
    public Movie recommendTodayMovie() {
        String query = "반지의 제왕"; // 임시 하드코딩
        MovieGroup movieGroup = new MovieGroup(movieRepository.findByQuery(query));
        return movieGroup.getHighestRatingMovie()
                .orElseThrow(ClientNoContentRuntimeException::new);
    }
}
