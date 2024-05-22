package com.example.client_toeic.enums;

public enum TypeResource {

    IMAGE("Image"),
    AUDIO("Audio");

    private String value;

    TypeResource(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
