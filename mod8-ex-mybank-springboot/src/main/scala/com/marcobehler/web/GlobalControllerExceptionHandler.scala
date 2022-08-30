package com.marcobehler.web

import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.{ExceptionHandler, ResponseStatus, RestControllerAdvice}

@RestControllerAdvice
class GlobalControllerExceptionHandler {
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(Array(classOf[MethodArgumentNotValidException]))
  def methodArgumentNotValidExceptionHandler(exception: MethodArgumentNotValidException): String = {
    s"Sorry, that was not quite right: ${exception.getMessage}"
  }
}
