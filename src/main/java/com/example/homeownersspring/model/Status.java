package com.example.homeownersspring.model;

public enum Status {
    NEW(1), ACCEPTED(2),COMPLETED(3);

    private final int code;

    Status(int code) {
        this.code=code;
    }

    public int getCode() {
        return code;
    }
}