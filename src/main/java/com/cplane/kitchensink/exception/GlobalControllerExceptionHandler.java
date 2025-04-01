package com.cplane.kitchensink.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<?> userNotFoundException(UserNotFoundException exception,
                                                   HttpServletRequest request) {

        return new ResponseEntity<>(
                new ErrorResponse(exception.getMessage(),
                        request.getRequestURI(),
                        request.getMethod(),
                        HttpStatus.NOT_FOUND.value()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({DuplicateEmailException.class})
    public ResponseEntity<?> duplicateKeyException(DuplicateEmailException exception,
                                                       HttpServletRequest request) {

        return new ResponseEntity<>(
                new ErrorResponse(exception.getMessage(),
                        request.getRequestURI(),
                        request.getMethod(),
                        HttpStatus.BAD_REQUEST.value()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<?> fieldValidationException(ConstraintViolationException exception,
                                                      HttpServletRequest request) {
        return new ResponseEntity<>(
                new ErrorResponse(buildValidationErrorMsg(exception),
                        request.getRequestURI(),
                        request.getMethod(),
                        HttpStatus.BAD_REQUEST.value()),
                HttpStatus.BAD_REQUEST);
    }

    private String buildValidationErrorMsg(ConstraintViolationException exception) {
        return exception.getConstraintViolations().stream()
                .map(val -> "Field '" + val.getPropertyPath().toString() + "' - " + val.getMessage())
                .toList()
                .toString();
    }
}
