package com.merobo.exceptions.handlers;

import com.merobo.dtos.error.ErrorResponseDto;
import com.merobo.resources.RoomResource;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice(assignableTypes = RoomResource.class)
public class RoomResourceExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(value = INTERNAL_SERVER_ERROR, code = INTERNAL_SERVER_ERROR)
    public ErrorResponseDto handleIOException(IOException ex) {
        return new ErrorResponseDto("Unable to upload image due to IO Error");
    }
}
