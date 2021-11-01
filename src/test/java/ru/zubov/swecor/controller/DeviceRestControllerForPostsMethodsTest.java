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
import ru.zubov.swecor.model.Project;
import ru.zubov.swecor.service.device.DeviceActionsAble;

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
class DeviceRestControllerForPostsMethodsTest {
    @MockBean
    private DeviceActionsAble<Device> deviceService;
    @Autowired
    private MockMvc mockMvc;
    private static final ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @Test
    @WithMockUser
    public void createDeviceShouldReturnStatusIsCreatedAndReturnDevice() throws Exception {
        Project project = Project.of(1, "Test_project", new HashSet<>());
        Device device = Device.of(0, project, "4BDC", new HashSet<>());
        when(deviceService.save(device)).thenReturn(Optional.of(device));
        this.mockMvc.perform(
                post("/device/save").contentType(
                        MediaType.APPLICATION_JSON).content(ow.writeValueAsString(device))).andExpect(
                status().isCreated());
        ArgumentCaptor<Device> argument = ArgumentCaptor.forClass(Device.class);
        verify(deviceService).save(argument.capture());
        assertThat(argument.getValue(), is(device));
    }

    @Test
    @WithMockUser
    public void createDeviceWithNameIsNullShouldReturnStatusIsBadRequest() throws Exception {
        this.mockMvc.perform(
                post("/device/save").contentType(
                        MediaType.APPLICATION_JSON).content("")).andExpect(
                status().isBadRequest());
    }

    @Test
    @WithMockUser
    public void deleteDeviceShouldReturnStatusIsOK() throws Exception {
        this.mockMvc.perform(
                post("/device/delete").contentType(
                        MediaType.APPLICATION_JSON).content(ow.writeValueAsString(1))).andExpect(
                status().isOk());
    }
}