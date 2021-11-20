package com.example.parsers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
@Component
public class Editor {

    public static String read() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return bufferedReader.readLine();
        } catch (IOException ex) {
            write(ex.toString());
            log.error(ex.getMessage());
        }
        return "";
    }

    public static void write(String msg) {
        System.out.println(msg);
    }
}
