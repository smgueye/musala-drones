package com.smgueye.drones.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Data
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NONE  , property = "error", visible = true)
@JsonTypeIdResolver(LoweCaseClassNameResolver.class)
public class ApiError {
  private HttpStatus status;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
  private LocalDateTime timestamp;
  private String message;
  private String debugMessage;
  private List<ApiSubError> errors;

  private ApiError() {
    timestamp = LocalDateTime.now();
  }

  public ApiError(HttpStatus status) {
    this();
    this.status = status;
  }

  public ApiError(HttpStatus status, Throwable ex) {
    this();
    this.status = status;
    this.message = "Unexpected error";
    this.debugMessage = ex.getLocalizedMessage();
  }

  public ApiError(HttpStatus status, String message, Throwable ex) {
    this();
    this.status = status;
    this.message = message;
    this.debugMessage = ex.getLocalizedMessage();
  }

  private void addError(ApiSubError error) {
    if (errors == null)
      errors = new ArrayList<>();
    errors.add(error);
  }

  private void addValidationError(String object, String field, Object rejectedValue, String message) {
    addError(new ApiValidationError(object, field, rejectedValue, message));
  }

  private void addValidationError(String object, String message) {
    addError(new ApiValidationError(object, message));
  }

  private void addValidationError(FieldError fieldError) {
    addValidationError(
      fieldError.getObjectName(),
      fieldError.getField(),
      fieldError.getRejectedValue(),
      fieldError.getDefaultMessage()
    );
  }

  public void addValidationErrors(List<FieldError> fieldErrors) {
    fieldErrors.forEach(this::addValidationError);
  }

  private void addValidationError(ObjectError objectError) {
    addValidationError(
      objectError.getObjectName(),
      objectError.getDefaultMessage()
    );
  }

  public void addValidationError(List<ObjectError> globalErrors) {
    globalErrors.forEach(this::addValidationError);
  }

  private void addValidationError(ConstraintViolation<?> cv) {
    addValidationError(
      cv.getRootBeanClass().getSimpleName(),
        (cv.getPropertyPath()).toString(),
      cv.getInvalidValue(),
      cv.getMessage()
    );
  }

  public void addValidationErrors(Set<ConstraintViolation<?>> constraintValidators) {
    constraintValidators.forEach(this::addValidationError);
  }
}
