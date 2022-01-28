
package com.vxtech.capela.web.rest;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.vxtech.capela.exceptions.ExceptionGeneric;
import com.vxtech.capela.web.rest.dto.ErrorResponseDTO;
import com.vxtech.capela.web.rest.dto.ExceptionResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Log4j2
@ControllerAdvice
public class ControllerExceptionHandler {

     @ExceptionHandler(ExceptionGeneric.class)
     public ResponseEntity<ExceptionResponseDTO> handleGeneric(ExceptionGeneric exception, HttpServletRequest request) {

          log.error(exception);
          ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO("", exception.getLocalizedMessage());
          ExceptionResponseDTO responseDTO = new ExceptionResponseDTO(new Date(), request.getRequestURI(), exception.getMessage(), List.of(errorResponseDTO), exception.getErrorCode());
          return new ResponseEntity<>(responseDTO, HttpStatus.valueOf(exception.getCode()));

     }

     @ExceptionHandler(ConstraintViolationException.class)
     public ResponseEntity<ExceptionResponseDTO> handleConstraintViolation(ConstraintViolationException exception, HttpServletRequest request) {

          log.error(exception);
          List<ErrorResponseDTO> errors = new ArrayList<>(exception.getConstraintViolations().size());

          for (ConstraintViolation violation : exception.getConstraintViolations()) {
               errors.add(new ErrorResponseDTO(violation.getPropertyPath().toString(), violation.getMessage()));
          }

          ExceptionResponseDTO responseDTO = new ExceptionResponseDTO(new Date(), request.getRequestURI(), exception.getMessage(), errors);
          return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
     }

     @ExceptionHandler(MethodArgumentNotValidException.class)
     public ResponseEntity<ExceptionResponseDTO> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpServletRequest request) {

          log.error(exception);
          List<ErrorResponseDTO> errors = new ArrayList<>(exception.getBindingResult().getFieldErrors().size());

          for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
               errors.add(new ErrorResponseDTO(fieldError.getField(), fieldError.getDefaultMessage()));
          }

          ExceptionResponseDTO responseDTO = new ExceptionResponseDTO(new Date(), request.getRequestURI(), "Validation Failed", errors);

          return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
     }

     @ExceptionHandler(InvalidFormatException.class)
     public ResponseEntity<ExceptionResponseDTO> handleInvalidFormat(InvalidFormatException exception, HttpServletRequest request) {

          log.error(exception);
          ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO("", exception.getLocalizedMessage());
          ExceptionResponseDTO responseDTO = new ExceptionResponseDTO(new Date(), request.getRequestURI(), exception.getMessage(), List.of(errorResponseDTO));
          return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
     }

     @ExceptionHandler(Exception.class)
     public ResponseEntity<ExceptionResponseDTO> handleAll(Exception exception, HttpServletRequest request) {

          log.error(exception);
          ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO("", exception.getCause().getMessage());
          ExceptionResponseDTO responseDTO = new ExceptionResponseDTO(new Date(), request.getRequestURI(), exception.getCause().getMessage(), List.of(errorResponseDTO));
          return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
     }

}
