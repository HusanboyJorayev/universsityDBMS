package com.example.universsitydbms.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto<T> {

    private int code;
    private boolean success;
    private String message;
    private T data;
    private List<ErrorDto> errors;
}
