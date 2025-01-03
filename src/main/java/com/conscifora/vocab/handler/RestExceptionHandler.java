package com.conscifora.vocab.handler;

import com.conscifora.vocab.exception.NotFoundInRepositoryException;
import com.conscifora.vocab.exception.NotFoundResourceException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.*;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            NotFoundResourceException.class,
            NotFoundInRepositoryException.class
    })
    public ResponseEntity<String> handleNotFoundResourceException(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}