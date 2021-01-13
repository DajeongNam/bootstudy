package com.example.demo0111.repository;

import com.example.demo0111.data.Book;

import java.util.List;

public interface BookRepository {
    List<Book> findByQuery(String query);
}
