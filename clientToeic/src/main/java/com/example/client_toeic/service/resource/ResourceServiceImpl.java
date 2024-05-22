package com.example.client_toeic.service.resource;

import com.example.client_toeic.dto.ResourceDto;
import com.example.client_toeic.entity.Resource;
import com.example.client_toeic.enums.TypeResource;
import com.example.client_toeic.repository.ResourceRepository;
import com.example.client_toeic.service.firebase.ImageUploadService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ImageUploadService imageUploadService;

    @Override
    public List<Resource> finAll() {
        return resourceRepository.findAll();
    }

    @Override
    public Resource findById(Integer id) {
        return resourceRepository.findById(id).orElseGet(() -> null);
    }


    @Override
    public Resource findByNewsId(Integer id) {
        return resourceRepository.findByNewsId(id);
    }

    @Override
    public List<Resource> findByNewsIds(List<Integer> ids) {
        return resourceRepository.findByNewsIdIn(ids);
    }

    @Override
    public List<Resource> findByRelatedDocumentIds(List<Integer> ids) {
        return resourceRepository.findByRelatedDocumentIdIn(ids);
    }

    @Override
    public List<Resource> findByExerciseIds(List<Integer> ids) {
        return resourceRepository.findByExerciseIdIn(ids);
    }

    @Override
    public List<Resource> findAllNewsId() {
        return resourceRepository.findAllByNewsId();
    }

    @Override
    public List<ResourceDto> getResourceImage() {
        List<Resource> resources = resourceRepository.findAllByTypeResource(TypeResource.IMAGE.getValue());
        return modelMapper.map(resources, new TypeToken<List<ResourceDto>>() {}.getType());
    }

    @Override
    public List<ResourceDto> getResourceAudio() {
        List<Resource> resources = resourceRepository.findAllByTypeResource(TypeResource.AUDIO.getValue());
        return modelMapper.map(resources, new TypeToken<List<ResourceDto>>() {
        }.getType());

    }

    @Override
    @Transactional
    public Resource saveFile(ResourceDto resourceDto,String typeResource) {
        if(resourceDto.getId() == null || resourceDto.getId() == 0) {
            String linkImage = imageUploadService.upload(resourceDto.getMultipartFile());
            Resource resource = new Resource();
            resource.setImage(linkImage);
            resource.setFileName(resourceDto.getMultipartFile().getOriginalFilename());
            resource.setTypeResource(typeResource);
            resource.setIsDeleted(false);
            return resourceRepository.save(resource);
        }else{
            // update
            Resource resource = findById(resourceDto.getId());
            if(resource == null){
                return null;
            }
            if(!resourceDto.getMultipartFile().getOriginalFilename().equals("")){
                String linkImage = imageUploadService.upload(resourceDto.getMultipartFile());
                resource.setImage(linkImage);
                resource.setFileName(resourceDto.getMultipartFile().getOriginalFilename());
                return resourceRepository.save(resource);
            }
        }
        return null;
    }

    @Override
    @Transactional
    public Boolean deleteFile(Integer id) {
        Resource resource = findById(id);
        if(resource == null){
            return false;
        }else{
            resource.setIsDeleted(true);
            resourceRepository.save(resource);
            return true;
        }
    }


}
