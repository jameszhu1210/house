package com.sysco.house.common.exception;

import com.sysco.house.common.response.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


@ControllerAdvice
@RestController
@Slf4j
public class RestExceptionHandler {

  @ExceptionHandler(value = ValidationException.class)
  public ResponseEntity validatorException(ValidationException e) {
    log.error("ValidatorException() : ", e);
    GenericResponse genericResponse = new GenericResponse();
    genericResponse.setErrorMessage(e.getMessage());
    genericResponse.setResult(false);
    return new ResponseEntity<Object>(genericResponse, new HttpHeaders(), e.getStatusCode());
  }

  @ExceptionHandler(value = Exception.class)
  public ResponseEntity allException(Exception e) {
    log.error("catchAll() : Internal Service Error", e);
    GenericResponse genericResponse = new GenericResponse();
    genericResponse.setErrorMessage(e.getMessage());
    genericResponse.setResult(false);
    return new ResponseEntity<Object>(genericResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
