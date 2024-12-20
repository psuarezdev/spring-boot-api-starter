package dev.pages.psuarez.springbootapistarter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalValidationExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, Object>> customHandleNotValid(MethodArgumentNotValidException e, WebRequest request) {
    List<String> errors = e.getBindingResult()
      .getFieldErrors()
      .stream()
      .map(error -> error.getField() + ": " + error.getDefaultMessage())
      .collect(Collectors.toList());

    Map<String, Object> response = new HashMap<>();
    response.put("errors", errors);
    response.put("message", "Validation failed");

    return ResponseEntity.badRequest().body(response);
  }
}
