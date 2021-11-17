package com.example.parsers;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
public class Editor {

    public static String read() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return bufferedReader.readLine();
        } catch (IOException ex) {
            Editor.write(ex.toString());
            log.error(ex.getMessage());
        }
        return null;
    }

    public static void write(String msg) {
        System.out.println(msg);
    }
}
