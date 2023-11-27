package com.example.universsitydbms.service.mapper;

import com.example.universsitydbms.dto.GroupsDto;
import com.example.universsitydbms.dto.TeacherDto;
import com.example.universsitydbms.model.Groups;
import com.example.universsitydbms.model.Teachers;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = Collectors.class)
public abstract class TeacherMapper {
    @Autowired
    protected StudentsMapper studentsMapper;

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    public abstract Teachers toEntity(TeacherDto dto);

    public abstract TeacherDto toDto(Teachers teachers);

    @Mapping(target = "students", expression = "java(teachers.getStudents().stream().map(this.studentsMapper::toDto).toList())")
    public abstract TeacherDto toDtoWithStudent(Teachers teachers);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Teachers teachers, TeacherDto dto);

    public void view(TeacherDto dto, Teachers teachers) {
        dto.setStudents(teachers.getStudents().stream().map(this.studentsMapper::toDto).toList());
    }
}
