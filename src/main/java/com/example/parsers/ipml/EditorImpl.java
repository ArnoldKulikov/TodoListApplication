package com.example.parsers.ipml;

import com.example.parsers.Editor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
@Component
public class EditorImpl implements Editor {

    public String read() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return bufferedReader.readLine();
        } catch (IOException ex) {
            write(ex.toString());
            log.error(ex.getMessage());
        }
        return "";
    }

    public void write(String msg) {
        System.out.println(msg);
    }
}
