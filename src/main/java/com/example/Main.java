package com.example;

import com.example.core.TodoListApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@Component
@RequiredArgsConstructor
@EnableSwagger2
public class Main implements CommandLineRunner {

    private final TodoListApplication todoListApplication;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) {
        todoListApplication.run();
    }
}
