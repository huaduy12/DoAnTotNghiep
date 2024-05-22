package com.example.do_an_toeic.enums;

public enum LevelToeic {
    level400("400"),
    level550("550"),
    level700("700"),
    level850("850");

    private String value;

    LevelToeic(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
