package com.example.universsitydbms.service;

import com.example.universsitydbms.dto.*;
import com.example.universsitydbms.model.Groups;
import com.example.universsitydbms.model.Subject;
import com.example.universsitydbms.model.Teachers;
import com.example.universsitydbms.repository.SubjectRepository;
import com.example.universsitydbms.service.mapper.SubjectMapper;
import com.example.universsitydbms.service.validation.SubjectValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService implements SimpleCrud<Integer, SubjectDto> {

    private final SubjectMapper subjectMapper;
    private final SubjectRepository subjectRepository;
    private final SubjectValidation subjectValidation;

    @Override
    public ResponseDto<SubjectDto> create(SubjectDto dto) {
        List<ErrorDto> errors = this.subjectValidation.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<SubjectDto>builder()
                    .code(-3)
                    .message("VAlidation error")
                    .errors(errors)
                    .build();
        }
        Subject subject = this.subjectMapper.toEntity(dto);
        subject.setCreatedAt(LocalDateTime.now());
        this.subjectRepository.save(subject);

        return ResponseDto.<SubjectDto>builder()
                .success(true)
                .message("Ok")
                .data(this.subjectMapper.toDto(subject))
                .build();
    }

    @Override
    public ResponseDto<SubjectDto> get(Integer id) {
        return this.subjectRepository.findByIdAndDeletedAtIsNull(id)
                .map(subject -> ResponseDto.<SubjectDto>builder()
                        .success(true)
                        .message("Ok")
                        .data(this.subjectMapper.toDto(subject))
                        .build())
                .orElse(ResponseDto.<SubjectDto>builder()
                        .code(-1)
                        .message("Group is not found")
                        .build());
    }

    @Override
    public ResponseDto<SubjectDto> update(SubjectDto dto, Integer id) {
        List<ErrorDto> errors = this.subjectValidation.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<SubjectDto>builder()
                    .code(-3)
                    .message("VAlidation error")
                    .errors(errors)
                    .build();
        }

        return this.subjectRepository.findByIdAndDeletedAtIsNull(id)
                .map(subject -> {
                    subject.setUpdatedAt(LocalDateTime.now());
                    this.subjectMapper.update(subject, dto);
                    this.subjectRepository.save(subject);

                    return ResponseDto.<SubjectDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.subjectMapper.toDto(subject))
                            .build();
                })
                .orElse(ResponseDto.<SubjectDto>builder()
                        .code(-1)
                        .message("Groups is not found")
                        .build());
    }

    @Override
    public ResponseDto<SubjectDto> delete(Integer id) {
        return this.subjectRepository.findByIdAndDeletedAtIsNull(id)
                .map(subject -> {
                    subject.setDeletedAt(LocalDateTime.now());
                    //this.subjectRepository.save(subject);
                    return ResponseDto.<SubjectDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.subjectMapper.toDto(subject))
                            .build();
                })
                .orElse(ResponseDto.<SubjectDto>builder()
                        .code(-1)
                        .message("Groups is not found")
                        .build());
    }

    @Override
    public ResponseDto<List<SubjectDto>> getAll() {
        List<Subject> list = this.subjectRepository.getAllSubject();
        if (list.isEmpty()) {
            return ResponseDto.<List<SubjectDto>>builder()
                    .code(-1)
                    .message("Groups are not found")
                    .build();
        }
        return ResponseDto.<List<SubjectDto>>builder()
                .success(true)
                .message("Ok")
                .data(list.stream().map(this.subjectMapper::toDto).toList())
                .build();
    }

    public ResponseDto<Page<SubjectDto>>getByPage(Integer page, Integer size){

        Page<Subject>teachersPage=this.subjectRepository.findAllByDeletedAtIsNull(PageRequest.of(page,size));
        if (teachersPage.isEmpty()) {
            return ResponseDto.<Page<SubjectDto>>builder()
                    .code(-1)
                    .message("Teachers are not found")
                    .build();
        }
        return ResponseDto.<Page<SubjectDto>>builder()
                .success(true)
                .message("Ok")
                .data(teachersPage.map(this.subjectMapper::toDto))
                .build();
    }
}
