package com.inventory.inventory_servic.exception;


import com.inventory.inventory_servic.dto.response.ErrorResponseDTO;
import jakarta.persistence.PersistenceException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class JpaExceptionHandler{

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponseDTO> handleDataIntegrity(DataIntegrityViolationException ex) {


        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponseDTO("Violación de integridad de datos "));
    }

}
