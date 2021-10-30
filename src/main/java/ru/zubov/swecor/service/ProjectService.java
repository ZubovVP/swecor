package ru.zubov.swecor.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Service;
import ru.zubov.swecor.model.Project;
import ru.zubov.swecor.repository.ProjectRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $.
 * Date: 29.10.2021.
 */
@Slf4j
@Service
public class ProjectService implements SimpleActionsForService<Project, Integer> {
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
    public Project findById(Integer id) {
        Optional<Project> result = this.repository.findById(id);
        if (result.isPresent()) {
            log.info("Find project by id - {} successfully found", id);
        } else {
            log.info("Find project by id - {} don't found", id);
        }
        return result.orElse(null);
    }

    @Override
    public Set<Project> findAll() {
        Iterable<Project> result = this.repository.findAll();
        return new HashSet<>(IterableUtils.toList(result));
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.info("Delete project id - {} is empty.", id);
            return;
        }
        Project project = new Project();
        project.setId(id);
        this.repository.delete(project);
        log.info("Delete project id - {} complete.", id);
    }

    @Override
    public Set<Project> findAllDeep() {
        return this.repository.findAllDeep();
    }
}
