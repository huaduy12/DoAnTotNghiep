package com.example.do_an_toeic.service.question;

import com.example.do_an_toeic.dto.*;
import com.example.do_an_toeic.entity.*;
import com.example.do_an_toeic.enums.TypeExercise;
import com.example.do_an_toeic.enums.TypeQuestion;
import com.example.do_an_toeic.enums.TypeResource;
import com.example.do_an_toeic.repository.*;
import com.example.do_an_toeic.service.firebase.ImageUploadService;
import com.example.do_an_toeic.service.resource.ResourceService;
import com.example.do_an_toeic.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    private ImageUploadService imageUploadService;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private ExamQuestionRepository examQuestionRepository;

    @Autowired
    private ExerciseQuestionRepository exerciseQuestionRepository;

    @Override
    public Question saveQuestionPhoto(QuestionPhotoDto questionPhotoDto) throws IOException {
        // save question
        Question question = questionRepository.save(mapperQuestionPhotoDtoToQuestion(questionPhotoDto, TypeExercise.PHOTO.getValue()));

        // save answer

        saveAnswer(questionPhotoDto.getAnswers(),question.getId());
        // save image
        saveFile(questionPhotoDto.getImage(),question.getId(),TypeResource.IMAGE.getValue());
        //save audio
        saveFile(questionPhotoDto.getAudio(),question.getId(),TypeResource.AUDIO.getValue());
        return question;
    }

    @Override
    public Question saveQuestionQuestionResponse(QuestionPhotoDto questionPhotoDto) throws IOException {
        // save question
        Question question = questionRepository.save(mapperQuestionPhotoDtoToQuestion(questionPhotoDto, TypeExercise.QUESTION_RESPONSE.getValue()));

        // save answer

        saveAnswer(questionPhotoDto.getAnswers(),question.getId());

        //save audio
        saveFile(questionPhotoDto.getAudio(),question.getId(),TypeResource.AUDIO.getValue());
        return question;
    }

    @Override
    public Question saveQuestionShortConversation(QuestionPhotoDto questionPhotoDto) throws IOException {
        Question question = questionRepository.save(mapperQuestionPhotoDtoToQuestion(questionPhotoDto, TypeExercise.SHORT_CONVERSATION.getValue()));

        // save answer

        saveAnswer(questionPhotoDto.getAnswers(),question.getId());
        saveFile(questionPhotoDto.getAudio(),question.getId(),TypeResource.AUDIO.getValue());
        return question;
    }

    @Override
    public Question saveQuestionIncomplete(QuestionPhotoDto questionPhotoDto) throws IOException {
        Question question = questionRepository.save(mapperQuestionPhotoDtoToQuestion(questionPhotoDto, TypeExercise.INCOMPLETE_SENTENCE.getValue()));

        // save answer

        saveAnswer(questionPhotoDto.getAnswers(),question.getId());
        return question;
    }

    @Override
    public Question saveQuestionShortTalk(QuestionShortTalkDto questionPhotoDto, String type) throws IOException {
        Question questionParent = questionRepository.save(mapperQuestionShortTaskToQuestion(questionPhotoDto, type));
        saveFile(questionPhotoDto.getAudio(),questionParent.getId(),TypeResource.AUDIO.getValue());

        for (ChildrenQuestionDto childrenQuestionDto: questionPhotoDto.getQuestions()) {
            Question question = new Question();
            question.setName(childrenQuestionDto.getName());
            question.setLevel(questionPhotoDto.getLevel());
            question.setTypeQuestion(Integer.parseInt(questionPhotoDto.getType()));
            question.setTypeSkill(type);
            question.setParentQuestion(questionParent.getId());
            question.setIndexCorrect(childrenQuestionDto.getAnswerCorrect());
            Question questionSave = questionRepository.save(question);
            saveAnswer(childrenQuestionDto.getAnswers(),questionSave.getId());
        }
        return questionParent;
    }

    @Override
    public Question saveQuestionTextCompletion(QuestionShortTalkDto questionPhotoDto, String type) throws IOException {
        Question questionParent = questionRepository.save(mapperQuestionShortTaskToQuestion(questionPhotoDto, type));
        for (ChildrenQuestionDto childrenQuestionDto: questionPhotoDto.getQuestions()) {
            Question question = new Question();
            question.setName(childrenQuestionDto.getName());
            question.setLevel(questionPhotoDto.getLevel());
            question.setTypeQuestion(Integer.parseInt(questionPhotoDto.getType()));
            question.setTypeSkill(type);
            question.setParentQuestion(questionParent.getId());
            question.setIndexCorrect(childrenQuestionDto.getAnswerCorrect());
            Question questionSave = questionRepository.save(question);
            saveAnswer(childrenQuestionDto.getAnswers(),questionSave.getId());
        }
        return questionParent;
    }


    @Override
    public Question updateQuestionPhoto(QuestionPhotoDto questionPhotoDto) throws IOException {
        // save question
        Question question = questionRepository.findById(Integer.parseInt(questionPhotoDto.getId())).orElseThrow(()->new RuntimeException("Không tìm thấy câu hỏi"));
        question.setName(questionPhotoDto.getName().trim());
        question.setIndexCorrect(questionPhotoDto.getAnswerCorrect());
        Question questionSave = questionRepository.save(question);
        // save answer
        List<Answer> answers = answerRepository.findAllByQuestionId(question.getId());
        answerRepository.deleteAll(answers);
        saveAnswer(questionPhotoDto.getAnswers(),question.getId());

        List<Resource> resources = resourceRepository.findAllByQuestionId(question.getId());
        Resource image = null;
        Resource audio = null;
        for (Resource resource: resources) {
            if(resource.getTypeResource().equals(TypeResource.IMAGE.getValue())){
                image = resource;
            }else{
                audio = resource;
            }
        }
        // save image
        saveUpdateFile(questionPhotoDto.getImage(),question.getId(),TypeResource.IMAGE.getValue(),image);
        //save audio
        saveUpdateFile(questionPhotoDto.getAudio(),question.getId(),TypeResource.AUDIO.getValue(),audio);
        return question;
    }

    @Override
    public Question updateQuestionResponse(QuestionPhotoDto questionPhotoDto) throws IOException {
        // save question
        Question question = questionRepository.findById(Integer.parseInt(questionPhotoDto.getId())).orElseThrow(()->new RuntimeException("Không tìm thấy câu hỏi"));
        question.setName(questionPhotoDto.getName().trim());
        question.setIndexCorrect(questionPhotoDto.getAnswerCorrect());
        if(!CommonUtils.isEmptyOrNull(questionPhotoDto.getDescription())){
            question.setDescription(questionPhotoDto.getDescription());
        }
        Question questionSave = questionRepository.save(question);
        // save answer
        List<Answer> answers = answerRepository.findAllByQuestionId(question.getId());
        answerRepository.deleteAll(answers);
        saveAnswer(questionPhotoDto.getAnswers(),question.getId());

        List<Resource> resources = resourceRepository.findAllByQuestionId(question.getId());
        Resource image = null;
        Resource audio = null;
        for (Resource resource: resources) {
            if(resource.getTypeResource().equals(TypeResource.IMAGE.getValue())){
                image = resource;
            }else{
                audio = resource;
            }
        }
        // save image
        //save audio
        saveUpdateFile(questionPhotoDto.getAudio(),question.getId(),TypeResource.AUDIO.getValue(),audio);
        return question;
    }

    @Override
    public Question updateShortTalk(QuestionShortTalkDto questionPhotoDto) throws IOException {
        Question questionParent = questionRepository.findById(Integer.parseInt(questionPhotoDto.getId())).orElseThrow(() -> new RuntimeException("Không tìm thấy câu hỏi"));
        questionParent.setName(questionPhotoDto.getName());
        questionParent.setDescription(questionPhotoDto.getDescription());
        List<Resource> resource = resourceRepository.findAllByQuestionId(questionParent.getId());
        saveUpdateFile(questionPhotoDto.getAudio(),questionParent.getId(),TypeResource.AUDIO.getValue(),resource.get(0));

        List<Question> questions = questionRepository.findAllByParentQuestion(questionParent.getId());
        List<Answer> answers = answerRepository.findAllByQuestionIdIn(questions.stream().map(Question::getId).collect(Collectors.toList()));
        answerRepository.deleteAll(answers);
        questionRepository.deleteAll(questions);


        for (ChildrenQuestionDto childrenQuestionDto: questionPhotoDto.getQuestions()) {
            Question question = new Question();
            question.setName(childrenQuestionDto.getName());
            question.setLevel(questionParent.getLevel());
            question.setTypeQuestion(questionParent.getTypeQuestion());
            question.setTypeSkill(questionParent.getTypeSkill());
            question.setParentQuestion(questionParent.getId());
            question.setIndexCorrect(childrenQuestionDto.getAnswerCorrect());
            Question questionSave = questionRepository.save(question);
            saveAnswer(childrenQuestionDto.getAnswers(),questionSave.getId());
        }
        return questionParent;

    }

    @Override
    public Question updateIncomplete(QuestionPhotoDto questionPhotoDto) throws IOException {
        Question question = questionRepository.findById(Integer.parseInt(questionPhotoDto.getId())).orElseThrow(()->new RuntimeException("Không tìm thấy câu hỏi"));
        question.setName(questionPhotoDto.getName().trim());
        question.setIndexCorrect(questionPhotoDto.getAnswerCorrect());
        if(!CommonUtils.isEmptyOrNull(questionPhotoDto.getDescription())){
            question.setDescription(questionPhotoDto.getDescription());
        }
        Question questionSave = questionRepository.save(question);
        // save answer
        List<Answer> answers = answerRepository.findAllByQuestionId(question.getId());
        answerRepository.deleteAll(answers);
        saveAnswer(questionPhotoDto.getAnswers(),question.getId());

        return question;
    }

    @Override
    public Question updateTextCompletion(QuestionShortTalkDto questionPhotoDto) throws IOException {
        Question questionParent = questionRepository.findById(Integer.parseInt(questionPhotoDto.getId())).orElseThrow(() -> new RuntimeException("Không tìm thấy câu hỏi"));
        questionParent.setName(questionPhotoDto.getName());
        questionParent.setDescription(questionPhotoDto.getDescription());

        List<Question> questions = questionRepository.findAllByParentQuestion(questionParent.getId());
        List<Answer> answers = answerRepository.findAllByQuestionIdIn(questions.stream().map(Question::getId).collect(Collectors.toList()));
        answerRepository.deleteAll(answers);
        questionRepository.deleteAll(questions);

        for (ChildrenQuestionDto childrenQuestionDto: questionPhotoDto.getQuestions()) {
            Question question = new Question();
            question.setName(childrenQuestionDto.getName());
            question.setLevel(questionParent.getLevel());
            question.setTypeQuestion(questionParent.getTypeQuestion());
            question.setTypeSkill(questionParent.getTypeSkill());
            question.setParentQuestion(questionParent.getId());
            question.setIndexCorrect(childrenQuestionDto.getAnswerCorrect());
            Question questionSave = questionRepository.save(question);
            saveAnswer(childrenQuestionDto.getAnswers(),questionSave.getId());
        }
        return questionParent;

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

    @Override
    public List<Question> finAll() {
        List<Question> questions = new ArrayList<>();
        List<Question> questionsAll = questionRepository.findAll();
        for (Question question: questionsAll) {
            if(question.getTypeSkill().equals(TypeExercise.SHORT_TALK.getValue()) ||
               question.getTypeSkill().equals(TypeExercise.TEXT_COMPLETION.getValue()) ||
               question.getTypeSkill().equals(TypeExercise.DOUBLE_PASSAGE.getValue()) ||
               question.getTypeSkill().equals(TypeExercise.SINGLE_PASSAGE.getValue())){
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
    public void delete(Question question) {
        String typeSkill = question.getTypeSkill();
        switch (typeSkill){
            case "photo","question_response","short_conversation":
                List<Answer> answers  = answerRepository.findAllByQuestionId(question.getId());
                answerRepository.deleteAll(answers);

                List<Resource> resources = resourceRepository.findAllByQuestionId(question.getId());
                resourceRepository.deleteAll(resources);
                break;
            case "incomplete_sentence":
                List<Answer> answers1  = answerRepository.findAllByQuestionId(question.getId());
                answerRepository.deleteAll(answers1);
                break;

            case "short_talk":
                List<Resource> resources1 = resourceRepository.findAllByQuestionId(question.getId());
                resourceRepository.deleteAll(resources1);

                List<Question> questions = questionRepository.findAllByParentQuestion(question.getId());
                List<Answer> answers2 = answerRepository.findAllByQuestionIdIn(questions.stream().map(Question::getId).collect(Collectors.toList()));
                answerRepository.deleteAll(answers2);
                questionRepository.deleteAll(questions);
                break;

            case "text_completion","single_passage","double_passage":
                List<Question> questions1 = questionRepository.findAllByParentQuestion(question.getId());
                List<Answer> answers21 = answerRepository.findAllByQuestionIdIn(questions1.stream().map(Question::getId).collect(Collectors.toList()));
                answerRepository.deleteAll(answers21);
                questionRepository.deleteAll(questions1);
                break;

        }
        questionRepository.delete(question);

    }


    @Override
    public QuestionPhotoDto findQuestionPhoto(Integer id) {
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
                if(!CommonUtils.isEmptyOrNull(resource.getFileName())){
                    image.setNameBase64(resource.getFileName() != null ? resource.getFileName() : null);
                }else {
                    image.setLink(resource.getImage() != null ? resource.getImage() : null);
                }
            }else{
                if(!CommonUtils.isEmptyOrNull(resource.getFileName())){
                    audio.setNameBase64(resource.getFileName() != null ? resource.getFileName() : null);
                }else {
                    audio.setLink(resource.getAudio() != null ? resource.getAudio() : null);
                }
            }
        }
        questionPhotoDto.setImage(image);
        questionPhotoDto.setAudio(audio);
        return questionPhotoDto;
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
    public List<Question> responseQuestionExercise(QuestionExerciseDto questionExerciseDto,  Integer typeQuestion) {
        String levelToeic = questionExerciseDto.getLevel();
        String skillToeic = questionExerciseDto.getSkill();
        List<Question> questions = questionRepository.findAllByLevelAndSkill(levelToeic,skillToeic,typeQuestion);

        return questions;
    }


    /**
     * Lưu image or audio với question id cho trước khi upate photo
     * @param fileBase64Dto
     * @param id
     */
    public void saveUpdateFile(FileBase64Dto fileBase64Dto, Integer id, String type,Resource resource) throws IOException {

        if(CommonUtils.isEmptyOrNull(fileBase64Dto.getLink()) && !CommonUtils.isEmptyOrNull(fileBase64Dto.getBase64())){
            String contentTypeAudio = CommonUtils.getContentTypeFromBase64(fileBase64Dto.getBase64());
            MultipartFile multipartFile = CommonUtils.convertBase64ToMultipartFile(CommonUtils.getBase64DataFromBase64String(fileBase64Dto.getBase64()),fileBase64Dto.getNameBase64(),contentTypeAudio);
            String link = imageUploadService.upload(multipartFile);

            if(TypeResource.IMAGE.getValue().equals(type)){
                resource.setImage(link);
            }
            else {
                resource.setAudio(link);
            }
            resource.setFileName(multipartFile.getOriginalFilename());
            resource.setTypeResource(type);
            resource.setQuestionId(id);
            resourceRepository.save(resource);
        }
        else if(!CommonUtils.isEmptyOrNull(fileBase64Dto.getLink())){
            if(TypeResource.IMAGE.getValue().equals(type)){
                resource.setImage(fileBase64Dto.getLink());
            }
            else {
                resource.setAudio(fileBase64Dto.getLink());
            }
            resource.setFileName(null);
            resource.setTypeResource(type);
            resource.setQuestionId(id);
            resourceRepository.save(resource);
        }

    }


    /**
     * Lưu image or audio với question id cho trước khi thêm mới photo
     * @param fileBase64Dto
     * @param id
     */
    public void saveFile(FileBase64Dto fileBase64Dto, Integer id, String type) throws IOException {

        if(CommonUtils.isEmptyOrNull(fileBase64Dto.getLink())){
            String contentTypeAudio = CommonUtils.getContentTypeFromBase64(fileBase64Dto.getBase64());
            MultipartFile multipartFile = CommonUtils.convertBase64ToMultipartFile(CommonUtils.getBase64DataFromBase64String(fileBase64Dto.getBase64()),fileBase64Dto.getNameBase64(),contentTypeAudio);
            String link = imageUploadService.upload(multipartFile);
            Resource resource = new Resource();
            if(TypeResource.IMAGE.getValue().equals(type)){
                resource.setImage(link);
            }
            else {
                resource.setAudio(link);
            }
            resource.setFileName(multipartFile.getOriginalFilename());
            resource.setTypeResource(type);
            resource.setQuestionId(id);
            resourceRepository.save(resource);
        }else {
            Resource resource = new Resource();
            if(TypeResource.IMAGE.getValue().equals(type)){
                resource.setImage(fileBase64Dto.getLink());
            }
            else {
                resource.setAudio(fileBase64Dto.getLink());
            }
            resource.setTypeResource(type);
            resource.setQuestionId(id);
            resourceRepository.save(resource);
        }

    }


    public Question mapperQuestionPhotoDtoToQuestion(QuestionPhotoDto questionPhotoDto, String type){
        Question question = new Question();
        if(!CommonUtils.isEmptyOrNull(questionPhotoDto.getId())){
            question.setId(Integer.parseInt(questionPhotoDto.getId()));
        }
        question.setName(questionPhotoDto.getName().trim());
        if(!CommonUtils.isEmptyOrNull(questionPhotoDto.getType())){
            question.setTypeQuestion(Integer.parseInt(questionPhotoDto.getType()));
        }
        if(!CommonUtils.isEmptyOrNull(questionPhotoDto.getDescription())){
            question.setDescription(questionPhotoDto.getDescription());
        }
        question.setTypeSkill(type);
        if(!CommonUtils.isEmptyOrNull(questionPhotoDto.getLevel())){
            question.setLevel(questionPhotoDto.getLevel());
        }
        question.setIndexCorrect(questionPhotoDto.getAnswerCorrect());
        return question;
    }

    public Question mapperQuestionShortTaskToQuestion(QuestionShortTalkDto questionShortTalkDto, String type){
        Question question = new Question();
        if(!CommonUtils.isEmptyOrNull(questionShortTalkDto.getId())){
            question.setId(Integer.parseInt(questionShortTalkDto.getId()));
        }
        question.setName(questionShortTalkDto.getName().trim());
        if(!CommonUtils.isEmptyOrNull(questionShortTalkDto.getType())){
            question.setTypeQuestion(Integer.parseInt(questionShortTalkDto.getType()));
        }
        if(!CommonUtils.isEmptyOrNull(questionShortTalkDto.getDescription())){
            question.setDescription(questionShortTalkDto.getDescription());
        }
        question.setTypeSkill(type);
        if(!CommonUtils.isEmptyOrNull(questionShortTalkDto.getLevel())){
            question.setLevel(questionShortTalkDto.getLevel());
        }

        return question;
    }

    public List<QuestionApi> mapperQuestionToQuestionApis(List<Question> questions){
        List<QuestionApi> questionApis = new ArrayList<>();
        for (Question question: questions) {
            QuestionApi questionApi = new QuestionApi();
            questionApi.setId(question.getId());
            questionApi.setName(question.getName());
            questionApis.add(questionApi);
        }
        return questionApis;
    }
}
