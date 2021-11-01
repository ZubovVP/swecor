package ru.zubov.swecor.service.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import ru.zubov.swecor.model.Project;
import ru.zubov.swecor.repository.ProjectRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $.
 * Date: 01.11.2021.
 */
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {
    @Mock
    ProjectRepository repository;
    @InjectMocks
    ProjectService service;

    @BeforeEach
    void setUp() {
        service = new ProjectService(repository);
    }

    @Test
    public void saveProjectShouldReturnProjectWithId() {
        Project project = Project.of(0, "TestProject", new HashSet<>());
        Mockito.when(repository.save(project)).thenReturn(Project.of(1, "TestProject", new HashSet<>()));
        assertEquals(1, service.save(project).get().getId());
    }


    @Test()
    void saveNullProjectShouldReturnException() {
        Project project = null;
        Exception exception = assertThrows(
                NullPointerException.class,
                () -> new ProjectService(repository).save(project));
        assertTrue(exception.getMessage().contains("Project or name of project mustn't be empty"));
    }

    @Test()
    void saveProjectWithEmptyNameShouldReturnException() {
        Project project = Project.of(0, "", new HashSet<>());
        Exception exception = assertThrows(
                NullPointerException.class,
                () -> new ProjectService(repository).save(project));
        assertTrue(exception.getMessage().contains("Project or name of project mustn't be empty"));
    }

    @Test()
    void saveProjectWithNameNullNameShouldReturnException() {
        Project project = Project.of(0, null, new HashSet<>());
        Exception exception = assertThrows(
                NullPointerException.class,
                () -> new ProjectService(repository).save(project));
        assertTrue(exception.getMessage().contains("Project or name of project mustn't be empty"));
    }

    @Test
    void findByIdProjectShouldReturnProject() {
        Project project = Project.of(1, "TestProject", new HashSet<>());
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(project));
        assertEquals(project, service.findById(1).get());
    }

    @Test
    void findAllProjectsShouldReturnProjects() {
        Project project1 = Project.of(1, "TestProject_1", new HashSet<>());
        Project project2 = Project.of(2, "TestProject_2", new HashSet<>());
        Mockito.when(repository.findAll()).thenReturn(Set.of(project1, project2));
        assertTrue(service.findAll().get().contains(project1));
    }

    @Test
    void findAllDeepProjectsShouldReturnAllProjects() {
        Project project1 = Project.of(1, "TestProject_1", new HashSet<>());
        Project project2 = Project.of(2, "TestProject_2", new HashSet<>());
        Mockito.when(repository.findAllDeep()).thenReturn(Set.of(project1, project2));
        assertTrue( service.findAllDeep().get().contains(project1));
    }
}