package com.eltonkaqiu.resbackend.controllers.advice;

import com.eltonkaqiu.resbackend.dtos.ErrorMessage;
import com.eltonkaqiu.resbackend.exceptions.EntityFoundException;
import com.eltonkaqiu.resbackend.exceptions.EntityNoContentException;
import com.eltonkaqiu.resbackend.exceptions.EntityNotFoundException;
import com.eltonkaqiu.resbackend.exceptions.NotAuthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(Exception.class)
    public static ResponseEntity<ErrorMessage> handleException(Exception e, HttpServletRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }

    @ExceptionHandler(EntityFoundException.class)
    public static ResponseEntity<ErrorMessage> handleEntityFoundException(EntityFoundException e, HttpServletRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.CONFLICT.value(), LocalDateTime.now(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public static ResponseEntity<ErrorMessage> handleEntityNotFoundException(EntityNotFoundException e, HttpServletRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND.value(), LocalDateTime.now(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(NotAuthorizedException.class)
    public static ResponseEntity<ErrorMessage> handleNotAuthorizedException(NotAuthorizedException e, HttpServletRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.UNAUTHORIZED.value(), LocalDateTime.now(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
    }

    @ExceptionHandler(EntityNoContentException.class)
    public static ResponseEntity<ErrorMessage> handleEntityNoContentException(EntityNoContentException e, HttpServletRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NO_CONTENT.value(), LocalDateTime.now(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(errorMessage);
    }

}
