package com.example.universsitydbms.service.validation;

import com.example.universsitydbms.dto.ErrorDto;
import com.example.universsitydbms.dto.StudentsDto;
import com.example.universsitydbms.dto.SubjectDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubjectValidation {
    public List<ErrorDto> validate(SubjectDto dto) {
        List<ErrorDto> error = new ArrayList<>();

        if (StringUtils.isBlank(dto.getTitle())) {
            error.add(new ErrorDto("title", "title cannot be null or empty"));
        }

        return error;
    }
}
