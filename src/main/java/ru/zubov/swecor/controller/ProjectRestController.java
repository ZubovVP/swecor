package ru.zubov.swecor.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zubov.swecor.model.Project;
import ru.zubov.swecor.model.dto.ProjectDTO;
import ru.zubov.swecor.model.projectInfo.ProjectInfo;
import ru.zubov.swecor.service.project.ProjectActionsAble;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $.
 * Date: 29.10.2021.
 */
@RestController
@RequestMapping("/project")
public class ProjectRestController {
    private final ProjectActionsAble<Project> projectService;

    public ProjectRestController(ProjectActionsAble<Project> projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/findAllProjects")
    public ResponseEntity<Set<ProjectInfo>> findAllDeep() {
        Optional<Set<Project>> projects = this.projectService.findAllDeep();
        return projects.map(projectSet -> new ResponseEntity<>(projectSet.stream().map(ProjectInfo::projectToDeviceInfo).collect(Collectors.toSet()), HttpStatus.FOUND))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
    }

    @PostMapping("/save")
    public ResponseEntity<ProjectDTO> saveOrUpdate(@RequestBody Project project) {
        Optional<Project> result = this.projectService.save(project);
        return result.map(value -> new ResponseEntity<>(
                new ProjectDTO(value), HttpStatus.CREATED
        )).orElseGet(() -> new ResponseEntity<>(
                null, HttpStatus.BAD_REQUEST
        ));
    }

    @GetMapping("/find")
    private ResponseEntity<Set<ProjectDTO>> findAll() {
        Optional<Set<Project>> projects = this.projectService.findAll();
        return projects.map(projectSet -> new ResponseEntity<>(projectSet.stream().map(ProjectDTO::new).collect(Collectors.toSet()), HttpStatus.FOUND))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ProjectDTO> findById(@PathVariable int id) {
        Optional<Project> device = this.projectService.findById(id);
        return device.map(value -> new ResponseEntity<>(new ProjectDTO(value), HttpStatus.FOUND))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody int id) {
        this.projectService.delete(id);
        return ResponseEntity.ok().build();
    }
}
