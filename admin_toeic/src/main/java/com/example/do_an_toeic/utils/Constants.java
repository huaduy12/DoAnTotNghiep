package com.example.do_an_toeic.utils;

import com.example.do_an_toeic.enums.LevelToeic;

public class Constants {

    public static String ROLE_ADMIN = "ROLE_ADMIN";
    public static String ROLE_MANAGER = "ROLE_MANAGER";
    public static String ROLE_USER = "ROLE_USER";
    public static String TYPE_MANAGER = "TYPE_MANAGER";
    public static String TYPE_USER = "TYPE_USER";



    public static String getLevelToeic(String levelToeic){
        String level;
        switch (levelToeic){
            case "400":
                level = LevelToeic.level400.getValue();
                break;
            case "550":
                level = LevelToeic.level550.getValue();
                break;
            case "700":
                level = LevelToeic.level700.getValue();
                break;
            case "850":
                level = LevelToeic.level850.getValue();
                break;
            default:
                level = "";
        }
        return level;
    }
}
