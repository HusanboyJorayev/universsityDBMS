package com.example.universsitydbms.service;

import com.example.universsitydbms.dto.*;
import com.example.universsitydbms.model.Groups;
import com.example.universsitydbms.model.Teachers;
import com.example.universsitydbms.repository.TeachersRepository;
import com.example.universsitydbms.service.mapper.TeacherMapper;
import com.example.universsitydbms.service.validation.TeacherValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeachersService implements SimpleCrud<Integer, TeacherDto> {

    private final TeachersRepository teachersRepository;
    private final TeacherMapper teacherMapper;
    private final TeacherValidation teacherValidation;

    @Override
    public ResponseDto<TeacherDto> create(TeacherDto dto) {
        List<ErrorDto> errors = this.teacherValidation.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<TeacherDto>builder()
                    .code(-3)
                    .message("VAlidation error")
                    .errors(errors)
                    .build();
        }
        Teachers teachers = this.teacherMapper.toEntity(dto);
        teachers.setCreatedAt(LocalDateTime.now());
        this.teachersRepository.save(teachers);

        return ResponseDto.<TeacherDto>builder()
                .success(true)
                .message("Ok")
                .data(this.teacherMapper.toDto(teachers))
                .build();
    }

    @Override
    public ResponseDto<TeacherDto> get(Integer id) {

        return this.teachersRepository.findByIdAndDeletedAtIsNull(id)
                .map(teachers -> ResponseDto.<TeacherDto>builder()
                        .success(true)
                        .message("Ok")
                        .data(this.teacherMapper.toDtoWithStudent(teachers))
                        .build())
                .orElse(ResponseDto.<TeacherDto>builder()
                        .code(-1)
                        .message("Group is not found")
                        .build());
    }

    @Override
    public ResponseDto<TeacherDto> update(TeacherDto dto, Integer id) {
        List<ErrorDto> errors = this.teacherValidation.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<TeacherDto>builder()
                    .code(-3)
                    .message("VAlidation error")
                    .errors(errors)
                    .build();
        }

        return this.teachersRepository.findByIdAndDeletedAtIsNull(id)
                .map(teachers -> {
                    teachers.setUpdatedAt(LocalDateTime.now());
                    this.teacherMapper.update(teachers, dto);
                    this.teachersRepository.save(teachers);

                    return ResponseDto.<TeacherDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.teacherMapper.toDto(teachers))
                            .build();
                })
                .orElse(ResponseDto.<TeacherDto>builder()
                        .code(-1)
                        .message("Groups is not found")
                        .build());
    }

    @Override
    public ResponseDto<TeacherDto> delete(Integer id) {
        return this.teachersRepository.findByIdAndDeletedAtIsNull(id)
                .map(teachers -> {
                    teachers.setDeletedAt(LocalDateTime.now());
                    //this.teachersRepository.save(teachers);
                    return ResponseDto.<TeacherDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.teacherMapper.toDto(teachers))
                            .build();
                })
                .orElse(ResponseDto.<TeacherDto>builder()
                        .code(-1)
                        .message("Groups is not found")
                        .build());
    }

    @Override
    public ResponseDto<List<TeacherDto>> getAll() {
        List<Teachers> list = this.teachersRepository.getAllTeachers();
        if (list.isEmpty()) {
            return ResponseDto.<List<TeacherDto>>builder()
                    .code(-1)
                    .message("Groups are not found")
                    .build();
        }
        return ResponseDto.<List<TeacherDto>>builder()
                .success(true)
                .message("Ok")
                .data(list.stream().map(this.teacherMapper::toDto).toList())
                .build();
    }
}
