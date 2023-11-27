package com.example.universsitydbms.service;

import com.example.universsitydbms.dto.*;
import com.example.universsitydbms.model.Groups;
import com.example.universsitydbms.model.Students;
import com.example.universsitydbms.model.Teachers;
import com.example.universsitydbms.repository.StudentsRepository;
import com.example.universsitydbms.service.mapper.StudentsMapper;
import com.example.universsitydbms.service.validation.StudentsValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentsService implements SimpleCrud<Integer, StudentsDto> {

    private final StudentsRepository studentsRepository;
    private final StudentsMapper studentsMapper;
    private final StudentsValidation studentsValidation;

    @Override
    public ResponseDto<StudentsDto> create(StudentsDto dto) {
        List<ErrorDto> errors = this.studentsValidation.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<StudentsDto>builder()
                    .code(-3)
                    .message("VAlidation error")
                    .errors(errors)
                    .build();
        }
        Students s = this.studentsMapper.toEntity(dto);
        s.setCreatedAt(LocalDateTime.now());
        this.studentsRepository.save(s);

        return ResponseDto.<StudentsDto>builder()
                .success(true)
                .message("Ok")
                .data(this.studentsMapper.toDto(s))
                .build();
    }

    @Override
    public ResponseDto<StudentsDto> get(Integer id) {
        return this.studentsRepository.findByIdAndDeletedAtIsNull(id)
                .map(students -> ResponseDto.<StudentsDto>builder()
                        .success(true)
                        .message("Ok")
                        .data(this.studentsMapper.toDtoWithMArks(students))
                        .build())
                .orElse(ResponseDto.<StudentsDto>builder()
                        .code(-1)
                        .message("Group is not found")
                        .build());
    }

    @Override
    public ResponseDto<StudentsDto> update(StudentsDto dto, Integer id) {
        List<ErrorDto> errors = this.studentsValidation.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<StudentsDto>builder()
                    .code(-3)
                    .message("VAlidation error")
                    .errors(errors)
                    .build();
        }

        return this.studentsRepository.findByIdAndDeletedAtIsNull(id)
                .map(students -> {
                    students.setUpdatedAt(LocalDateTime.now());
                    this.studentsMapper.update(students, dto);
                    this.studentsRepository.save(students);

                    return ResponseDto.<StudentsDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.studentsMapper.toDto(students))
                            .build();
                })
                .orElse(ResponseDto.<StudentsDto>builder()
                        .code(-1)
                        .message("Groups is not found")
                        .build());
    }

    @Override
    public ResponseDto<StudentsDto> delete(Integer id) {
        return this.studentsRepository.findByIdAndDeletedAtIsNull(id)
                .map(students -> {
                    students.setDeletedAt(LocalDateTime.now());
                    //this.studentsRepository.save(students);
                    return ResponseDto.<StudentsDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.studentsMapper.toDto(students))
                            .build();
                })
                .orElse(ResponseDto.<StudentsDto>builder()
                        .code(-1)
                        .message("Groups is not found")
                        .build());
    }

    @Override
    public ResponseDto<List<StudentsDto>> getAll() {
        List<Students> list = this.studentsRepository.getAllStudents();
        if (list.isEmpty()) {
            return ResponseDto.<List<StudentsDto>>builder()
                    .code(-1)
                    .message("Groups are not found")
                    .build();
        }
        return ResponseDto.<List<StudentsDto>>builder()
                .success(true)
                .message("Ok")
                .data(list.stream().map(this.studentsMapper::toDto).toList())
                .build();
    }

    public ResponseDto<Page<StudentsDto>>getByPage(Integer page, Integer size){

        Page<Students>teachersPage=this.studentsRepository.findAllByDeletedAtIsNull(PageRequest.of(page,size));
        if (teachersPage.isEmpty()) {
            return ResponseDto.<Page<StudentsDto>>builder()
                    .code(-1)
                    .message("Teachers are not found")
                    .build();
        }
        return ResponseDto.<Page<StudentsDto>>builder()
                .success(true)
                .message("Ok")
                .data(teachersPage.map(this.studentsMapper::toDto))
                .build();
    }
}
