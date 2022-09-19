package com.smgueye.drones.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

  @ExceptionHandler(EntityException.class)
  protected ResponseEntity<Object> handleEntityException(EntityException exception) {
    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
    apiError.setMessage(exception.getMessage());
    return buildResponseEntity(apiError);
  }

  @ExceptionHandler(EntityAlreadyExistsException.class)
  protected ResponseEntity<Object> handleEntityAlreadyExists(EntityException exception) {
    ApiError apiError = new ApiError(HttpStatus.CONFLICT);
    apiError.setMessage(exception.getMessage());
    return buildResponseEntity(apiError);
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestParameter(
      MissingServletRequestParameterException exception,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    String error = exception.getParameterName() + " parameter is missing";
    return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, exception));
  }

  @Override
  protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
      HttpMediaTypeNotSupportedException exception,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    StringBuilder builder = new StringBuilder();
    builder.append(exception.getContentType());
    builder.append(" media type is not supported. Supported media types are ");
    exception.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));
    return buildResponseEntity(
      new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, builder.substring(0, builder.length() - 2), exception));
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException exception,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
    apiError.setMessage("Validation error");
    apiError.addValidationErrors(exception.getBindingResult().getFieldErrors());
    apiError.addValidationError(exception.getBindingResult().getGlobalErrors());
    return buildResponseEntity(apiError);
  }

  @ExceptionHandler(javax.validation.ConstraintViolationException.class)
  protected ResponseEntity<Object> handleConstraintViolation(
      javax.validation.ConstraintViolationException exception) {
    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
    apiError.setMessage("Validation error");
    apiError.addValidationErrors(exception.getConstraintViolations());
    return buildResponseEntity(apiError);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  protected ResponseEntity<Object> handleEntityNotFound(
      EntityNotFoundException exception) {
    ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
    apiError.setMessage(exception.getMessage());
    return buildResponseEntity(apiError);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(
      HttpMessageNotReadableException exception,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    ServletWebRequest servletWebRequest = (ServletWebRequest) request;
    String error = "Malformed JSON request";
    return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, exception));
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotWritable(
      HttpMessageNotWritableException exception,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    String error = "Error writing JSON output";
    return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, error, exception));
  }

  @Override
  protected ResponseEntity<Object> handleNoHandlerFoundException(
    NoHandlerFoundException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
    apiError.setMessage(
      String.format("Could not find the %s method for URL %s",
      exception.getHttpMethod(),
      exception.getRequestURL()));
    apiError.setDebugMessage(exception.getMessage());
    return buildResponseEntity(apiError);
  }

  @ExceptionHandler(javax.persistence.EntityNotFoundException.class)
  protected ResponseEntity<Object> handleEntityNotFound(javax.persistence.EntityNotFoundException ex) {
    return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, ex));
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  protected ResponseEntity<Object> handleDataIntegrityViolation(
      DataIntegrityViolationException ex,
      WebRequest request) {
    if (ex.getCause() instanceof ConstraintViolationException) {
      String message = ex.getRootCause().getMessage();
      message = message.substring(message.indexOf("Key"));
      return buildResponseEntity(new ApiError(HttpStatus.CONFLICT, message, ex.getCause()));
    }
    return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex));
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
      WebRequest request) {
    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
    apiError.setMessage(String.format("The parameter '%s' of value '%s' could not be converted to type '%s'",
        ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName()));
    apiError.setDebugMessage(ex.getMessage());
    return buildResponseEntity(apiError);
  }

  private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
    return new ResponseEntity<>(apiError, apiError.getStatus());
  }
}
