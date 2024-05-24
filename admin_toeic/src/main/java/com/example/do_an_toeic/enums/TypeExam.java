package com.example.do_an_toeic.enums;


public enum TypeExam {
    skill("skill"),
    mini("mini"),
    full("full");

    private String value;
    TypeExam(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
