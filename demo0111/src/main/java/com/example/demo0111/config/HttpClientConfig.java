package com.example.demo0111.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpClientConfig {
    @Bean
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        HttpClient httpClient = HttpClientBuilder.create()
                .setMaxConnTotal(50) // 최대 오픈되는 커넥션 수 제한
                .setMaxConnPerRoute(10) // IP,포트 1쌍에대해 수행할 연결 수 제한
                .build();

        factory.setHttpClient(httpClient); // 동기실행에 사용될 HttpClient세팅(connection pool적용)
        factory.setConnectTimeout(3000); // 연결시간초과
        factory.setReadTimeout(5000); // 읽기 시간 초과
        return new RestTemplate(factory);
    }
}
