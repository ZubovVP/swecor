package ru.zubov.swecor.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.zubov.swecor.SwecorApplication;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $.
 * Date: 31.10.2021.
 */
@SpringBootTest(classes = SwecorApplication.class)
@AutoConfigureMockMvc
class DeviceRestControllerForGetMethodsTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void findDevicesShouldReturnDefaultMessageWithStatusIsFound() throws Exception {
        this.mockMvc.perform(get("/device/findDevices/1"))
                .andDo(print())
                .andExpect(status().isFound());
    }

    @Test
    @WithMockUser
    public void findAllShouldReturnDefaultWithStatusIsFound() throws Exception {
        this.mockMvc.perform(get("/device/find"))
                .andDo(print())
                .andExpect(status().isFound());
    }

    @Test
    @WithMockUser
    public void findByIdShouldReturnDefaultWithStatusIsFound() throws Exception {
        this.mockMvc.perform(get("/device/find/1"))
                .andDo(print())
                .andExpect(status().isFound());
    }
}