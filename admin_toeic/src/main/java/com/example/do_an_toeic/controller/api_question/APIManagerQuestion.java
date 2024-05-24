package com.example.do_an_toeic.controller.api_question;

import com.example.do_an_toeic.dto.*;
import com.example.do_an_toeic.dto.exam.ExamSkillQuestionDto;
import com.example.do_an_toeic.entity.Question;
import com.example.do_an_toeic.enums.TypeExercise;
import com.example.do_an_toeic.enums.TypeQuestion;
import com.example.do_an_toeic.service.question.QuestionService;
import com.example.do_an_toeic.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequestMapping("/api/question")
@RestController
public class APIManagerQuestion {

    @Autowired
    private QuestionService questionService;

    /**
     * Thêm mới
     * @param questionPhotoDto
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/save/photo")
    public ResponseEntity<String> saveQuestionPhoto(@RequestBody QuestionPhotoDto questionPhotoDto) throws IOException {
        validatePhoto(questionPhotoDto);
        Question question = questionService.saveQuestionPhoto(questionPhotoDto);
        if(question == null){
            throw new RuntimeException("Lưu câu hỏi thất bại");
        }
        return ResponseEntity.ok("Câu hỏi đã được lưu thành công");
    }

    /**
     * update
     * @param questionPhotoDto
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/update/photo")
    public ResponseEntity<String> updateQuestionPhoto(@RequestBody QuestionPhotoDto questionPhotoDto) throws IOException {
        validatePhoto(questionPhotoDto);
        Question question = questionService.updateQuestionPhoto(questionPhotoDto);
        if(question == null){
            throw new RuntimeException("Lưu câu hỏi thất bại");
        }
        return ResponseEntity.ok("Câu hỏi đã được lưu thành công");
    }

    @GetMapping("/photo/{id}")
    public ResponseEntity<QuestionPhotoDto> getQuestionPhoto(@PathVariable("id") Integer id){
        return ResponseEntity.ok(questionService.findQuestionPhoto(id));
    }
//    end


    @PostMapping(value = "/save/question-response")
    public ResponseEntity<String> saveQuestionQuestionResponse(@RequestBody QuestionPhotoDto questionPhotoDto) throws IOException {
        validateQuestionResponse(questionPhotoDto);
        Question question = questionService.saveQuestionQuestionResponse(questionPhotoDto);
        if(question == null){
            throw new RuntimeException("Lưu câu hỏi thất bại");
        }
        return ResponseEntity.ok("Câu hỏi đã được lưu thành công");
    }

    /**
     * update lưu question response and short converstion when update
     * @param questionPhotoDto
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/update/question-response")
    public ResponseEntity<String> updateQuestionResponse(@RequestBody QuestionPhotoDto questionPhotoDto) throws IOException {
        validateQuestionResponse(questionPhotoDto);
        Question question = questionService.updateQuestionResponse(questionPhotoDto);
        if(question == null){
            throw new RuntimeException("Lưu câu hỏi thất bại");
        }
        return ResponseEntity.ok("Câu hỏi đã được lưu thành công");
    }

    // end

    @PostMapping(value = "/save/short-conversation")
    public ResponseEntity<String> saveQuestionShortConversation(@RequestBody QuestionPhotoDto questionPhotoDto) throws IOException {
        validateQuestionResponse(questionPhotoDto);
        Question question = questionService.saveQuestionShortConversation(questionPhotoDto);
        if(question == null){
            throw new RuntimeException("Lưu câu hỏi thất bại");
        }
        return ResponseEntity.ok("Câu hỏi đã được lưu thành công");
    }

    //end

    @PostMapping(value = "/save/short-talk")
    public ResponseEntity<String> saveQuestionShortTalk(@RequestBody QuestionShortTalkDto questionShortTalkDto) throws IOException {
         validateShortTalk(questionShortTalkDto);
        Question question = questionService.saveQuestionShortTalk(questionShortTalkDto, TypeExercise.SHORT_TALK.getValue());
        if(question == null){
            throw new RuntimeException("Lưu câu hỏi thất bại");
        }
        return ResponseEntity.ok("Câu hỏi đã được lưu thành công");
    }

    @GetMapping("/short-talk/{id}")
    public ResponseEntity<QuestionShortTalkDto> getQuestionShortTalk(@PathVariable("id") Integer id){
        return ResponseEntity.ok(questionService.findQuestionShortTalk(id));
    }

    @PostMapping(value = "/update/short-talk")
    public ResponseEntity<String> updateQuestionShortTalk(@RequestBody QuestionShortTalkDto questionShortTalkDto) throws IOException {
        validateShortTalk(questionShortTalkDto);
        Question question = questionService.updateShortTalk(questionShortTalkDto);
        if(question == null){
            throw new RuntimeException("Lưu câu hỏi thất bại");
        }
        return ResponseEntity.ok("Câu hỏi đã được lưu thành công");
    }

    // end
    @PostMapping(value = "/save/incomplete")
    public ResponseEntity<String> saveQuestionIncomplete(@RequestBody QuestionPhotoDto questionPhotoDto) throws IOException {
        validateTextIncomplete(questionPhotoDto);
        Question question = questionService.saveQuestionIncomplete(questionPhotoDto);
        if(question == null){
            throw new RuntimeException("Lưu câu hỏi thất bại");
        }
        return ResponseEntity.ok("Câu hỏi đã được lưu thành công");
    }

    /**
     * update lưu question response and short converstion when update
     * @param questionPhotoDto
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/update/incomplete")
    public ResponseEntity<String> updateIncomplete(@RequestBody QuestionPhotoDto questionPhotoDto) throws IOException {
        validateTextIncomplete(questionPhotoDto);
        Question question = questionService.updateIncomplete(questionPhotoDto);
        if(question == null){
            throw new RuntimeException("Lưu câu hỏi thất bại");
        }
        return ResponseEntity.ok("Câu hỏi đã được lưu thành công");
    }

//end

    @PostMapping(value = "/save/text-completion")
    public ResponseEntity<String> saveQuestionIncomplete(@RequestBody QuestionShortTalkDto questionPhotoDto) throws IOException {
        validateTextCompletion(questionPhotoDto);
        Question question = questionService.saveQuestionTextCompletion(questionPhotoDto, TypeExercise.TEXT_COMPLETION.getValue());
        if(question == null){
            throw new RuntimeException("Lưu câu hỏi thất bại");
        }
        return ResponseEntity.ok("Câu hỏi đã được lưu thành công");
    }

    @PostMapping(value = "/update/text-completion")
    public ResponseEntity<String> updateQuestionTextCompletion(@RequestBody QuestionShortTalkDto questionShortTalkDto) throws IOException {
        validateTextCompletion(questionShortTalkDto);
        Question question = questionService.updateTextCompletion(questionShortTalkDto);
        if(question == null){
            throw new RuntimeException("Lưu câu hỏi thất bại");
        }
        return ResponseEntity.ok("Câu hỏi đã được lưu thành công");
    }

    // end

    @PostMapping(value = "/save/single-passage")
    public ResponseEntity<String> saveQuestionSinglePassage(@RequestBody QuestionShortTalkDto questionPhotoDto) throws IOException {
        validateTextCompletion(questionPhotoDto);
        Question question = questionService.saveQuestionTextCompletion(questionPhotoDto, TypeExercise.SINGLE_PASSAGE.getValue());
        if(question == null){
            throw new RuntimeException("Lưu câu hỏi thất bại");
        }
        return ResponseEntity.ok("Câu hỏi đã được lưu thành công");
    }

    // end

    @PostMapping(value = "/save/double-passage")
    public ResponseEntity<String> saveQuestionDoublePassage(@RequestBody QuestionShortTalkDto questionPhotoDto) throws IOException {
        validateTextCompletion(questionPhotoDto);
        Question question = questionService.saveQuestionTextCompletion(questionPhotoDto, TypeExercise.DOUBLE_PASSAGE.getValue());
        if(question == null){
            throw new RuntimeException("Lưu câu hỏi thất bại");
        }
        return ResponseEntity.ok("Câu hỏi đã được lưu thành công");
    }
    public void validatePhoto(QuestionPhotoDto questionPhotoDto){
        if(CommonUtils.isEmptyOrNull(questionPhotoDto.getName())){
            throw new RuntimeException("Tên câu hỏi không được để trống");
        }
        if(CommonUtils.isEmptyOrNull(questionPhotoDto.getImage().getNameBase64()) &&
                CommonUtils.isEmptyOrNull(questionPhotoDto.getImage().getLink())){
            throw new RuntimeException("Vui lòng nhập link ảnh hoặc chọn file");
        }
        if(CommonUtils.isEmptyOrNull(questionPhotoDto.getAudio().getNameBase64()) &&
                CommonUtils.isEmptyOrNull(questionPhotoDto.getAudio().getLink())){
            throw new RuntimeException("Vui lòng nhập link audio hoặc chọn file");
        }
        if(CommonUtils.isListEmptyOrNull(questionPhotoDto.getAnswers())){
            throw new RuntimeException("Vui lòng nhập đáp án");
        }
        if(questionPhotoDto.getAnswerCorrect() == null)
        {
            throw new RuntimeException("Vui lòng nhập vị trí đáp án đúng");
        }
        int lengthAnswer = questionPhotoDto.getAnswers().size();
        if(questionPhotoDto.getAnswerCorrect() < 1 || questionPhotoDto.getAnswerCorrect() > lengthAnswer){
            throw new RuntimeException("Vui lòng nhập đúng vị trí đáp án đúng");
        }

    }

    public void validateQuestionResponse(QuestionPhotoDto questionPhotoDto){
        if(CommonUtils.isEmptyOrNull(questionPhotoDto.getName())){
            throw new RuntimeException("Tên câu hỏi không được để trống");
        }
//        if(CommonUtils.isEmptyOrNull(questionPhotoDto.getImage().getNameBase64()) &&
//                CommonUtils.isEmptyOrNull(questionPhotoDto.getImage().getLink())){
//            throw new RuntimeException("Vui lòng nhập link ảnh hoặc chọn file");
//        }
        if(CommonUtils.isEmptyOrNull(questionPhotoDto.getAudio().getNameBase64()) &&
                CommonUtils.isEmptyOrNull(questionPhotoDto.getAudio().getLink())){
            throw new RuntimeException("Vui lòng nhập link audio hoặc chọn file");
        }
        if(CommonUtils.isListEmptyOrNull(questionPhotoDto.getAnswers())){
            throw new RuntimeException("Vui lòng nhập đáp án");
        }
        if(questionPhotoDto.getAnswerCorrect() == null)
        {
            throw new RuntimeException("Vui lòng nhập vị trí đáp án đúng");
        }
        int lengthAnswer = questionPhotoDto.getAnswers().size();
        if(questionPhotoDto.getAnswerCorrect() < 1 || questionPhotoDto.getAnswerCorrect() > lengthAnswer){
            throw new RuntimeException("Vui lòng nhập đúng vị trí đáp án đúng");
        }

    }

    public void validateTextIncomplete(QuestionPhotoDto questionPhotoDto){
        if(CommonUtils.isEmptyOrNull(questionPhotoDto.getName())){
            throw new RuntimeException("Tên câu hỏi không được để trống");
        }

        if(CommonUtils.isListEmptyOrNull(questionPhotoDto.getAnswers())){
            throw new RuntimeException("Vui lòng nhập đáp án");
        }
        if(questionPhotoDto.getAnswerCorrect() == null)
        {
            throw new RuntimeException("Vui lòng nhập vị trí đáp án đúng");
        }
        int lengthAnswer = questionPhotoDto.getAnswers().size();
        if(questionPhotoDto.getAnswerCorrect() < 1 || questionPhotoDto.getAnswerCorrect() > lengthAnswer){
            throw new RuntimeException("Vui lòng nhập đúng vị trí đáp án đúng");
        }
    }

    public void validateShortTalk(QuestionShortTalkDto questionShortTalkDto){
        if(CommonUtils.isEmptyOrNull(questionShortTalkDto.getName())){
            throw new RuntimeException("Tên câu hỏi không được để trống");
        }
        if (CommonUtils.isEmptyOrNull(questionShortTalkDto.getAudio().getLink()) && CommonUtils.isEmptyOrNull(questionShortTalkDto.getAudio().getNameBase64())) {
            throw new RuntimeException("Vui lòng nhập hoặc chọn file audio");
        }
        if(CommonUtils.isListEmptyOrNull(questionShortTalkDto.getQuestions())){
            throw new RuntimeException("Vui lòng nhập ít nhất 1 câu hỏi");
        }

        for (ChildrenQuestionDto childrenQuestionDto: questionShortTalkDto.getQuestions()) {
            if(CommonUtils.isEmptyOrNull(childrenQuestionDto.getName())){
                throw new RuntimeException("Vui lòng nhập tên câu hỏi");
            }
            if(childrenQuestionDto.getAnswerCorrect() == null){
                throw new RuntimeException("Vui lòng nhập vị trí đáp án đúng");
            }
            int lengthAnswer = childrenQuestionDto.getAnswers().size();
            if(childrenQuestionDto.getAnswerCorrect() < 1 || childrenQuestionDto.getAnswerCorrect() > lengthAnswer){
                throw new RuntimeException("Vui lòng nhập đúng vị trí đáp án đúng");
            }
        }

    }

    public void validateTextCompletion(QuestionShortTalkDto questionShortTalkDto){
        if(CommonUtils.isEmptyOrNull(questionShortTalkDto.getName())){
            throw new RuntimeException("Tên câu hỏi không được để trống");
        }
        if(CommonUtils.isListEmptyOrNull(questionShortTalkDto.getQuestions())){
            throw new RuntimeException("Vui lòng nhập ít nhất 1 câu hỏi");
        }

        for (ChildrenQuestionDto childrenQuestionDto: questionShortTalkDto.getQuestions()) {
            if(CommonUtils.isEmptyOrNull(childrenQuestionDto.getName())){
                throw new RuntimeException("Vui lòng nhập tên câu hỏi");
            }
            if(childrenQuestionDto.getAnswerCorrect() == null){
                throw new RuntimeException("Vui lòng nhập vị trí đáp án đúng");
            }
            int lengthAnswer = childrenQuestionDto.getAnswers().size();
            if(childrenQuestionDto.getAnswerCorrect() < 1 || childrenQuestionDto.getAnswerCorrect() > lengthAnswer){
                throw new RuntimeException("Vui lòng nhập đúng vị trí đáp án đúng");
            }
        }

    }

    /**
     * trả ra những câu hỏi tương ứng với skill và level đối với câu hỏi ôn tập
     * @param questionExerciseDto
     * @return
     */
    @PostMapping("/question-exercise")
    public ResponseEntity<List<Question>> responseQuestion(@RequestBody QuestionExerciseDto questionExerciseDto){

        return ResponseEntity.ok(questionService.responseQuestionExercise(questionExerciseDto, TypeQuestion.TYPE_EXERCISE.getValue()));
    }

    @PostMapping("/question-exam")
    public ResponseEntity<List<Question>> responseQuestionExam(@RequestBody ExamSkillQuestionDto examSkillQuestionDto){
        return ResponseEntity.ok(questionService.responseQuestionExam(examSkillQuestionDto, TypeQuestion.TYPE_EXAM.getValue()));
    }

    @PostMapping("/question-exam-update")
    public ResponseEntity<List<Question>> responseQuestionExamUpdate(@RequestBody ExamSkillQuestionDto examSkillQuestionDto){
        return ResponseEntity.ok(questionService.responseQuestionExamUpdate(examSkillQuestionDto, TypeQuestion.TYPE_EXAM.getValue()));
    }


}
