package com.example.data.models;

import com.example.data.dictionaries.ErrorList;

public class MyException extends Exception {

    public MyException(String message) {
        super(ErrorList.ERRORLIST.get(message));
    }
}
