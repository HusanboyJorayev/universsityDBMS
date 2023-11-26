package com.example.universsitydbms.service.mapper;

import com.example.universsitydbms.dto.GroupsDto;
import com.example.universsitydbms.dto.StudentsDto;
import com.example.universsitydbms.model.Groups;
import com.example.universsitydbms.model.Students;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class StudentsMapper {
    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    public abstract Students toEntity(StudentsDto dto);

    public abstract StudentsDto toDto(Students students);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Students students, StudentsDto dto);
}
