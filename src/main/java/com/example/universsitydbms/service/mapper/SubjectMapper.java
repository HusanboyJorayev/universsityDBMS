package com.example.universsitydbms.service.mapper;

import com.example.universsitydbms.dto.SubjectDto;
import com.example.universsitydbms.model.Subject;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class SubjectMapper {
    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    public abstract Subject toEntity(SubjectDto dto);

    public abstract SubjectDto toDto(Subject subject);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Subject subject, SubjectDto dto);
}
