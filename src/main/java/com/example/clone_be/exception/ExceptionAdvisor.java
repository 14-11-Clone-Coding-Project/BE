package com.example.clone_be.exception;

import com.example.clone_be.util.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ExceptionAdvisor {

    @ExceptionHandler
    public ResponseEntity<Message> exceptionHandler(Exception exception) {
        Message exceptionMessage = Message.setSuccess(exception.getMessage(), null);
        return new ResponseEntity(exceptionMessage, HttpStatus.BAD_REQUEST);
    }
}
