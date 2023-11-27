package com.example.universsitydbms.service.mapper;

import com.example.universsitydbms.dto.SubjectDto;
import com.example.universsitydbms.model.Subject;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = Collectors.class)
public abstract class SubjectMapper {

    @Autowired
    protected MarksMapper mapper;

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    public abstract Subject toEntity(SubjectDto dto);

    public abstract SubjectDto toDto(Subject subject);

    @Mapping(target = "marks", expression = "java(subject.getMarks().stream().map(this.mapper::toDto).toList())")
    public abstract SubjectDto toDtoWithMArks(Subject subject);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Subject subject, SubjectDto dto);

    public void view(Subject subject, SubjectDto dto) {
        dto.setMarks(subject.getMarks().stream().map(this.mapper::toDto).toList());
    }
}
