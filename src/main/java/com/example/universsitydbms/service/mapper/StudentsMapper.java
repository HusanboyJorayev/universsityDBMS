package com.example.universsitydbms.service.mapper;

import com.example.universsitydbms.dto.GroupsDto;
import com.example.universsitydbms.dto.StudentsDto;
import com.example.universsitydbms.model.Groups;
import com.example.universsitydbms.model.Students;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = Collectors.class)
public abstract class StudentsMapper {

    @Autowired
    protected MarksMapper mapper;

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    public abstract Students toEntity(StudentsDto dto);

    public abstract StudentsDto toDto(Students students);

    @Mapping(target = "marks", expression = "java(students.getMarks().stream().map(this.mapper::toDto).toList())")
    public abstract StudentsDto toDtoWithMArks(Students students);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Students students, StudentsDto dto);

    public void view(Students students, StudentsDto dto) {
        dto.setMarks(students.getMarks().stream().map(this.mapper::toDto).toList());
    }
}
