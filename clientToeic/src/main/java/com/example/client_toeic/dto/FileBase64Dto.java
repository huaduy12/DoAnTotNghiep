package com.example.client_toeic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileBase64Dto {
    private String link;
    private String base64;
    private String nameBase64;
}
