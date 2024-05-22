package com.example.do_an_toeic.enums;

public enum Level {
    DE("DE"),
    TRUNGBINH("TRUNGBINH"),
    KHO("KHO");

    private String value;

    Level(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
