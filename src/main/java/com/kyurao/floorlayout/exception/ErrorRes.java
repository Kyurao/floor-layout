package com.kyurao.floorlayout.exception;

public class ErrorRes {

    private final String error;

    ErrorRes(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

}
