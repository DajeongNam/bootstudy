package com.example.demo0111.controller;

import com.example.demo0111.data.Book;
import com.example.demo0111.data.Movie;
import com.example.demo0111.service.BookService;
import com.example.demo0111.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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
}
