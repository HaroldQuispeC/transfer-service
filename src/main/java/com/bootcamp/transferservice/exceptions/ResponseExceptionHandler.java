package com.bootcamp.transferservice.exceptions;

import java.time.LocalDateTime;
import java.util.Arrays;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {
  /**
   * handleModelException method - extends ResponseEntityExceptionHandler.
   * @param model ModelException
   * @param web  WebRequest
   * @return ResponseEntity
   */
  @ExceptionHandler(ModelException.class)
  public final ResponseEntity<ExceptionResponse> handleModelException(
                        ModelException model, WebRequest web) {
    ExceptionResponse exception = new ExceptionResponse(LocalDateTime.now(),
                                                        model.getMessage(),
                                                        web.getDescription(false));

    logger.error(String.format("%s - %s",
                  model.getMessage(), Arrays.toString(model.getStackTrace())));
    return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
  }
}