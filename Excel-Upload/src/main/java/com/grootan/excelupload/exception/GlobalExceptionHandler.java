package com.grootan.excelupload.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final long serialVersionUID = 1L;

    private String filename;

    private String message;

    // gets Excel Filename
    public void getFilename(String filename) {
        this.filename = filename;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @ExceptionHandler
    public void defaultException(Exception exceptionMessage) {
        log.error(exceptionMessage.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> credentialException(BadCredentialsException exceptionMessage) {
        message = "Invalid Credentials";
        log.error(exceptionMessage.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Object> expiredJwtException(ExpiredJwtException exceptionMessage) {
        message = "JWT Token has expired";
        log.warn("JWT Token has expired");
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(message);
    }

    @ExceptionHandler(FileUploadException.class)
    public HttpStatus fileUploadException(FileUploadException exceptionMessage) {
        log.error("FAILED to upload" + filename + " ,Because -> " + exceptionMessage.getMessage());
        return HttpStatus.BAD_REQUEST;
    }
}
