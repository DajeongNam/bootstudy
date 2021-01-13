package com.example.demo0111.service;

import com.example.demo0111.data.Movie;
import com.example.demo0111.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public List<Movie> search(final String query) {
        return movieRepository.findByQuery(query);
    }
}
