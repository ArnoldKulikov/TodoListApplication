package com.example.core;

import com.example.dictionaries.ErrorList;

public class MyException extends Exception {

    public MyException(String message) {
        super(ErrorList.ERRORLIST.get(message));
    }
}
