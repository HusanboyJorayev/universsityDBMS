package com.example.universsitydbms.service.mapper;

import com.example.universsitydbms.dto.GroupsDto;
import com.example.universsitydbms.dto.TeacherDto;
import com.example.universsitydbms.model.Groups;
import com.example.universsitydbms.model.Teachers;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class TeacherMapper {

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    public abstract Teachers toEntity(TeacherDto dto);

    public abstract TeacherDto toDto(Teachers teachers);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Teachers teachers, TeacherDto dto);
}
