package com.example.client_toeic.utils;

public class CommonUtils {
    public static boolean isEmptyOrNull(String str){
        if (str == null || str.trim().isEmpty()){
            return true;
        }
        return false;
    }
}
