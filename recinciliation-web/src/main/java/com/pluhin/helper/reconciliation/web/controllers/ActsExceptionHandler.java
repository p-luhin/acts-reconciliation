package com.pluhin.helper.reconciliation.web.controllers;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import com.pluhin.helper.reconciliation.common.dto.ErrorDTO;
import com.pluhin.helper.reconciliation.common.exception.InvalidFileException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ActsExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(InvalidFileException.class)
  public ResponseEntity handleInvalidFileException(InvalidFileException e) {
    log.error("", e);
    return ResponseEntity.badRequest()
        .body(new ErrorDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
  }

  @ResponseStatus(INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception.class)
  public ErrorDTO handleUncaughtException(Exception e) {
    return new ErrorDTO(INTERNAL_SERVER_ERROR.value(), e.getMessage());
  }
}
