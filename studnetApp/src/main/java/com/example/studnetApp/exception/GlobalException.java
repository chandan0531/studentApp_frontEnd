package com.example.studnetApp.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDate;

@ControllerAdvice
public class GlobalException {


    @ExceptionHandler(StudentException.class)
    ResponseEntity<ErrorDetails> studentException(StudentException se, WebRequest wr){

        ErrorDetails ed = new ErrorDetails(LocalDate.now(), se.getMessage(), wr.getDescription(false));
        return new ResponseEntity<>(ed, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    ResponseEntity<ErrorDetails> noHandlerFoundException(NoHandlerFoundException ne, WebRequest wr){

        ErrorDetails ed = new ErrorDetails(LocalDate.now(), ne.getMessage(), wr.getDescription(false));
        return new ResponseEntity<>(ed, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ErrorDetails> exception(Exception ne, WebRequest wr){

        ErrorDetails ed = new ErrorDetails(LocalDate.now(), ne.getMessage(), wr.getDescription(false));
        return new ResponseEntity<>(ed, HttpStatus.BAD_REQUEST);
    }

}
