package com.example.demo0111.data;

import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class CacheMovieList {
    private static Map<String, List<Movie>> cacheMovie = new HashMap<>();

    public void addCacheMovie(String query, List<Movie> movieList){
        cacheMovie.put(query, movieList);
    }

    public Optional<List<Movie>> getCacheMovie(String query) {
        return Optional.ofNullable(cacheMovie.get(query));
    }

    public Set<String> getCacheMovieKey(){
        return cacheMovie.keySet();
    }

}
