package com.example.client_toeic.enums;


public enum TypeQuestion {
    TYPE_EXERCISE(1),
    TYPE_EXAM(2);

    private int value;

    TypeQuestion(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
