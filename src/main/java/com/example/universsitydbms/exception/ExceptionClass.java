package com.example.universsitydbms.exception;

import com.example.universsitydbms.dto.ErrorDto;
import com.example.universsitydbms.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionClass {
    @ExceptionHandler
    public ResponseEntity<ResponseDto<Void>> exception(MethodArgumentNotValidException e) {
        return ResponseEntity.ok().body(ResponseDto.<Void>builder()
                .code(-3)
                .message("Validation error")
                .errors(e.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(fieldError -> {
                            String message = fieldError.getDefaultMessage();
                            String field = fieldError.getField();

                            return new ErrorDto(field, message);
                        })
                        .toList()
                )

                .build());
    }
}
