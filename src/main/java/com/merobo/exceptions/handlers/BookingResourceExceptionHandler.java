package com.merobo.exceptions.handlers;

import com.merobo.dtos.error.ErrorResponseDto;
import com.merobo.exceptions.UnAuthorizedAccessException;
import com.merobo.resources.BookingResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = BookingResource.class)
public class BookingResourceExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponseDto> handle(UnAuthorizedAccessException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                new ErrorResponseDto("You are not authorized to perform this operation")
        );
    }

}
