package com.example.do_an_toeic.service.import_file;


import com.example.do_an_toeic.dto.ImportFile.PhotoDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImportQuestionService {
    Integer importFileQuestion(MultipartFile multipartFile) throws IOException;
}
