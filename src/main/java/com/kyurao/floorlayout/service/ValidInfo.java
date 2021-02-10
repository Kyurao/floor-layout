package com.kyurao.floorlayout.service;

public class ValidInfo {

    private final boolean valid;

    private final String message;

    public ValidInfo(boolean valid, String  message) {
        this.valid = valid;
        this.message = message;
    }

    public boolean isValid() {
        return valid;
    }

    public String getMessage() {
        return message;
    }
}
