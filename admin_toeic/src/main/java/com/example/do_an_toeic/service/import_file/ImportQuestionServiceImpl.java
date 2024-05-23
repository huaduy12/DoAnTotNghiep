package com.example.do_an_toeic.service.import_file;

import com.example.do_an_toeic.dto.ImportFile.ChildrenShortTalkDto;
import com.example.do_an_toeic.dto.ImportFile.PhotoDto;
import com.example.do_an_toeic.dto.ImportFile.ShortTalkDto;
import com.example.do_an_toeic.dto.QuestionPhotoDto;
import com.example.do_an_toeic.entity.Answer;
import com.example.do_an_toeic.entity.Question;
import com.example.do_an_toeic.entity.Resource;
import com.example.do_an_toeic.enums.TypeExercise;
import com.example.do_an_toeic.enums.TypeResource;
import com.example.do_an_toeic.repository.AnswerRepository;
import com.example.do_an_toeic.repository.QuestionRepository;
import com.example.do_an_toeic.repository.ResourceRepository;
import com.example.do_an_toeic.utils.CommonUtils;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional(rollbackFor = {Exception.class})
public class ImportQuestionServiceImpl implements ImportQuestionService{

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Override
    public Integer importFileQuestion(MultipartFile multipartFile) throws IOException {
        Integer result = -1;
        Workbook workbook = WorkbookFactory.create(multipartFile.getInputStream());

        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet = workbook.getSheetAt(i);
            switch (i) {
                case 0:
                    // đối với câu hỏi thuộc dạng photo ở  sheet 1
                    try {
                        for (Row row : sheet) {
                            if (row.getRowNum() == 0 ||isRowEmpty(row)) {
                                continue; // Bỏ qua dòng tiêu đề
                            }
                            PhotoDto photoDto = new PhotoDto();
                            photoDto.setName(getCellValue(row.getCell(2)));
                            photoDto.setLinkAudio(getCellValue(row.getCell(0)));
                            photoDto.setLinkImage(getCellValue(row.getCell(1)));
                            photoDto.setAnswer(getCellValue(row.getCell(3)));
                            photoDto.setIndexCorrect((int) Double.parseDouble(getCellValue(row.getCell(4))));
                            photoDto.setTypeQuestion((int) Double.parseDouble(getCellValue(row.getCell(5))));
                            photoDto.setLevel(getCellValue(row.getCell(6)));
                            saveQuestionFilePhoto(photoDto);

                        }
                    } catch (Exception e) {
                        result =1;
                        throw new RuntimeException("Lỗi import");
                    }
                    break;
                case 1:
                    // question response
                    try {
                        for (Row row : sheet) {
                            if (row.getRowNum() == 0) {
                                continue; // Bỏ qua dòng tiêu đề
                            }
                            if(isRowEmpty(row)){
                                break;
                            }
                            PhotoDto photoDto = new PhotoDto();
                            photoDto.setName(getCellValue(row.getCell(1)));
                            photoDto.setAnswer(getCellValue(row.getCell(2)));
                            photoDto.setIndexCorrect((int) Double.parseDouble(getCellValue(row.getCell(3))));
                            photoDto.setTypeQuestion((int) Double.parseDouble(getCellValue(row.getCell(4))));
                            photoDto.setLevel(getCellValue(row.getCell(5)));
                            photoDto.setLinkAudio(getCellValue(row.getCell(0)));

                            saveQuestionFileQuestionReponse(photoDto);

                        }
                    } catch (Exception e) {
                        result =2;
                        throw new RuntimeException("Lỗi import");
                    }
                    break;
                case 2:
                    // short conversation
                    try {
                        for (Row row : sheet) {
                            if (row.getRowNum() == 0) {
                                continue; // Bỏ qua dòng tiêu đề
                            }
                            if(isRowEmpty(row)){
                                break;
                            }
                            PhotoDto photoDto = new PhotoDto();
                            photoDto.setName(getCellValue(row.getCell(1)));
                            photoDto.setAnswer(getCellValue(row.getCell(2)));
                            photoDto.setDescription(getCellValue(row.getCell(3)));
                            photoDto.setIndexCorrect((int) Double.parseDouble(getCellValue(row.getCell(4))));
                            photoDto.setTypeQuestion((int) Double.parseDouble(getCellValue(row.getCell(5))));
                            photoDto.setLevel(getCellValue(row.getCell(6)));
                            photoDto.setLinkAudio(getCellValue(row.getCell(0)));

                            saveQuestionFileSortConversation(photoDto);

                        }
                    } catch (Exception e) {
                        result =3;
                        throw new RuntimeException("Lỗi import");
                    }
                    break;
                case 3:
                    // short talk
                    try {
                        List<ShortTalkDto> shortTalkDtos = new ArrayList<>();
                        Iterator<Row> rowIterator = sheet.iterator();
                        ShortTalkDto currentShortTalk = null;
                        boolean isFirstRow = true;
                        while (rowIterator.hasNext()) {

                            Row row = rowIterator.next();
                            if (isFirstRow) {
                                isFirstRow = false; // Bỏ qua dòng tiêu đề
                                continue;
                            }
                            if(isRowEmpty(row)){
                                break;
                            }

                            Cell firstCell = row.getCell(0);

                            if (firstCell != null && firstCell.getCellType() == CellType.STRING && !firstCell.getStringCellValue().isEmpty()) {
                                // Đây là câu hỏi cha
                                if (currentShortTalk != null) {
                                    shortTalkDtos.add(currentShortTalk); // Thêm câu hỏi cha hiện tại vào danh sách
                                }

                                currentShortTalk = new ShortTalkDto();
                                currentShortTalk.setAudio(getCellValue(row.getCell(1)));
                                currentShortTalk.setName(getCellValue(row.getCell(2)));
                                currentShortTalk.setDescription(getCellValue(row.getCell(4)));

                                currentShortTalk.setTypeQuestion((int) Double.parseDouble(getCellValue(row.getCell(6))));
                                currentShortTalk.setLevel(getCellValue(row.getCell(7)));
                                currentShortTalk.setQuestions(new ArrayList<>());

                            } else if (currentShortTalk != null) {
                                // Đây là câu hỏi con
                                ChildrenShortTalkDto childQuestion = new ChildrenShortTalkDto();
                                childQuestion.setName(getCellValue(row.getCell(2)));
                                childQuestion.setAnswer(getCellValue(row.getCell(3)));
                                childQuestion.setAnswerCorrect((int) Double.parseDouble(getCellValue(row.getCell(5))));
                                currentShortTalk.getQuestions().add(childQuestion);
                            }
                        }
                        if (currentShortTalk != null) {
                            shortTalkDtos.add(currentShortTalk); // Thêm câu hỏi cha cuối cùng vào danh sách
                        }
                        saveQuestionFileShortTalk(shortTalkDtos, TypeExercise.SHORT_TALK.getValue());
                    } catch (Exception e) {
                        result =4;
                        throw new RuntimeException("Lỗi import");
                    }
                    break;
                case 4:
                    // incomplete sentence
                    try {
                        for (Row row : sheet) {
                            if (row.getRowNum() == 0) {
                                continue; // Bỏ qua dòng tiêu đề
                            }
                            if(isRowEmpty(row)){
                                break;
                            }
                            PhotoDto photoDto = new PhotoDto();
                            photoDto.setName(getCellValue(row.getCell(0)));
                            photoDto.setAnswer(getCellValue(row.getCell(1)));
                            photoDto.setDescription(getCellValue(row.getCell(2)));
                            photoDto.setIndexCorrect((int) Double.parseDouble(getCellValue(row.getCell(3))));
                            photoDto.setTypeQuestion((int) Double.parseDouble(getCellValue(row.getCell(4))));
                            photoDto.setLevel(getCellValue(row.getCell(5)));
                            saveQuestionFileTextCompletion(photoDto);
                        }
                    } catch (Exception e) {
                        result =5;
                        throw new RuntimeException("Lỗi import");
                    }
                    break;
                case 5:
                    // text completion
                    try {
                        List<ShortTalkDto> textCompletionDtos = new ArrayList<>();
                        Iterator<Row> rowIterator = sheet.iterator();
                        ShortTalkDto currentTextCompletion = null;
                        boolean isFirstRow = true;
                        while (rowIterator.hasNext()) {

                            Row row = rowIterator.next();
                            if (isFirstRow) {
                                isFirstRow = false; // Bỏ qua dòng tiêu đề
                                continue;
                            }
                            if(isRowEmpty(row)){
                                break;
                            }
                            Cell firstCell = row.getCell(0);

                            if (firstCell != null && firstCell.getCellType() == CellType.STRING && !firstCell.getStringCellValue().isEmpty()) {
                                // Đây là câu hỏi cha
                                if (currentTextCompletion != null) {
                                    textCompletionDtos.add(currentTextCompletion); // Thêm câu hỏi cha hiện tại vào danh sách
                                }
                                currentTextCompletion = new ShortTalkDto();
                                currentTextCompletion.setName(getCellValue(row.getCell(1)));
                                currentTextCompletion.setDescription(getCellValue(row.getCell(3)));
                                currentTextCompletion.setTypeQuestion((int) Double.parseDouble(getCellValue(row.getCell(5))));
                                currentTextCompletion.setLevel(getCellValue(row.getCell(6)));
                                currentTextCompletion.setQuestions(new ArrayList<>());

                            } else if (currentTextCompletion != null) {
                                // Đây là câu hỏi con
                                ChildrenShortTalkDto childQuestion = new ChildrenShortTalkDto();
                                childQuestion.setName(getCellValue(row.getCell(1)));
                                childQuestion.setAnswer(getCellValue(row.getCell(2)));
                                childQuestion.setAnswerCorrect((int) Double.parseDouble(getCellValue(row.getCell(4))));
                                currentTextCompletion.getQuestions().add(childQuestion);
                            }
                        }
                        if (currentTextCompletion != null) {
                            textCompletionDtos.add(currentTextCompletion); // Thêm câu hỏi cha cuối cùng vào danh sách
                        }
                        saveQuestionFileShortTalk(textCompletionDtos, TypeExercise.TEXT_COMPLETION.getValue());
                    } catch (Exception e) {
                        result =6;
                        throw new RuntimeException("Lỗi import");
                    }
                    break;
                case 6:
                    try {
                        List<ShortTalkDto> singlePassages = new ArrayList<>();
                        Iterator<Row> rowIterator = sheet.iterator();
                        ShortTalkDto currentSinglePassage = null;
                        boolean isFirstRow = true;
                        while (rowIterator.hasNext()) {

                            Row row = rowIterator.next();
                            if (isFirstRow) {
                                isFirstRow = false; // Bỏ qua dòng tiêu đề
                                continue;
                            }
                            if(isRowEmpty(row)){
                                break;
                            }
                            Cell firstCell = row.getCell(0);

                            if (firstCell != null && firstCell.getCellType() == CellType.STRING && !firstCell.getStringCellValue().isEmpty()) {
                                // Đây là câu hỏi cha
                                if (currentSinglePassage != null) {
                                    singlePassages.add(currentSinglePassage); // Thêm câu hỏi cha hiện tại vào danh sách
                                }
                                currentSinglePassage = new ShortTalkDto();
                                currentSinglePassage.setName(getCellValue(row.getCell(1)));
                                currentSinglePassage.setDescription(getCellValue(row.getCell(3)));
                                currentSinglePassage.setTypeQuestion((int) Double.parseDouble(getCellValue(row.getCell(5))));
                                currentSinglePassage.setLevel(getCellValue(row.getCell(6)));
                                currentSinglePassage.setQuestions(new ArrayList<>());

                            } else if (currentSinglePassage != null) {
                                // Đây là câu hỏi con
                                ChildrenShortTalkDto childQuestion = new ChildrenShortTalkDto();
                                childQuestion.setName(getCellValue(row.getCell(1)));
                                childQuestion.setAnswer(getCellValue(row.getCell(2)));
                                childQuestion.setAnswerCorrect((int) Double.parseDouble(getCellValue(row.getCell(4))));
                                currentSinglePassage.getQuestions().add(childQuestion);
                            }
                        }
                        if (currentSinglePassage != null) {
                            singlePassages.add(currentSinglePassage); // Thêm câu hỏi cha cuối cùng vào danh sách
                        }
                        saveQuestionFileShortTalk(singlePassages, TypeExercise.SINGLE_PASSAGE.getValue());
                    } catch (Exception e) {
                        result =7;
                        throw new RuntimeException("Lỗi import");
                    }
                    break;
                case 7:
                    try {
                        List<ShortTalkDto> doublePassages = new ArrayList<>();
                        Iterator<Row> rowIterator = sheet.iterator();
                        ShortTalkDto currentDoublePassage = null;
                        boolean isFirstRow = true;
                        while (rowIterator.hasNext() ) {

                            Row row = rowIterator.next();
                            if (isFirstRow) {
                                isFirstRow = false; // Bỏ qua dòng tiêu đề
                                continue;
                            }
                            if(isRowEmpty(row)){
                                break;
                            }
                            Cell firstCell = row.getCell(0);

                            if (firstCell != null && firstCell.getCellType() == CellType.STRING && !firstCell.getStringCellValue().isEmpty()) {
                                // Đây là câu hỏi cha
                                if (currentDoublePassage != null) {
                                    doublePassages.add(currentDoublePassage); // Thêm câu hỏi cha hiện tại vào danh sách
                                }
                                currentDoublePassage = new ShortTalkDto();
                                currentDoublePassage.setName(getCellValue(row.getCell(1)));
                                currentDoublePassage.setDescription(getCellValue(row.getCell(3)));
                                currentDoublePassage.setTypeQuestion((int) Double.parseDouble(getCellValue(row.getCell(5))));
                                currentDoublePassage.setLevel(getCellValue(row.getCell(6)));
                                currentDoublePassage.setQuestions(new ArrayList<>());

                            } else if (currentDoublePassage != null) {
                                // Đây là câu hỏi con
                                ChildrenShortTalkDto childQuestion = new ChildrenShortTalkDto();
                                childQuestion.setName(getCellValue(row.getCell(1)));
                                childQuestion.setAnswer(getCellValue(row.getCell(2)));
                                childQuestion.setAnswerCorrect((int) Double.parseDouble(getCellValue(row.getCell(4))));
                                currentDoublePassage.getQuestions().add(childQuestion);
                            }
                        }
                        if (currentDoublePassage != null) {
                            doublePassages.add(currentDoublePassage); // Thêm câu hỏi cha cuối cùng vào danh sách
                        }
                        saveQuestionFileShortTalk(doublePassages, TypeExercise.DOUBLE_PASSAGE.getValue());
                    } catch (Exception e) {
                        result =8;
                        throw new RuntimeException("Lỗi import");
                    }
                    break;

            }

        }
        workbook.close();
        return result;
    }

    private String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return cell.toString();
        }
    }



    
    public void saveQuestionFilePhoto(PhotoDto photoDto){
        Question question = new Question();
        question.setName(photoDto.getName());
        question.setIndexCorrect(photoDto.getIndexCorrect());
        question.setTypeQuestion(photoDto.getTypeQuestion());

        int dotIndex = photoDto.getLevel().indexOf(".");
        String withoutDecimal = photoDto.getLevel().substring(0, dotIndex);
        question.setLevel(String.valueOf(Integer.parseInt(withoutDecimal)));
        question.setTypeSkill(TypeExercise.PHOTO.getValue());

        Question questionSave = questionRepository.save(question);


        Resource resourceAudio = new Resource();
        resourceAudio.setAudio(photoDto.getLinkAudio());
        resourceAudio.setTypeResource(TypeResource.AUDIO.getValue());
        resourceAudio.setQuestionId(questionSave.getId());
        resourceRepository.save(resourceAudio);

        Resource resourcePhoto = new Resource();
        resourcePhoto.setImage(photoDto.getLinkImage());
        resourcePhoto.setTypeResource(TypeResource.IMAGE.getValue());
        resourcePhoto.setQuestionId(questionSave.getId());
        resourceRepository.save(resourcePhoto);

        saveAnswer(Arrays.asList(photoDto.getAnswer().split("\\*")), questionSave.getId());
    }


    
    public void saveQuestionFileQuestionReponse(PhotoDto photoDto){
        Question question = new Question();
        question.setName(photoDto.getName());
        question.setIndexCorrect(photoDto.getIndexCorrect());
        question.setTypeQuestion(photoDto.getTypeQuestion());
        int dotIndex = photoDto.getLevel().indexOf(".");
        String withoutDecimal = photoDto.getLevel().substring(0, dotIndex);
        question.setLevel(String.valueOf(Integer.parseInt(withoutDecimal)));
        question.setTypeSkill(TypeExercise.QUESTION_RESPONSE.getValue());

        Question questionSave = questionRepository.save(question);

        Resource resourceAudio = new Resource();
        resourceAudio.setAudio(photoDto.getLinkAudio());
        resourceAudio.setTypeResource(TypeResource.AUDIO.getValue());
        resourceAudio.setQuestionId(questionSave.getId());
        resourceRepository.save(resourceAudio);

        saveAnswer(Arrays.asList(photoDto.getAnswer().split("\\*")), questionSave.getId());
    }

    
    public void saveQuestionFileSortConversation(PhotoDto photoDto){
        Question question = new Question();
        question.setName(photoDto.getName());
        question.setDescription(photoDto.getDescription());
        question.setIndexCorrect(photoDto.getIndexCorrect());
        question.setTypeQuestion(photoDto.getTypeQuestion());
        int dotIndex = photoDto.getLevel().indexOf(".");
        String withoutDecimal = photoDto.getLevel().substring(0, dotIndex);
        question.setLevel(String.valueOf(Integer.parseInt(withoutDecimal)));
        question.setTypeSkill(TypeExercise.SHORT_CONVERSATION.getValue());

        Question questionSave = questionRepository.save(question);

        Resource resourceAudio = new Resource();
        resourceAudio.setAudio(photoDto.getLinkAudio());
        resourceAudio.setTypeResource(TypeResource.AUDIO.getValue());
        resourceAudio.setQuestionId(questionSave.getId());
        resourceRepository.save(resourceAudio);

        saveAnswer(Arrays.asList(photoDto.getAnswer().split("\\*")), questionSave.getId());
    }

    
    public void saveQuestionFileTextCompletion(PhotoDto photoDto){
        Question question = new Question();
        question.setName(photoDto.getName());
        question.setDescription(photoDto.getDescription());
        question.setIndexCorrect(photoDto.getIndexCorrect());
        question.setTypeQuestion(photoDto.getTypeQuestion());
        int dotIndex = photoDto.getLevel().indexOf(".");
        String withoutDecimal = photoDto.getLevel().substring(0, dotIndex);
        question.setLevel(String.valueOf(Integer.parseInt(withoutDecimal)));
        question.setTypeSkill(TypeExercise.INCOMPLETE_SENTENCE.getValue());

        Question questionSave = questionRepository.save(question);

        Resource resourceAudio = new Resource();
        resourceAudio.setAudio(photoDto.getLinkAudio());
        resourceAudio.setTypeResource(TypeResource.AUDIO.getValue());
        resourceAudio.setQuestionId(questionSave.getId());
        resourceRepository.save(resourceAudio);

        saveAnswer(Arrays.asList(photoDto.getAnswer().split("\\*")), questionSave.getId());
    }

    
    public void saveQuestionFileShortTalk(List<ShortTalkDto> shortTalkDtos, String typeSkill){
        for (ShortTalkDto shortTalkDto: shortTalkDtos) {
            Question questionParent = new Question();
            questionParent.setName(shortTalkDto.getName());
            questionParent.setDescription(shortTalkDto.getDescription());
            questionParent.setTypeQuestion(shortTalkDto.getTypeQuestion());

            int dotIndex = shortTalkDto.getLevel().indexOf(".");
            String withoutDecimal = shortTalkDto.getLevel().substring(0, dotIndex);
            questionParent.setLevel(String.valueOf(Integer.parseInt(withoutDecimal)));

            questionParent.setTypeSkill(typeSkill);
            Question questionParentSave = questionRepository.save(questionParent);

            if(!CommonUtils.isEmptyOrNull(shortTalkDto.getAudio())){
                Resource resourceAudio = new Resource();
                resourceAudio.setAudio(shortTalkDto.getAudio());
                resourceAudio.setTypeResource(TypeResource.AUDIO.getValue());
                resourceAudio.setQuestionId(questionParentSave.getId());
                resourceRepository.save(resourceAudio);
            }

            for (ChildrenShortTalkDto childrenShortTalkDto: shortTalkDto.getQuestions()) {
                Question question = new Question();
                question.setName(childrenShortTalkDto.getName());
                question.setLevel(questionParentSave.getLevel());
                question.setTypeQuestion(questionParentSave.getTypeQuestion());
                question.setTypeSkill(questionParentSave.getTypeSkill());
                question.setParentQuestion(questionParentSave.getId());
                question.setIndexCorrect(childrenShortTalkDto.getAnswerCorrect());
                Question questionSave = questionRepository.save(question);
                saveAnswer(Arrays.asList(childrenShortTalkDto.getAnswer().split("\\*")),questionSave.getId());
            }
        }
    }

    
    public void saveAnswer(List<String> answerDtos, Integer questionId){
        List<Answer> answers = new ArrayList<>();
        for (String content:answerDtos) {
            Answer answer = new Answer();
            answer.setContent(content);
            answer.setQuestionId(questionId);
            answers.add(answer);
        }
        answerRepository.saveAll(answers);
    }

    private boolean isRowEmpty(Row row) {
        for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }
}
