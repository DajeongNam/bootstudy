package com.example.demo0111.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String title;
    private String link;
    private String author;
    private LocalDate pubdate;

}
