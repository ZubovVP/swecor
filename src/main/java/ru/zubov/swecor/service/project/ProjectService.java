package ru.zubov.swecor.service.project;

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
public class ProjectService implements ProjectActionsAble<Project> {
    private final ProjectRepository repository;

    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Project> save(Project element) {
        if(!validateProject(element)){
            throw new NullPointerException("Project doesn't have name");
        }
        log.info("Processing add/update project {}", element);
        Optional<Project> result = Optional.of(this.repository.save(element));
        log.info("Project {} created successfully", element);
        return result;
    }

    @Override
    public Optional<Project> findById(Integer id) {
        Optional<Project> result = this.repository.findById(id);
        if (result.isPresent()) {
            log.info("Find project by id - {} successfully", id);
        } else {
            log.info("Find project by id - {} don't found", id);
        }
        return result;
    }

    @Override
    public Optional<Set<Project>> findAll() {
        Iterable<Project> result = this.repository.findAll();
        return Optional.of(new HashSet<>(IterableUtils.toList(result)));
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
    public Optional<Set<Project>> findAllDeep() {
        return Optional.of(this.repository.findAllDeep());
    }

    private boolean validateProject(Project project) {
        return !project.getName().isBlank();
    }
}
