package ru.zubov.swecor.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.zubov.swecor.SwecorApplication;
import ru.zubov.swecor.model.Project;
import ru.zubov.swecor.service.project.ProjectActionsAble;

import java.util.HashSet;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.verify;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $.
 * Date: 31.10.2021.
 */
@SpringBootTest(classes = SwecorApplication.class)
@AutoConfigureMockMvc
class ProjectRestControllerForPostsMethodsTest {
    @MockBean
    private ProjectActionsAble<Project> projectService;
    @Autowired
    private MockMvc mockMvc;
    private static final ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @Test
    @WithMockUser
    public void createProjectShouldReturnStatusIsCreatedAndReturnProject() throws Exception {
        Project project = Project.of(0, "Project_test", new HashSet<>());
        when(projectService.save(project)).thenReturn(Optional.of(project));
        this.mockMvc.perform(
                post("/project/save").contentType(
                        MediaType.APPLICATION_JSON).content(ow.writeValueAsString(project))).andExpect(
                status().isCreated());
        ArgumentCaptor<Project> argument = ArgumentCaptor.forClass(Project.class);
        verify(projectService).save(argument.capture());
        assertThat(argument.getValue(), is(project));
    }

    @Test
    @WithMockUser
    public void createProjectWithNameIsNullShouldReturnStatusIsBadRequest() throws Exception {
        this.mockMvc.perform(
                post("/project/save").contentType(
                        MediaType.APPLICATION_JSON).content("")).andExpect(
                status().isBadRequest());
    }

    @Test
    @WithMockUser
    public void deleteProjectShouldReturnStatusIsOK() throws Exception {
        this.mockMvc.perform(
                post("/project/delete").contentType(
                        MediaType.APPLICATION_JSON).content(ow.writeValueAsString(1))).andExpect(
                status().isOk());
    }
}