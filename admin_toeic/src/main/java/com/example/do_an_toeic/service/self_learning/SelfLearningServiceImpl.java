package com.example.do_an_toeic.service.self_learning;

import com.example.do_an_toeic.dto.RelatedDocumentDto;
import com.example.do_an_toeic.dto.SelfLearningDto;
import com.example.do_an_toeic.entity.Exercise;
import com.example.do_an_toeic.entity.RelatedDocument;
import com.example.do_an_toeic.entity.Resource;
import com.example.do_an_toeic.enums.TypeExercise;
import com.example.do_an_toeic.enums.TypeResource;
import com.example.do_an_toeic.repository.ExerciseRepository;
import com.example.do_an_toeic.repository.ResourceRepository;
import com.example.do_an_toeic.service.firebase.ImageUploadService;
import com.example.do_an_toeic.service.resource.ResourceService;
import com.example.do_an_toeic.utils.CommonUtils;
import com.example.do_an_toeic.utils.Constants;
import org.checkerframework.checker.units.qual.A;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SelfLearningServiceImpl implements SelfLearningService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private ImageUploadService imageUploadService;

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<SelfLearningDto> getAll(String type) {
        List<Exercise> exercises = exerciseRepository.findAllByTypeExercise(type);
        List<SelfLearningDto> selfLearningDtos = modelMapper.map(exercises, new TypeToken<List<SelfLearningDto>>() {
        }.getType());
        List<Integer> exerciseIds = exercises.stream().map(Exercise::getId).collect(Collectors.toList());
        List<Resource> resources = resourceService.findByExerciseIds(exerciseIds);
        Map<Integer, Resource> mapResources = resources.stream()
                .collect(Collectors.toMap(Resource::getExerciseId, Function.identity()));

        for (SelfLearningDto selfLearningDto : selfLearningDtos) {
            Resource resource = mapResources.get(selfLearningDto.getId());
            selfLearningDto.setFileName(resource.getFileName());
            selfLearningDto.setLinkImage(resource.getImage());
        }
       return selfLearningDtos;
    }

    @Override
    @Transactional
    public Exercise save(SelfLearningDto selfLearningDto, String nameImage, String type) {
        Exercise exercise = modelMapper.map(selfLearningDto, Exercise.class);
        if (selfLearningDto.getId() != null) {
            Exercise exerciseUpdate = exerciseRepository.findById(selfLearningDto.getId()).orElseGet(null);
            exerciseUpdate.setName(exercise.getName());
            exerciseUpdate.setTitle(exercise.getTitle());
            exerciseUpdate.setDescription(exercise.getDescription());
            Exercise exerciseUpdateSave = exerciseRepository.save(exerciseUpdate);

            Resource resource = resourceRepository.findByExerciseId(selfLearningDto.getId());
            if (!CommonUtils.isEmptyOrNull(selfLearningDto.getLinkImage())) {
                resource.setImage(selfLearningDto.getLinkImage());
                resource.setFileName(null);
            } else if (CommonUtils.isEmptyOrNull(selfLearningDto.getLinkImage()) &&
                    !CommonUtils.isEmptyOrNull(nameImage)) {
                if (!selfLearningDto.getFileImage().getOriginalFilename().equals("")) {
                    String linkImage = imageUploadService.upload(selfLearningDto.getFileImage());
                    resource.setImage(linkImage);
                    resource.setFileName(selfLearningDto.getFileImage().getOriginalFilename());
                }
            } else if (CommonUtils.isEmptyOrNull(selfLearningDto.getLinkImage()) &&
                    CommonUtils.isEmptyOrNull(selfLearningDto.getFileImage().getOriginalFilename()) &&
                    CommonUtils.isEmptyOrNull(nameImage)) {
                resource.setImage("");
                resource.setFileName(null);
            }
            resourceRepository.save(resource);
            return exerciseUpdateSave;
        } else {
            exercise.setNumberAccess(0);
            exercise.setTypeExercise(type);
            Exercise exerciseSave = exerciseRepository.save(exercise);
            // lưu ảnh
            Resource resource = new Resource();
            resource.setExerciseId(exerciseSave.getId());
            if (selfLearningDto.getFileImage() != null && !selfLearningDto.getFileImage().isEmpty()) {
                resource.setFileName(selfLearningDto.getFileImage().getOriginalFilename());
            }
            resource.setTypeResource(TypeResource.IMAGE.getValue());

            if (!CommonUtils.isEmptyOrNull(selfLearningDto.getLinkImage())) {
                resource.setImage(selfLearningDto.getLinkImage());
            } else if(!selfLearningDto.getFileImage().getOriginalFilename().equals("")){
                String linkImage = imageUploadService.upload(selfLearningDto.getFileImage());
                resource.setImage(linkImage);
            }
            resourceRepository.save(resource);
            return exerciseSave;
        }

    }


    @Override
    @Transactional
    public boolean delete(Integer id) {
        Exercise exercise = findById(id);
        if (exercise == null) {
            return false;
        } else {
            Resource resource = resourceRepository.findByExerciseId(id);
            resource.setIsDeleted(true);
            resourceRepository.save(resource);
            exerciseRepository.delete(exercise);
            return true;
        }
    }

    @Override
    public Exercise findById(Integer id) {
        return exerciseRepository.findById(id).orElseGet(null);
    }

    @Override
    public SelfLearningDto detailShowUpdate(Integer id) {
        SelfLearningDto selfLearningDto =  modelMapper.map(findById(id),SelfLearningDto.class);
        if (selfLearningDto != null) {
            Resource resource = resourceRepository.findByExerciseId(id);
            if (!CommonUtils.isEmptyOrNull(resource.getFileName())) {
                selfLearningDto.setFileName(resource.getFileName());
            } else {
                selfLearningDto.setLinkImage(resource.getImage());
            }
            return selfLearningDto;
        }
        return null;
    }

    @Override
    public SelfLearningDto detail(Integer id) {
        Exercise exercise = findById(id);
        SelfLearningDto selfLearningDto = null;
        if(exercise != null){
            selfLearningDto = modelMapper.map(exercise,SelfLearningDto.class);
            Resource resource = resourceRepository.findByExerciseId(id);
            selfLearningDto.setLinkImage(resource.getImage());
        }
        return selfLearningDto;
    }
}
