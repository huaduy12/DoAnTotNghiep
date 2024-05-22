package com.example.client_toeic.service.self_learning;

import com.example.client_toeic.dto.NewsDto;
import com.example.client_toeic.dto.SelfLearningDto;
import com.example.client_toeic.entity.Exercise;
import com.example.client_toeic.entity.News;
import com.example.client_toeic.entity.Resource;
import com.example.client_toeic.enums.TypeExercise;
import com.example.client_toeic.enums.TypeResource;
import com.example.client_toeic.repository.ExerciseRepository;
import com.example.client_toeic.repository.ResourceRepository;
import com.example.client_toeic.service.firebase.ImageUploadService;
import com.example.client_toeic.service.resource.ResourceService;
import com.example.client_toeic.utils.CommonUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    /**
     *
     * @param type của bài tụ học
     * @param page số trang hiện tại
     * @return
     */
    @Override
    public Page<SelfLearningDto> getAll(String type, Integer page) {
        Pageable pageable =  PageRequest.of(page-1,6);
        Page<Exercise> exercises = exerciseRepository.findAllByTypeExercise(type,pageable);
        List<SelfLearningDto> selfLearningDtos = modelMapper.map(exercises.getContent(), new TypeToken<List<SelfLearningDto>>() {
        }.getType());
        List<Integer> exerciseIds = exercises.getContent().stream().map(Exercise::getId).collect(Collectors.toList());
        List<Resource> resources = resourceService.findByExerciseIds(exerciseIds);
        Map<Integer, Resource> mapResources = resources.stream()
                .collect(Collectors.toMap(Resource::getExerciseId, Function.identity()));

        for (SelfLearningDto selfLearningDto : selfLearningDtos) {
            Resource resource = mapResources.get(selfLearningDto.getId());
            selfLearningDto.setFileName(resource.getFileName());
            selfLearningDto.setLinkImage(resource.getImage());
        }
        return new PageImpl<>(selfLearningDtos,pageable,exercises.getTotalElements());
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

    @Override
    public List<SelfLearningDto> getLearningOther(String type, Integer id) {
        List<Exercise> exercises = exerciseRepository.findByIdOther(id,type);
        return setResourceForNews(exercises.stream().limit(20).collect(Collectors.toList()));
    }

    private List<SelfLearningDto> setResourceForNews(List<Exercise> exercises){
        List<SelfLearningDto> selfLearningDtoList = modelMapper.map(exercises,new TypeToken<List<SelfLearningDto>>(){}.getType());
        List<Integer> selfLearningDtoIds = selfLearningDtoList.stream().map(SelfLearningDto::getId).collect(Collectors.toList());
        List<Resource> resources = resourceService.findByExerciseIds(selfLearningDtoIds);
        Map<Integer, Resource> mapResources = resources.stream()
                .collect(Collectors.toMap(Resource::getExerciseId, Function.identity()));
        for (SelfLearningDto selfLearningDto: selfLearningDtoList) {
            Resource resource = mapResources.get(selfLearningDto.getId());
            selfLearningDto.setFileName(resource.getFileName());
            selfLearningDto.setLinkImage(resource.getImage());
        }
        return selfLearningDtoList;
    }
}
