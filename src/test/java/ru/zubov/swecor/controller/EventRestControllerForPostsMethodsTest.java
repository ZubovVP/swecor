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
import ru.zubov.swecor.model.Device;
import ru.zubov.swecor.model.Event;
import ru.zubov.swecor.model.Project;
import ru.zubov.swecor.model.Type;
import ru.zubov.swecor.service.event.EventActionsAble;

import java.util.Date;
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
 * Date: 01.11.2021.
 */
@SpringBootTest(classes = SwecorApplication.class)
@AutoConfigureMockMvc
class EventRestControllerForPostsMethodsTest {
    @MockBean
    private EventActionsAble<Event> eventService;
    @Autowired
    private MockMvc mockMvc;
    private static final ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @Test
    @WithMockUser
    public void createEventShouldReturnStatusIsCreatedAndReturnDevice() throws Exception {
       Event event = Event.of(0, Device.of(1, new Project(), null, new HashSet<>()), new Date(System.currentTimeMillis()), Type.EVENT, false);
        when(eventService.save(event)).thenReturn(Optional.of(event));
        this.mockMvc.perform(
                post("/event/save").contentType(
                        MediaType.APPLICATION_JSON).content(ow.writeValueAsString(event))).andExpect(
                status().isCreated());
        ArgumentCaptor<Event> argument = ArgumentCaptor.forClass(Event.class);
        verify(eventService).save(argument.capture());
        assertThat(argument.getValue(), is(event));
    }

    @Test
    @WithMockUser
    public void createEventWithNameIsNullShouldReturnStatusIsBadRequest() throws Exception {
        this.mockMvc.perform(
                post("/event/save").contentType(
                        MediaType.APPLICATION_JSON).content("")).andExpect(
                status().isBadRequest());
    }

    @Test
    @WithMockUser
    public void deleteEventShouldReturnStatusIsOK() throws Exception {
        this.mockMvc.perform(
                post("/event/delete").contentType(
                        MediaType.APPLICATION_JSON).content(ow.writeValueAsString(1))).andExpect(
                status().isOk());
    }
}