package com.example.universsitydbms.controller;

import com.example.universsitydbms.dto.ResponseDto;
import com.example.universsitydbms.dto.SimpleCrud;
import com.example.universsitydbms.dto.SubjectDto;
import com.example.universsitydbms.service.SubjectService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("subject")
@Tag(name = "api subject")
public class SubjectController implements SimpleCrud<Integer, SubjectDto> {

    private final SubjectService service;

    @Override
    @PostMapping("/create")
    public ResponseDto<SubjectDto> create(@RequestBody @Valid SubjectDto dto) {
        return this.service.create(dto);
    }

    @Override
    @GetMapping("/get")
    public ResponseDto<SubjectDto> get(@RequestParam Integer id) {
        return this.service.get(id);
    }

    @Override
    @PutMapping("/update")
    public ResponseDto<SubjectDto> update(@RequestBody @Valid SubjectDto dto, @RequestParam Integer id) {
        return this.service.update(dto, id);
    }

    @Override
    @DeleteMapping("/delete")
    public ResponseDto<SubjectDto> delete(@RequestParam Integer id) {
        return this.service.delete(id);
    }

    @Override
    @GetMapping("/getAll")
    public ResponseDto<List<SubjectDto>> getAll() {
        return this.service.getAll();
    }
}
