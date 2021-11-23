package com.example;

import com.example.core.TodoListApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component
@RequiredArgsConstructor
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
