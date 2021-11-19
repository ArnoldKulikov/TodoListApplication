package com.example;

import com.example.core.TodoListApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        TodoListApplication.getInstance().run();
    }
}
