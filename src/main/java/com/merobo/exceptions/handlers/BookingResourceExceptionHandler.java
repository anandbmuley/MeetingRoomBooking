package com.merobo.exceptions.handlers;

import com.merobo.dtos.error.ErrorResponseDto;
import com.merobo.exceptions.UnAuthorizedAccessException;
import com.merobo.resources.BookingResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = BookingResource.class)
public class BookingResourceExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.FORBIDDEN, code = HttpStatus.FORBIDDEN)
    public ErrorResponseDto handle(UnAuthorizedAccessException e) {
        return new ErrorResponseDto("You are not authorized to perform this operation");
    }

}
