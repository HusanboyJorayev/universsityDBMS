package com.example.universsitydbms.service.mapper;

import com.example.universsitydbms.dto.GroupsDto;
import com.example.universsitydbms.model.Groups;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = Collectors.class)
public abstract class GroupsMapper {
    @Autowired
    protected StudentsMapper studentsMapper;

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    public abstract Groups toEntity(GroupsDto dto);

    public abstract GroupsDto toDto(Groups groups);

    @Mapping(target = "students", expression = "java(groups.getStudents().stream().map(this.studentsMapper::toDto).toList())")
    public abstract GroupsDto toDtoWithStudents(Groups groups);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Groups groups, GroupsDto dto);

    public void view(Groups groups, GroupsDto dto) {
        dto.setStudents(groups.getStudents().stream().map(this.studentsMapper::toDto).toList());
    }
}
