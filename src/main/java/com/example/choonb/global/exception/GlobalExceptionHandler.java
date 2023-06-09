package com.example.choonb.global.exception;

import com.example.choonb.domain.user.dto.MessageResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  // CustomException 으로 발생시킨 예외 처리
  @ExceptionHandler(CustomException.class)
  public ResponseEntity<MessageResponseDto> handleCustomException(CustomException e) {
    String message = e.getMessage().split(":")[0];
    MessageResponseDto messageResponseDto = new MessageResponseDto(e.getHttpStatus(), message);
    return new ResponseEntity<>(messageResponseDto, e.getHttpStatus());
  }

  // @Valid 에외 처리
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<MessageResponseDto> handleNotValidException(
      MethodArgumentNotValidException e) {
    String message = e.getFieldError().getDefaultMessage().split(":")[0];
    MessageResponseDto messageResponseDto = new MessageResponseDto(HttpStatus.BAD_REQUEST, message);
    return new ResponseEntity<>(messageResponseDto, HttpStatus.BAD_REQUEST);
  }

  // 나머지 예외 처리
  @ExceptionHandler
  public ResponseEntity<MessageResponseDto> handleException(Exception e) {
    String message = e.getMessage().split(":")[0];
    MessageResponseDto messageResponseDto = new MessageResponseDto(HttpStatus.BAD_REQUEST, message);
    return new ResponseEntity<>(messageResponseDto, HttpStatus.BAD_REQUEST);
  }

}
