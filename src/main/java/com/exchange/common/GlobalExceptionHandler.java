package com.exchange.common;

import com.exchange.vo.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResponse error(Exception ex) {
        ex.printStackTrace();
        return ApiResponse.fail(ex.getMessage());
    }
}
