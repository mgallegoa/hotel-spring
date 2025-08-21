package com.co.manuel.hotel_spring.exceptions;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import com.co.manuel.hotel_spring.dto.ProblemDetailDTO;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalDetailHandler {

  @ExceptionHandler(ResponseStatusException.class)
  public ResponseEntity<ProblemDetailDTO> responseStatusException(ResponseStatusException ex, WebRequest request) {
    ProblemDetailDTO problemDetailDTO = new ProblemDetailDTO(URI.create("https://mgallegoa.github.io/"),
        ex.getReason() != null ? ex.getReason() : ex.getStatusCode().toString(), ex.getStatusCode().value(),
        ex.getMessage(), request.getDescription(false).replace("uri=", ""));
    return ResponseEntity.status(ex.getStatusCode()).body(problemDetailDTO);
  }

  /**
   * Throw when @Email in DTO is configured for the field.
   * 
   * @Valid
   */
  @ExceptionHandler
  public ResponseEntity<ProblemDetailDTO> validationException(MethodArgumentNotValidException ex, WebRequest request) {
    List<String> errors = ex.getBindingResult().getFieldErrors().stream()
        .map(err -> err.getField() + " : " + err.getDefaultMessage()).collect(Collectors.toList());

    ProblemDetailDTO problemDetailDTO = new ProblemDetailDTO(URI.create("https://mgallegoa.github.io/"),
        "Validations Filed", HttpStatus.BAD_REQUEST.value(), String.join("; ", errors),
        request.getDescription(false).replace("URI=", ""));
    return ResponseEntity.status(ex.getStatusCode()).body(problemDetailDTO);

  }

  /**
   * Throw when, for example in the controller:
   * public String getUser(@RequestParam @Min(1) int id) {
   * and send id=0
   *
   * @Validated
   */
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ProblemDetailDTO> handleConstraintViolation(ConstraintViolationException ex,
      WebRequest request) {
    List<String> errors = ex.getConstraintViolations()
        .stream()
        .map(v -> v.getPropertyPath() + ": " + v.getMessage())
        .collect(Collectors.toList());

    ProblemDetailDTO problem = new ProblemDetailDTO(
        URI.create("https://mgallegoa.github.io/errors/validation"),
        "Validation Failed",
        HttpStatus.BAD_REQUEST.value(),
        String.join("; ", errors),
        request.getDescription(false).replace("uri=", ""));
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problem);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ProblemDetailDTO> handleGenericException(Exception ex, WebRequest request) {
    ProblemDetailDTO problem = new ProblemDetailDTO(
        URI.create("https://mgallegoa.github.io/errors/internal"),
        "Internal Server Error",
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        ex.getMessage(),
        request.getDescription(false).replace("uri=", ""));
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(problem);
  }
}
