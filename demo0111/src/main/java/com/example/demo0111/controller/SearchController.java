package com.example.demo0111.controller;

import com.example.demo0111.data.Book;
import com.example.demo0111.data.Movie;
import com.example.demo0111.service.BookService;
import com.example.demo0111.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/search")
@RequiredArgsConstructor
public class SearchController {
    private final MovieService movieService;
    private final BookService bookService;

    @GetMapping("/movies")
    public List<Movie> getMoviesByQuery(@RequestParam String query) {
        return movieService.search(query);
    }

    @GetMapping("/books")
    public List<Book> getBooksByQuery(@RequestParam String query) {
        return bookService.search(query);
    }

    @GetMapping("/recommend-movie")
    public Movie getRecommendMovie() {
        return movieService.recommendTodayMovie();
    }

    //관리자에 의한 캐시데이터 강제 업데이트
    @GetMapping("/update-cache")
    public ResponseEntity updateCache(){
        Map<String, Integer> response = new HashMap<>();
        int updated = movieService.updateCache();
        response.put("updated", updated);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
