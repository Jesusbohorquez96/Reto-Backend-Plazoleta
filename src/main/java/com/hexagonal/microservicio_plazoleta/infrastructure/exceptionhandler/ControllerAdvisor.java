package com.hexagonal.microservicio_plazoleta.infrastructure.exceptionhandler;

import com.hexagonal.microservicio_plazoleta.application.dto.ErrorResponse;
import com.hexagonal.microservicio_plazoleta.infrastructure.exception.*;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.hexagonal.microservicio_plazoleta.constants.ValidationConstants.*;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<Map<String, String>> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> {
                    Map<String, String> errorDetails = new HashMap<>();
                    errorDetails.put(FIELD, fieldError.getField());
                    errorDetails.put(MESSAGE, fieldError.getDefaultMessage());
                    errorDetails.put(REJECTED_VALUE, fieldError.getRejectedValue() != null ? fieldError.getRejectedValue().toString() : NULL);
                    return errorDetails;
                })
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put(ERRORS, errors);
        response.put(STATUS, HttpStatus.BAD_REQUEST.value());

        response.put(MESSAGE, VALIDATION_FAILED);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<Map<String, String>> handleMalformedJwtException(MalformedJwtException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Map.of(
                        STATUS, String.valueOf(HttpStatus.FORBIDDEN.value()),
                        MESSAGE, INVALID_JWT
                ));
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleAlreadyExistsException(AlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of(
                        STATUS, String.valueOf(HttpStatus.CONFLICT.value()),
                        MESSAGE, ex.getMessage()
                ));
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(NoDataFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of(
                        STATUS, String.valueOf(HttpStatus.NOT_FOUND.value()),
                        MESSAGE, ex.getMessage()
                ));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                List.of()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleNotFoundException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        STATUS, String.valueOf(HttpStatus.BAD_REQUEST.value()),
                        MESSAGE, ex.getMessage()
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                        STATUS, String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                        MESSAGE, OCCURRED_UNEXPECTED
                ));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> handleUnauthorizedException(UnauthorizedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }

    @ExceptionHandler(DishValidationException.class)
    public ResponseEntity<Map<String, Object>> handleDishValidationException(DishValidationException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put(STATUS, HttpStatus.BAD_REQUEST.value());
        response.put(MESSAGE, ex.getMessage());
        response.put("dishId", ex.getDishId());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
