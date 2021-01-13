package com.example.demo0111.service;

import com.example.demo0111.data.Book;
import com.example.demo0111.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> search(String query) {
        return bookRepository.findByQuery(query);
    }
}
