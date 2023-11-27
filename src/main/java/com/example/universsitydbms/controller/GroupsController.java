package com.example.universsitydbms.controller;

import com.example.universsitydbms.dto.GroupsDto;
import com.example.universsitydbms.dto.ResponseDto;
import com.example.universsitydbms.dto.SimpleCrud;
import com.example.universsitydbms.dto.TeacherDto;
import com.example.universsitydbms.service.GroupsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("groups")
@Tag(name = "Api Group")
public class GroupsController implements SimpleCrud<Integer, GroupsDto> {
    private final GroupsService groupsService;

    @Override
    @PostMapping("/create")
    public ResponseDto<GroupsDto> create(@RequestBody @Valid GroupsDto dto) {
        return this.groupsService.create(dto);
    }

    @Override
    @GetMapping("/get")
    public ResponseDto<GroupsDto> get(@RequestParam Integer id) {
        return this.groupsService.get(id);
    }

    @Override
    @PutMapping("/update")
    public ResponseDto<GroupsDto> update(@RequestBody @Valid GroupsDto dto, @RequestParam Integer id) {
        return this.groupsService.update(dto, id);
    }

    @Override
    @DeleteMapping("/delete")
    public ResponseDto<GroupsDto> delete(@RequestParam Integer id) {
        return this.groupsService.delete(id);
    }

    @Override
    @GetMapping("/getAll")
    public ResponseDto<List<GroupsDto>> getAll() {
        return this.groupsService.getAll();
    }

    @GetMapping("/getPage")
    public ResponseDto<Page<GroupsDto>> getByPage(@RequestParam Integer page, @RequestParam Integer size) {
        return this.groupsService.getByPage(page, size);
    }
}
