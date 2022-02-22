package com.ee.employeesportal.advices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.context.MessageSource;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;

@ControllerAdvice
public class ExceptionInterceptor extends ResponseEntityExceptionHandler {
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(EmployeeException.class)
    public final ResponseEntity<Map<String ,String>> handleException(EmployeeException ex) {
        Map<String,String> message= new HashMap<>();
        message.put("message",ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException ex
    ) {
        ConstraintViolation<?> violation = ex.getConstraintViolations().iterator().next();
        System.out.println(violation.getPropertyPath());
        Map<String,String> message= new HashMap<>();
        message.put("message",violation.getPropertyPath()+" is invalid");
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}
