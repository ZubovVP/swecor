package ru.zubov.swecor.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Service;
import ru.zubov.swecor.model.Project;
import ru.zubov.swecor.repository.ProjectRepository;

import java.util.List;
import java.util.Optional;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $.
 * Date: 29.10.2021.
 */
@Slf4j
@Service
public class ProjectService implements SimpleActionsForService<Project, String> {
    private final ProjectRepository repository;

    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public Project save(Project element) {
        this.repository.save(element);
        log.info("Project {} created successfully", element);
        return null;
    }

    @Override
    public Project findById(String id) {
        Optional<Project> result = this.repository.findById(id);
        if (result.isPresent()) {
            log.info("Find project by id - {} successfully found", id);
        } else {
            log.info("Find project by id - {} don't found", id);
        }
        return result.orElse(null);
    }

    @Override
    public List<Project> findAll() {
        Iterable<Project> result = this.repository.findAll();
        return IterableUtils.toList(result);
    }

    @Override
    public void delete(String id) {
        if (id == null || id.equals("")) {
            log.info("Delete project id - {} is empty.", id);
            return;
        }
        Project project = new Project();
        project.setId(id);
        this.repository.delete(project);
        log.info("Delete project id - {} complete.", id);
    }
}
