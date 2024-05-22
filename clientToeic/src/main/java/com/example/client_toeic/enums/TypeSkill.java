package com.example.client_toeic.enums;

public enum TypeSkill {
    PHOTO("photo"),
    QUESTION_RESPONSE("question_response"),
    SHORT_CONVERSATION("short_conversation"),
    SHORT_TALK("short_talk"),
    INCOMPLETE_SENTENCE("incomplete_sentence"),
    TEXT_COMPLETION("text_completion"),
    SINGLE_PASSAGE("single_passage"),
    DOUBLE_PASSAGE("double_passage");

    private String value;

    TypeSkill(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
