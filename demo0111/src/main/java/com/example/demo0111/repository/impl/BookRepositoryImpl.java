package com.example.demo0111.repository.impl;

import com.example.demo0111.NaverProperties;
import com.example.demo0111.data.Book;
import com.example.demo0111.data.ResponseBook;
import com.example.demo0111.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {

    private final RestTemplate restTemplate;
    private final NaverProperties naverProperties;

    @Override
    public List<Book> findByQuery(String query) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Naver-Client-Id", naverProperties.getClientId());
        httpHeaders.add("X-Naver-Client-Secret", naverProperties.getClientSecret());

        String url = naverProperties.getBookUrl() + "?query=" + query;

        return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity(httpHeaders), ResponseBook.class)
                .getBody()
                .getItems()
                .stream()
                .map(b -> Book.builder()
                        .author(b.getAuthor())
                        .title(b.getTitle())
                        .link(b.getLink())
                        .pubdate(b.getPubdate())
                        .build())
                .collect(toList());

    }
}
