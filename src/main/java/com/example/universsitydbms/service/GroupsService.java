package com.example.universsitydbms.service;

import com.example.universsitydbms.dto.ErrorDto;
import com.example.universsitydbms.dto.GroupsDto;
import com.example.universsitydbms.dto.ResponseDto;
import com.example.universsitydbms.dto.SimpleCrud;
import com.example.universsitydbms.model.Groups;
import com.example.universsitydbms.repository.GroupRepository;
import com.example.universsitydbms.service.mapper.GroupsMapper;
import com.example.universsitydbms.service.validation.GroupsValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupsService implements SimpleCrud<Integer, GroupsDto> {

    private final GroupsMapper groupsMapper;
    private final GroupsValidation groupsValidation;
    private final GroupRepository groupRepository;

    @Override
    public ResponseDto<GroupsDto> create(GroupsDto dto) {
        List<ErrorDto> errors = this.groupsValidation.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<GroupsDto>builder()
                    .code(-3)
                    .message("VAlidation error")
                    .errors(errors)
                    .build();
        }
        Groups groups = this.groupsMapper.toEntity(dto);
        groups.setCreatedAt(LocalDateTime.now());
        this.groupRepository.save(groups);

        return ResponseDto.<GroupsDto>builder()
                .success(true)
                .message("Ok")
                .data(this.groupsMapper.toDto(groups))
                .build();
    }

    @Override
    public ResponseDto<GroupsDto> get(Integer id) {

        return this.groupRepository.findByIdAndDeletedAtIsNull(id)
                .map(groups -> ResponseDto.<GroupsDto>builder()
                        .success(true)
                        .message("Ok")
                        .data(this.groupsMapper.toDto(groups))
                        .build())
                .orElse(ResponseDto.<GroupsDto>builder()
                        .code(-1)
                        .message("Group is not found")
                        .build());

    }

    @Override
    public ResponseDto<GroupsDto> update(GroupsDto dto, Integer id) {
        List<ErrorDto> errors = this.groupsValidation.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<GroupsDto>builder()
                    .code(-3)
                    .message("VAlidation error")
                    .errors(errors)
                    .build();
        }

        return this.groupRepository.findByIdAndDeletedAtIsNull(id)
                .map(groups -> {
                    groups.setUpdatedAt(LocalDateTime.now());
                    this.groupsMapper.update(groups, dto);
                    this.groupRepository.save(groups);

                    return ResponseDto.<GroupsDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.groupsMapper.toDto(groups))
                            .build();
                })
                .orElse(ResponseDto.<GroupsDto>builder()
                        .code(-1)
                        .message("Groups is not found")
                        .build());

    }

    @Override
    public ResponseDto<GroupsDto> delete(Integer id) {

        return this.groupRepository.findByIdAndDeletedAtIsNull(id)
                .map(groups -> {
                    groups.setDeletedAt(LocalDateTime.now());
                    //this.groupRepository.save(groups);
                    return ResponseDto.<GroupsDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.groupsMapper.toDto(groups))
                            .build();
                })
                .orElse(ResponseDto.<GroupsDto>builder()
                        .code(-1)
                        .message("Groups is not found")
                        .build());

    }

    @Override
    public ResponseDto<List<GroupsDto>> getAll() {

        List<Groups> list = this.groupRepository.getAllGroups();
        if (list.isEmpty()) {
            return ResponseDto.<List<GroupsDto>>builder()
                    .code(-1)
                    .message("Groups are not found")
                    .build();
        }
        return ResponseDto.<List<GroupsDto>>builder()
                .success(true)
                .message("Ok")
                .data(list.stream().map(this.groupsMapper::toDto).toList())
                .build();
    }

}
