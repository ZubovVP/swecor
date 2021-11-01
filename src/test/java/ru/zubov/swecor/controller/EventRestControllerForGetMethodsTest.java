package ru.zubov.swecor.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.zubov.swecor.SwecorApplication;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
class EventRestControllerForGetMethodsTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void findAllShouldReturnDefaultWithStatusIsFound() throws Exception {
        this.mockMvc.perform(get("/event/find"))
                .andDo(print())
                .andExpect(status().isFound());
    }

    @Test
    @WithMockUser
    public void findByIdShouldReturnDefaultWithStatusIsFound() throws Exception {
        this.mockMvc.perform(get("/event/find/1"))
                .andDo(print())
                .andExpect(status().isFound());
    }
}