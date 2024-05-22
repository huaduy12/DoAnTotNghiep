package com.example.do_an_toeic.utils;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Collection;

public class CommonUtils {
    public static boolean isEmptyOrNull(String str){
        if (str == null || str.trim().isEmpty()){
            return true;
        }
        return false;
    }
    public  static <T> boolean isListEmptyOrNull(Collection<T> t){
        if (t == null || t.isEmpty()){
            return true;
        }
        return false;
    }

    public static String getContentTypeFromBase64(String base64String) {
        if (base64String == null || !base64String.contains(";base64,")) {
            return null;
        }

        try {
            String[] parts = base64String.split(";");
            if (parts.length > 0) {
                String mimeTypePart = parts[0];
                if (mimeTypePart.startsWith("data:")) {
                    return mimeTypePart.substring(5);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getBase64DataFromBase64String(String base64String) {
        if (base64String == null || !base64String.startsWith("data:") || !base64String.contains(";base64,")) {
            return null;
        }

        try {
            String[] parts = base64String.split(";base64,");
            if (parts.length > 1) {
                return parts[1];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static MultipartFile convertBase64ToMultipartFile(String base64String, String fileName, String contentType) throws IOException {
        // Giải mã chuỗi Base64 thành byte[]
        byte[] fileContent = Base64.getDecoder().decode(base64String);
        // Tạo đối tượng MultipartFile từ byte[]
        return new MockMultipartFile(fileName, fileName, contentType, fileContent);
    }

}
