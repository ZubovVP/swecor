package ru.zubov.swecor.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zubov.swecor.model.Project;
import ru.zubov.swecor.service.SimpleActionsForService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    private final SimpleActionsForService<Project, String> projectService;

    public ProjectController(@Qualifier("projectService") SimpleActionsForService<Project, String> projectService) {
        this.projectService = projectService;
    }

    @RequestMapping("/findAll")
    public Map<String, Project> findAll() {
        return this.projectService.findAll();
    }
}
