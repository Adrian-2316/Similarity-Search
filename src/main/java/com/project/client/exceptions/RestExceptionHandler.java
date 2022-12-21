package com.project.client.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  /**
   * Handles EntityNotFoundException. Thrown when no entity is found.
   *
   * @param ex EntityNotFoundException.
   * @return ResponseEntity - ResponseEntity object.
   */
  @ExceptionHandler(EntityNotFoundException.class)
  protected ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
    ApiResponse apiResponse = new ApiResponse(NOT_FOUND);
    apiResponse.setMessage("Entity Not found");
    log.error(ex.getMessage(), ex);
    return buildResponseEntity(apiResponse);
  }
  /**
   * Generic method to build ResponseEntity responses.
   *
   * @param apiResponse - ApiException.
   * @return ResponseEntity - ResponseEntity object.
   */
  private ResponseEntity<Object> buildResponseEntity(ApiResponse apiResponse) {
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }
}
