package com.jobsity.bowling.model;

public class FileValidatorException extends Exception {

    private final String msg;

    public FileValidatorException(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return msg;
    }
}
