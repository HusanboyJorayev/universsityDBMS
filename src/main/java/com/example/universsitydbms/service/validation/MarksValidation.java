package com.example.universsitydbms.service.validation;

import com.example.universsitydbms.dto.ErrorDto;
import com.example.universsitydbms.dto.MarksDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MarksValidation {
    public List<ErrorDto> validate(MarksDto dto) {
        List<ErrorDto> error = new ArrayList<>();

        if (dto.getStudentId() == null) {
            error.add(new ErrorDto("studentId", "studentId cannot be null"));
        }
        return error;
    }
}
