package com.example.universsitydbms.service.validation;

import com.example.universsitydbms.dto.ErrorDto;
import com.example.universsitydbms.dto.GroupsDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GroupsValidation {

    public List<ErrorDto> validate(GroupsDto dto) {
        List<ErrorDto> errors = new ArrayList<>();
        if (StringUtils.isBlank(dto.getName())) {
            errors.add(new ErrorDto("name", "name cannot be null or empty"));
        }
        return errors;
    }
}
