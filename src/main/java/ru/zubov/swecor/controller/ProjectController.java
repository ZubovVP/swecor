package ru.zubov.swecor.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zubov.swecor.model.Project;
import ru.zubov.swecor.service.SimpleActionsForService;

import java.util.Set;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $.
 * Date: 29.10.2021.
 */
@RestController
@RequestMapping("/project")
public class ProjectController {
    private final SimpleActionsForService<Project, Integer> projectService;

    public ProjectController(@Qualifier("projectService") SimpleActionsForService<Project, Integer> projectService) {
        this.projectService = projectService;
    }

    @RequestMapping("/findAll")
    public Set<Project> findAll() {
        return this.projectService.findAll();
    }


    @GetMapping("/findAllDeep")
    public Set<Project> findAllDeep() {
        return this.projectService.findAllDeep();
    }
}
