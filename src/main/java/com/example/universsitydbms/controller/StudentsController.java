package com.example.universsitydbms.controller;

import com.example.universsitydbms.dto.ResponseDto;
import com.example.universsitydbms.dto.SimpleCrud;
import com.example.universsitydbms.dto.StudentsDto;
import com.example.universsitydbms.dto.TeacherDto;
import com.example.universsitydbms.service.StudentsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("students")
@Tag(name = "api student")
public class StudentsController implements SimpleCrud<Integer, StudentsDto> {
    private final StudentsService service;

    @Override
    @PostMapping("/create")
    public ResponseDto<StudentsDto> create(@RequestBody @Valid StudentsDto dto) {
        return this.service.create(dto);
    }

    @Override
    @GetMapping("/get")
    public ResponseDto<StudentsDto> get(@RequestParam Integer id) {
        return this.service.get(id);
    }

    @Override
    @PutMapping("/update")
    public ResponseDto<StudentsDto> update(@RequestBody @Valid StudentsDto dto, @RequestParam Integer id) {
        return this.service.update(dto, id);
    }

    @Override
    @DeleteMapping("/delete")
    public ResponseDto<StudentsDto> delete(@RequestParam Integer id) {
        return this.service.delete(id);
    }

    @Override
    @GetMapping("/getAll")
    public ResponseDto<List<StudentsDto>> getAll() {
        return this.service.getAll();
    }

    @GetMapping("/getPage")
    public ResponseDto<Page<StudentsDto>> getByPage(@RequestParam Integer page, @RequestParam Integer size) {
        return this.service.getByPage(page, size);
    }
}
