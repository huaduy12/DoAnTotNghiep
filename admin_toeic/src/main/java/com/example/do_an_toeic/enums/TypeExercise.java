package com.example.do_an_toeic.enums;

public enum TypeExercise {
    TUHOC_TUVUNG("TUHOC_TUVUNG"),
    TUHOC_NGUPHAP("TUHOC_NGUPHAP"),
    TUHOC_BAINGHE("TUHOC_BAINGHE"),
    TUHOC_BAIDOC("TUHOC_BAIDOC"),

    // luu vao db
     PHOTO("photo"),
     QUESTION_RESPONSE("question_response"),
     SHORT_CONVERSATION("short_conversation"),
     SHORT_TALK("short_talk"),
     INCOMPLETE_SENTENCE("incomplete_sentence"),
     TEXT_COMPLETION("text_completion"),
     SINGLE_PASSAGE("single_passage"),
     DOUBLE_PASSAGE("double_passage"),

    // ôn tập
    ONTAP("ONTAP");







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
