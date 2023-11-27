package com.example.universsitydbms.service;

import com.example.universsitydbms.dto.*;
import com.example.universsitydbms.model.Groups;
import com.example.universsitydbms.model.Marks;
import com.example.universsitydbms.model.Teachers;
import com.example.universsitydbms.repository.MarksRepository;
import com.example.universsitydbms.service.mapper.MarksMapper;
import com.example.universsitydbms.service.validation.MarksValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MarksService implements SimpleCrud<Integer, MarksDto> {
    private final MarksRepository mArksRepository;
    private final MarksMapper mapper;
    private final MarksValidation marksValidation;

    @Override
    public ResponseDto<MarksDto> create(MarksDto dto) {
        List<ErrorDto> errors = this.marksValidation.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<MarksDto>builder()
                    .code(-3)
                    .message("VAlidation error")
                    .errors(errors)
                    .build();
        }
        Marks marks = this.mapper.toEntity(dto);
        marks.setCreatedAt(LocalDateTime.now());
        this.mArksRepository.save(marks);

        return ResponseDto.<MarksDto>builder()
                .success(true)
                .message("Ok")
                .data(this.mapper.toDto(marks))
                .build();
    }

    @Override
    public ResponseDto<MarksDto> get(Integer id) {

        return this.mArksRepository.findByIdAndDeletedAtIsNull(id)
                .map(marks -> ResponseDto.<MarksDto>builder()
                        .success(true)
                        .message("Ok")
                        .data(this.mapper.toDto(marks))
                        .build())
                .orElse(ResponseDto.<MarksDto>builder()
                        .code(-1)
                        .message("Group is not found")
                        .build());
    }

    @Override
    public ResponseDto<MarksDto> update(MarksDto dto, Integer id) {
        List<ErrorDto> errors = this.marksValidation.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<MarksDto>builder()
                    .code(-3)
                    .message("VAlidation error")
                    .errors(errors)
                    .build();
        }

        return this.mArksRepository.findByIdAndDeletedAtIsNull(id)
                .map(marks -> {
                    marks.setUpdatedAt(LocalDateTime.now());
                    this.mapper.update(marks, dto);
                    this.mArksRepository.save(marks);

                    return ResponseDto.<MarksDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.mapper.toDto(marks))
                            .build();
                })
                .orElse(ResponseDto.<MarksDto>builder()
                        .code(-1)
                        .message("Groups is not found")
                        .build());
    }

    @Override
    public ResponseDto<MarksDto> delete(Integer id) {
        return this.mArksRepository.findByIdAndDeletedAtIsNull(id)
                .map(marks -> {
                    marks.setDeletedAt(LocalDateTime.now());
                    //this.mArksRepository.save(marks);
                    return ResponseDto.<MarksDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.mapper.toDto(marks))
                            .build();
                })
                .orElse(ResponseDto.<MarksDto>builder()
                        .code(-1)
                        .message("Groups is not found")
                        .build());
    }

    @Override
    public ResponseDto<List<MarksDto>> getAll() {
        List<Marks> list = this.mArksRepository.getAllMarks();
        if (list.isEmpty()) {
            return ResponseDto.<List<MarksDto>>builder()
                    .code(-1)
                    .message("Groups are not found")
                    .build();
        }
        return ResponseDto.<List<MarksDto>>builder()
                .success(true)
                .message("Ok")
                .data(list.stream().map(this.mapper::toDto).toList())
                .build();
    }

    public ResponseDto<Page<MarksDto>>getByPage(Integer page, Integer size){

        Page<Marks>teachersPage=this.mArksRepository.findAllByDeletedAtIsNull(PageRequest.of(page,size));
        if (teachersPage.isEmpty()) {
            return ResponseDto.<Page<MarksDto>>builder()
                    .code(-1)
                    .message("Teachers are not found")
                    .build();
        }
        return ResponseDto.<Page<MarksDto>>builder()
                .success(true)
                .message("Ok")
                .data(teachersPage.map(this.mapper::toDto))
                .build();
    }
}
