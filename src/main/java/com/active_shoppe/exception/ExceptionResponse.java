package com.active_shoppe.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@Builder
public class ExceptionResponse {

    private LocalDateTime timeStamp;
    private String message;
    private String details;
}
