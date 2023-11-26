package com.example.universsitydbms.service.validation;

import com.example.universsitydbms.dto.ErrorDto;
import com.example.universsitydbms.dto.MarksDto;
import com.example.universsitydbms.dto.StudentsDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentsValidation {

    public List<ErrorDto> validate(StudentsDto dto) {
        List<ErrorDto> error = new ArrayList<>();

        if (StringUtils.isBlank(dto.getFirstname())) {
            error.add(new ErrorDto("firstname", "firstname cannot be null or empty"));
        }
        if (StringUtils.isBlank(dto.getLastname())) {
            error.add(new ErrorDto("lastname", "lastname cannot be null or empty"));
        }
        return error;
    }
}
