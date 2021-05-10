package com.micro.Flightsearch.exceptions;

import java.time.LocalDateTime;
import java.util.*;

import com.micro.Flightsearch.models.ApiErrors;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException arg0,
            HttpHeaders arg1, HttpStatus arg2, WebRequest arg3) {
        String message = arg0.getMessage();
        List<String> details= new ArrayList<>();
        details.add("Media not supported");
        ApiErrors errors = new ApiErrors(message,details,arg2,LocalDateTime.now());
        return ResponseEntity.status(arg2).body(errors);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getMessage();
        List<String> details= new ArrayList<>();
        details.add("Request body not readable");
        ApiErrors errors = new ApiErrors(message,details,status,LocalDateTime.now());
        return ResponseEntity.status(status).body(errors);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getMessage();
        List<String> details= new ArrayList<>();
        details.add("Request Method not supported");
        ApiErrors errors = new ApiErrors(message,details,status,LocalDateTime.now());
        return ResponseEntity.status(status).body(errors);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        String message = ex.getMessage();
        List<String> details= new ArrayList<>();
        details.add("Path Variable is missing");
        ApiErrors errors = new ApiErrors(message,details,status,LocalDateTime.now());
        return ResponseEntity.status(status).body(errors);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getMessage();
        List<String> details= new ArrayList<>();
        details.add("Request parameter is missing");
        ApiErrors errors = new ApiErrors(message,details,status,LocalDateTime.now());
        return ResponseEntity.status(status).body(errors);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        String message = ex.getMessage();
        List<String> details= new ArrayList<>();
        details.add("Type Mismatch");
        ApiErrors errors = new ApiErrors(message,details,status,LocalDateTime.now());
        return ResponseEntity.status(status).body(errors);
    }
    
}
