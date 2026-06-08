package com.inventory.inventory_servic.exception;


import com.inventory.inventory_servic.dto.response.ErrorResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {


        @ExceptionHandler(EntityNotFoundException.class)
        public ResponseEntity<ErrorResponseDTO> handleEntityNotFoundException(EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(ex.getMessage()));
        }

        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<ErrorResponseDTO> handleIllegalArgumentException(IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO(ex.getMessage()));
        }

        @ExceptionHandler(IllegalStateException.class)
        public ResponseEntity<ErrorResponseDTO> handleIllegalStateException(IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponseDTO(ex.getMessage()));
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ErrorResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {




            String message = ex.getBindingResult().getFieldErrors()
                    .stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO(message));
        }

        

        @ExceptionHandler(HttpMessageNotReadableException.class)
        public ResponseEntity<ErrorResponseDTO> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO("El cuerpo de la petición es inválido o está malformado"));
        }




        @ExceptionHandler(MethodArgumentTypeMismatchException.class)
        public ResponseEntity<ErrorResponseDTO> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
            String message = String.format("El parámetro '%s' recibió un valor inválido: '%s'", ex.getName(), ex.getValue());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO(message));
        }




        @ExceptionHandler(MissingServletRequestParameterException.class)
        public ResponseEntity<ErrorResponseDTO> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
            String message = String.format("El parámetro '%s' es requerido", ex.getParameterName());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO(message));
        }



        @ExceptionHandler(DataIntegrityViolationException.class)
        public ResponseEntity<ErrorResponseDTO> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponseDTO(ex.getCause().getMessage()));
        }



        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorResponseDTO> handleException(Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponseDTO("Error interno del servidor"));
        }


    }

