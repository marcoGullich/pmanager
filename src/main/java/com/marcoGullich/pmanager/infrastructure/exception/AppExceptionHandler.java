package com.marcoGullich.pmanager.infrastructure.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Objects;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {


    private RestError restError = new RestError();

    @ExceptionHandler(value = RequestException.class)
    public ResponseEntity<Object> handleRequestException(RequestException ex, WebRequest request) {

        return handleException(ex,
                ex.getErrorCode(),
                ex.getMessage(),
                null,
                HttpStatus.BAD_REQUEST,
                request);
    }


    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {

        return handleException(ex,
                null,
                ex.getMessage(),
                null,
                HttpStatus.INTERNAL_SERVER_ERROR,
                request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        List<String> details = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .filter(Objects::nonNull)
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        return handleException(ex,
                "ValidationError",
                null,
                details,
                HttpStatus.BAD_REQUEST,
                request);

    }

    private ResponseEntity<Object> handleException(
            Exception ex,
            String errorCode,
            String message,
            List<String> details,
            HttpStatus status,
            WebRequest request) {

        ServletWebRequest servletWebRequest = (ServletWebRequest) request;

        restError.setErroCode(errorCode);
        restError.setErrorMensage(message);
        restError.setDetails(details);
        restError.setStatus(status.value());
        restError.setPath(servletWebRequest.getRequest().getRequestURI());

        return handleExceptionInternal(
                ex,
                restError,
                new HttpHeaders(),
                status,
                request
        );
    }
}
