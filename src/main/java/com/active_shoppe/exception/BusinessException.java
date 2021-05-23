package com.active_shoppe.exception;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Setter
@Getter
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private  final String message;
    private final HttpStatus httpStatus;

    public BusinessException(String message, HttpStatus httpStatus){
        this.message = message;
        this.httpStatus = httpStatus;

    }
}
