package com.example.demo0111.service;

import com.example.demo0111.data.CacheMovieList;
import com.example.demo0111.data.Movie;
import com.example.demo0111.data.MovieGroup;
import com.example.demo0111.exception.ClientNoContentRuntimeException;
import com.example.demo0111.repository.MovieRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class MovieService {
    private final MovieRepository movieRepository;
    private CacheMovieList cacheMovieList;

    public List<Movie> search(final String query) {
        List<Movie> movieList;

        if(cacheMovieList.getCacheMovie(query).isPresent()){
            movieList = cacheMovieList.getCacheMovie(query).get();
            log.info("Use cache");
        }else{
            movieList = movieRepository.findByQuery(query);
            cacheMovieList.addCacheMovie(query, movieList);
            log.info("Use Repository");
        }

        MovieGroup movieGroup = new MovieGroup(movieList);
        return movieGroup.getListOrderRating();
    }


    public Movie recommendTodayMovie() {
        String query = "반지의 제왕"; // 임시 하드코딩
        MovieGroup movieGroup = new MovieGroup(movieRepository.findByQuery(query));
        return movieGroup.getHighestRatingMovie()
                .orElseThrow(ClientNoContentRuntimeException::new);
    }

    // 캐시 업데이트
    public int updateCache() {
        Set<String> cacheMovieKey = cacheMovieList.getCacheMovieKey();
        for (String key : cacheMovieKey) {
            cacheMovieList.addCacheMovie(key, movieRepository.findByQuery(key));
        }
        log.info("Update Cache");
        return cacheMovieKey.size();
    }
}
