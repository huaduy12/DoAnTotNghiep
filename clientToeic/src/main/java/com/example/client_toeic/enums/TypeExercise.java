package com.example.client_toeic.enums;

public enum TypeExercise {
    TUHOC_TUVUNG("TUHOC_TUVUNG"),
    TUHOC_NGUPHAP("TUHOC_NGUPHAP"),
    TUHOC_BAINGHE("TUHOC_BAINGHE"),
    TUHOC_BAIDOC("TUHOC_BAIDOC");

    // dang còn type cho phần ôn tập nữa
    private String value;

    TypeExercise(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
