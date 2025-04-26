package com.example.demo.Exception;


import com.example.demo.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result handleAllException(Exception e) {
        return Result.error(e.getMessage());
    }
}
