package com.example.core;

import com.example.dictionaries.ErrorList;

public class ErrorExecuteBy {

    public void idOnConsole(String errorCode) {

        if(ErrorList.ERRORLIST.containsKey(errorCode)) {
            String errorText = ErrorList.ERRORLIST.get(errorCode);

            Logback.logger.error(errorText);
            System.out.println(errorText);
        }

        else {
            String errorText = ErrorList.ERRORLIST.get("unknownError");

            Logback.logger.error(errorText);
            System.out.println(errorText);
        }

    }
}
