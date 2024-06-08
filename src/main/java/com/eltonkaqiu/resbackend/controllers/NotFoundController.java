package com.eltonkaqiu.resbackend.controllers;

import com.eltonkaqiu.resbackend.dtos.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;

@RestControllerAdvice
@RequestMapping("/api/")
public class NotFoundController {

    @RequestMapping("/**")
    public ResponseEntity<?> notFound(ServletWebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage("Route not found");
        errorMessage.setStatus(HttpStatus.NOT_FOUND.value());
        errorMessage.setUrl(request.getRequest().getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
}
