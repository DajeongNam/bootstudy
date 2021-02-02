package com.example.demo0111.data;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MovieGroup {

    private final List<Movie> list;

    public MovieGroup(List<Movie> list) {
        this.list = list;
    }

    public List<Movie> getList(){
        return list;
    }

    public List<Movie> getListOrderRating(){
        return list.stream()
                .filter(movie -> movie.getUserRating() != 0 ) // 영화검색 평점 0인 데이터 제외
                .sorted((a, b) -> b.getUserRating() > a.getUserRating() ? 1 : -1) // 평점 오름차순 정렬
                .collect(Collectors.toList());
    }

    public Optional<Movie> getHighestRatingMovie() {
        return getListOrderRating().stream().findFirst();
    }
}
