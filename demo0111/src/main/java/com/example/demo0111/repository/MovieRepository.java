package com.example.demo0111.repository;

import com.example.demo0111.data.Movie;

import java.util.List;

public interface MovieRepository {
    List<Movie> findByQuery(String query);
}
