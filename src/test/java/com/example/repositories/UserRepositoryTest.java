package com.example.repositories;

import com.example.entities.Task;
import com.example.entities.User;
import com.example.exeption.MyException;
import com.example.services.task.imp.TaskServiceImp;
import com.example.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(initializers = {UserRepositoryTest.Initializer.class})
@Testcontainers
public class UserRepositoryTest {

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:13")
            .withDatabaseName("mydb")
            .withUsername("myuser")
            .withPassword("mypass");
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskServiceImp taskServiceImp;

    @MockBean
    private UserService userService;

    @Test
    public void userCountShouldBeCorrect() {
        long count = userRepository.count();
        assertEquals(1, count);
        assertNotNull(userRepository.findByUserName("admin"));
    }

    @Test
    public void createTaskToBase() {
        User newUser = userRepository.findByUserName("admin");
        Mockito.when(userService.getCurrentUser()).thenReturn(newUser);

        taskServiceImp.createTask("test");

        assertEquals(1, newUser.getTasks().size());
    }

    @Test
    public void findTaskInBase() {
        User newUser = userRepository.findByUserName("admin");
        Mockito.when(userService.getCurrentUser()).thenReturn(newUser);

        taskServiceImp.createTask("test");
        List<Task> tasks = taskServiceImp.searchTask("test");

        assertEquals(1, tasks.size());
    }

    @Test
    public void changeTaskInBase() throws MyException {
        User newUser = userRepository.findByUserName("admin");
        Mockito.when(userService.getCurrentUser()).thenReturn(newUser);

        taskServiceImp.createTask("test");
        List<Task> tasks = taskServiceImp.searchTask("test");
        Task task = tasks.get(0);
        taskServiceImp.changeTask(task.getId(), true, "newDescription");
        task = newUser.getTasks().get(0);
        assertEquals("newDescription", task.getDescription());
        assertEquals(true, task.getClosed());
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword(),
                    "spring.liquibase.enabled=true"
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }
}