package com.example.demo0111.exception;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ExceptionResponse {
    private String message;
    private int status;

}
