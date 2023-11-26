package com.example.universsitydbms.service.mapper;

import com.example.universsitydbms.dto.GroupsDto;
import com.example.universsitydbms.dto.MarksDto;
import com.example.universsitydbms.model.Groups;
import com.example.universsitydbms.model.Marks;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class MarksMapper {

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    public abstract Marks toEntity(MarksDto dto);

    public abstract MarksDto toDto(Marks marks);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Marks marks, MarksDto dto);
}
