package com.example.universsitydbms.controller;

import com.example.universsitydbms.dto.ResponseDto;
import com.example.universsitydbms.dto.SimpleCrud;
import com.example.universsitydbms.dto.TeacherDto;
import com.example.universsitydbms.model.Teachers;
import com.example.universsitydbms.service.TeachersService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("teacher")
@Tag(name = "api teacher")
public class TeacherController implements SimpleCrud<Integer, TeacherDto> {
    private final TeachersService teachersService;

    @Override
    @PostMapping("/create")
    public ResponseDto<TeacherDto> create(@RequestBody @Valid TeacherDto dto) {
        return this.teachersService.create(dto);
    }

    @Override
    @GetMapping("/get")
    public ResponseDto<TeacherDto> get(@RequestParam Integer id) {
        return this.teachersService.get(id);
    }

    @Override
    @PutMapping("/update")
    public ResponseDto<TeacherDto> update(@RequestBody @Valid TeacherDto dto, @RequestParam Integer id) {
        return this.teachersService.update(dto, id);
    }

    @Override
    @DeleteMapping("/delete")
    public ResponseDto<TeacherDto> delete(@RequestParam Integer id) {
        return this.teachersService.delete(id);
    }

    @Override
    @GetMapping("/getAll")
    public ResponseDto<List<TeacherDto>> getAll() {
        return this.teachersService.getAll();
    }

    @GetMapping("/getPage")
    public ResponseDto<Page<TeacherDto>> getByPage(@RequestParam Integer page, @RequestParam Integer size) {
        return this.teachersService.getByPage(page, size);
    }
}
