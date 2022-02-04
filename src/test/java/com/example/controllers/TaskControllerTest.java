package com.example.controllers;

import com.example.models.request.CreateTaskRequestDto;
import com.example.services.MapService;
import com.example.services.TaskService;
import com.example.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskController taskController;

    @MockBean
    private UserService userService;

    @MockBean
    private MapService mapService;

    @MockBean
    private TaskService taskService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createTask() throws Exception {
        CreateTaskRequestDto createTaskRequestDto = new CreateTaskRequestDto();
        createTaskRequestDto.setDescription("test1");

        mockMvc.perform(MockMvcRequestBuilders.post("/task")
                .content(objectMapper.writeValueAsString(createTaskRequestDto))
                .contentType(MediaType.APPLICATION_JSON))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    void getTaskList() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/task"))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    void getTaskListAll() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/task/all"))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    void getTaskListBySearch() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/task/search?search=test1"))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    void changeTask() throws Exception {

        CreateTaskRequestDto createTaskRequestDto = new CreateTaskRequestDto();
        createTaskRequestDto.setDescription("test1");

        mockMvc.perform(MockMvcRequestBuilders.patch("/task/1")
                .content(objectMapper.writeValueAsString(createTaskRequestDto))
                .contentType(MediaType.APPLICATION_JSON))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    void deleteTask() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/task/1"))

                .andExpect(MockMvcResultMatchers.status().isOk());

    }
}