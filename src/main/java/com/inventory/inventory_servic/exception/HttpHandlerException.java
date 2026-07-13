package com.inventory.inventory_servic.exception;

import com.inventory.inventory_servic.dto.response.ErrorResponseDTO;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HttpHandlerException {


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponseDTO> handler(HttpRequestMethodNotSupportedException ex){

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(new ErrorResponseDTO(ex.getMessage()));

    }
}
