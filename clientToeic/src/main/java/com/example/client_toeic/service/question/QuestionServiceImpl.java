package com.example.client_toeic.service.question;

import com.example.client_toeic.dto.*;
import com.example.client_toeic.entity.Answer;
import com.example.client_toeic.entity.Question;
import com.example.client_toeic.entity.Resource;
import com.example.client_toeic.enums.TypeResource;
import com.example.client_toeic.enums.TypeSkill;
import com.example.client_toeic.repository.AnswerRepository;
import com.example.client_toeic.repository.QuestionRepository;
import com.example.client_toeic.repository.ResourceRepository;
import com.example.client_toeic.service.resource.ResourceService;
import com.example.client_toeic.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class QuestionServiceImpl implements QuestionService{

  
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ResourceRepository resourceRepository;
    
    
    @Override
    public List<Question> finAll() {
        List<Question> questions = new ArrayList<>();
        List<Question> questionsAll = questionRepository.findAll();
        for (Question question: questionsAll) {
            if(question.getTypeSkill().equals(TypeSkill.SHORT_TALK.getValue()) ||
               question.getTypeSkill().equals(TypeSkill.TEXT_COMPLETION.getValue()) ||
               question.getTypeSkill().equals(TypeSkill.DOUBLE_PASSAGE.getValue()) ||
               question.getTypeSkill().equals(TypeSkill.SINGLE_PASSAGE.getValue())){
                if(question.getParentQuestion() == null){
                    questions.add(question);
                }
            }else {
                questions.add(question);
            }
        }
        // Sử dụng Collections.sort với Comparator tùy chỉnh
        Collections.sort(questions, (q1, q2) -> q2.getCreatedDate().compareTo(q1.getCreatedDate()));
        return questions;
    }

    @Override
    public QuestionPhoto findQuestionPhoto(Integer id) {
        Question question = questionRepository.findById(id).orElseThrow(()->new RuntimeException("Không tìm thấy câu hỏi"));
        QuestionPhoto questionPhoto = new QuestionPhoto();
        questionPhoto.setId(question.getId());
        questionPhoto.setText(question.getName());
        if(!CommonUtils.isEmptyOrNull(question.getDescription())){
            questionPhoto.setDescription(question.getDescription());
        }
        questionPhoto.setCorrectAnswerIndex(question.getIndexCorrect());
        List<String> answers = answerRepository.findAllByQuestionId(id).stream().map(Answer::getContent).collect(Collectors.toList());
        questionPhoto.setAnswers(answers);

        List<Resource> resources = resourceRepository.findAllByQuestionId(id);
        FileBase64Dto image = new FileBase64Dto();
        FileBase64Dto audio = new FileBase64Dto();
        for (Resource resource: resources) {
            if(TypeResource.IMAGE.getValue().equals(resource.getTypeResource())){
                questionPhoto.setImage(resource.getImage() != null ? resource.getImage() : null);
            }else{
                questionPhoto.setAudioSrc(resource.getAudio() != null ? resource.getAudio() : null);
            }
        }
        return questionPhoto;
    }

    @Override
    public QuestionShortTalkDto findQuestionShortTalk(Integer id) {
        Question question = questionRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy câu hỏi phù hợp"));
        QuestionShortTalkDto questionShortTalkDto = new QuestionShortTalkDto();
        questionShortTalkDto.setId(question.getId().toString());
        questionShortTalkDto.setName(question.getName());
        questionShortTalkDto.setDescription(question.getDescription());
        List<Resource> resources = resourceRepository.findAllByQuestionId(id);
        FileBase64Dto image = new FileBase64Dto();
        FileBase64Dto audio = new FileBase64Dto();
        for (Resource resource: resources) {
            if(TypeResource.IMAGE.getValue().equals(resource.getTypeResource())){
                if(CommonUtils.isEmptyOrNull(resource.getFileName())){
                    image.setLink(resource.getImage() != null ? resource.getImage() : null);
                }else{
                    image.setNameBase64(resource.getFileName() != null ? resource.getFileName() : null);
                }
            }else{
                if(CommonUtils.isEmptyOrNull(resource.getFileName())){
                    audio.setLink(resource.getAudio() != null ? resource.getAudio() : null);
                }else{
                    audio.setNameBase64(resource.getFileName() != null ? resource.getFileName() : null);
                }
            }
        }
        questionShortTalkDto.setImage(image);
        questionShortTalkDto.setAudio(audio);
        List<ChildrenQuestionDto> childrenQuestionDtos = new ArrayList<>();
        List<Question> children = questionRepository.findAllByParentQuestion(question.getId());
        for (Question questionChild: children) {
           ChildrenQuestionDto childrenQuestionDto = new ChildrenQuestionDto();
           childrenQuestionDto.setName(questionChild.getName());
           childrenQuestionDto.setAnswerCorrect(questionChild.getIndexCorrect());
           List<String> answers = answerRepository.findAllByQuestionIdContent(questionChild.getId());
           childrenQuestionDto.setAnswers(answers);
           childrenQuestionDtos.add(childrenQuestionDto);
        }
        questionShortTalkDto.setQuestions(childrenQuestionDtos);
        return questionShortTalkDto;
    }

    @Override
    public QuestionShortTalkDto detailQuestionShortTalk(Integer id) {
        Question question = questionRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy câu hỏi phù hợp"));
        QuestionShortTalkDto questionShortTalkDto = new QuestionShortTalkDto();
        questionShortTalkDto.setId(question.getId().toString());
        questionShortTalkDto.setName(question.getName());
        questionShortTalkDto.setDescription(question.getDescription());
        List<Resource> resources = resourceRepository.findAllByQuestionId(id);
        FileBase64Dto image = new FileBase64Dto();
        FileBase64Dto audio = new FileBase64Dto();
        for (Resource resource: resources) {
            if(TypeResource.IMAGE.getValue().equals(resource.getTypeResource())){
                    image.setLink(resource.getImage() != null ? resource.getImage() : null);
                    image.setNameBase64(resource.getFileName() != null ? resource.getFileName() : null);
            }else{
                    audio.setLink(resource.getAudio() != null ? resource.getAudio() : null);
                    audio.setNameBase64(resource.getFileName() != null ? resource.getFileName() : null);
            }
        }
        questionShortTalkDto.setImage(image);
        questionShortTalkDto.setAudio(audio);
        List<ChildrenQuestionDto> childrenQuestionDtos = new ArrayList<>();
        List<Question> children = questionRepository.findAllByParentQuestion(question.getId());
        for (Question questionChild: children) {
            ChildrenQuestionDto childrenQuestionDto = new ChildrenQuestionDto();
            childrenQuestionDto.setName(questionChild.getName());
            childrenQuestionDto.setAnswerCorrect(questionChild.getIndexCorrect());
            List<String> answers = answerRepository.findAllByQuestionIdContent(questionChild.getId());
            childrenQuestionDto.setAnswers(answers);
            childrenQuestionDtos.add(childrenQuestionDto);
        }
        questionShortTalkDto.setQuestions(childrenQuestionDtos);
        return questionShortTalkDto;
    }

    @Override
    public QuestionPhotoDto detailQuestionPhoto(Integer id) {
        Question question = questionRepository.findById(id).orElseThrow(()->new RuntimeException("Không tìm thấy câu hỏi"));
        QuestionPhotoDto questionPhotoDto = new QuestionPhotoDto();
        questionPhotoDto.setId(question.getId().toString());
        questionPhotoDto.setName(question.getName());
        if(!CommonUtils.isEmptyOrNull(question.getDescription())){
            questionPhotoDto.setDescription(question.getDescription());
        }
        questionPhotoDto.setAnswerCorrect(question.getIndexCorrect());
        List<String> answers = answerRepository.findAllByQuestionId(id).stream().map(Answer::getContent).collect(Collectors.toList());
        questionPhotoDto.setAnswers(answers);

        List<Resource> resources = resourceRepository.findAllByQuestionId(id);
        FileBase64Dto image = new FileBase64Dto();
        FileBase64Dto audio = new FileBase64Dto();
        for (Resource resource: resources) {
            if(TypeResource.IMAGE.getValue().equals(resource.getTypeResource())){
                image.setNameBase64(resource.getFileName() != null ? resource.getFileName() : null);
                image.setLink(resource.getImage() != null ? resource.getImage() : null);
            }else{
                   audio.setLink(resource.getAudio() != null ? resource.getAudio() : null);
                    audio.setNameBase64(resource.getFileName() != null ? resource.getFileName() : null);
            }
        }
        questionPhotoDto.setImage(image);
        questionPhotoDto.setAudio(audio);
        return questionPhotoDto;
    }


    /**
     * lấy ra những câu hỏi tương ứng với level và kỹ năng
     * @param questionExerciseDto
     * @return
     */
    @Override
    public List<Question> responseQuestionExercise(QuestionExerciseDto questionExerciseDto, Integer typeQuestion) {
        String levelToeic = questionExerciseDto.getLevel();
        String skillToeic = questionExerciseDto.getSkill();
        List<Question> questions = questionRepository.findAllByLevelAndSkill(levelToeic,skillToeic,typeQuestion);

        return questions;
    }


}
