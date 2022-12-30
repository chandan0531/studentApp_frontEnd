package com.hostbooks.studentApplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.swing.text.html.parser.Entity;
import java.time.LocalDate;

//@RestControllerAdvice
//@ControllerAdvice
public class GlobalException {


    @ExceptionHandler(StudentException.class)
    public  ResponseEntity<MyError> studentException(StudentException se, WebRequest wr){

        MyError myError = new MyError(LocalDate.now(), se.getMessage(), wr.getDescription(false));

        return new ResponseEntity<MyError> (myError,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CourseException.class)
    public  ResponseEntity<MyError> courseException(CourseException ce, WebRequest wr){

        MyError myError = new MyError(LocalDate.now(), ce.getMessage(), wr.getDescription(false));

        return new ResponseEntity<MyError> (myError,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public  ResponseEntity<MyError> noHandlerFoundException(NoHandlerFoundException ce, WebRequest wr){

        MyError myError = new MyError(LocalDate.now(), ce.getMessage(), wr.getDescription(false));

        return new ResponseEntity<MyError> (myError,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public  ResponseEntity<MyError> illegalArgumentException(IllegalArgumentException ce, WebRequest wr){

        MyError myError = new MyError(LocalDate.now(), ce.getMessage(), wr.getDescription(false));

        return new ResponseEntity<MyError> (myError,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public  ResponseEntity<MyError> nullPointerException(NullPointerException ce, WebRequest wr){

        MyError myError = new MyError(LocalDate.now(), ce.getMessage(), wr.getDescription(false));

        return new ResponseEntity<MyError> (myError,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public  ResponseEntity<MyError> exception(Exception ce, WebRequest wr){

        MyError myError = new MyError(LocalDate.now(), ce.getMessage(), wr.getDescription(false));

        return new ResponseEntity<MyError> (myError,HttpStatus.BAD_REQUEST);
    }

}
