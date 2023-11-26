package com.example.universsitydbms.controller;

import com.example.universsitydbms.dto.MarksDto;
import com.example.universsitydbms.dto.ResponseDto;
import com.example.universsitydbms.dto.SimpleCrud;
import com.example.universsitydbms.service.MarksService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("marks")
@Tag(name = "api marks")
public class MarksController implements SimpleCrud<Integer, MarksDto> {
    private final MarksService marksService;

    @Override
    @PostMapping("/create")
    public ResponseDto<MarksDto> create(@RequestBody @Valid MarksDto dto) {
        return this.marksService.create(dto);
    }

    @Override
    @GetMapping("/get")
    public ResponseDto<MarksDto> get(@RequestParam Integer id) {
        return this.marksService.get(id);
    }

    @Override
    @PutMapping("/update")
    public ResponseDto<MarksDto> update(@RequestBody @Valid MarksDto dto, @RequestParam Integer id) {
        return this.marksService.update(dto,id);
    }

    @Override
    @DeleteMapping("/delete")
    public ResponseDto<MarksDto> delete(@RequestParam Integer id) {
        return this.marksService.delete(id);
    }

    @Override
    @GetMapping("/getAll")
    public ResponseDto<List<MarksDto>> getAll() {
        return this.marksService.getAll();
    }
}
