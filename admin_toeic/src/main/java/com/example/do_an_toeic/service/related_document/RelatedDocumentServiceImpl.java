package com.example.do_an_toeic.service.related_document;

import com.example.do_an_toeic.dto.RelatedDocumentDto;
import com.example.do_an_toeic.entity.RelatedDocument;
import com.example.do_an_toeic.entity.Resource;
import com.example.do_an_toeic.enums.LevelToeic;
import com.example.do_an_toeic.enums.TypeResource;
import com.example.do_an_toeic.repository.RelatedDocumentRepository;
import com.example.do_an_toeic.repository.ResourceRepository;
import com.example.do_an_toeic.service.firebase.ImageUploadService;
import com.example.do_an_toeic.service.resource.ResourceService;
import com.example.do_an_toeic.utils.CommonUtils;
import com.example.do_an_toeic.utils.Constants;
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
public class RelatedDocumentServiceImpl implements RelatedDocumentService {
    @Autowired
    private RelatedDocumentRepository relatedDocumentRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ImageUploadService imageUploadService;


    @Override
    public List<RelatedDocumentDto> getAll() {
        List<RelatedDocument> relatedDocuments = relatedDocumentRepository.findAll();
        List<RelatedDocumentDto> relatedDocumentDtos = modelMapper.map(relatedDocuments, new TypeToken<List<RelatedDocumentDto>>() {
        }.getType());
        List<Integer> relatedDocumentIds = relatedDocuments.stream().map(RelatedDocument::getId).collect(Collectors.toList());
        List<Resource> resources = resourceService.findByRelatedDocumentIds(relatedDocumentIds);
        Map<Integer, Resource> mapResources = resources.stream()
                .collect(Collectors.toMap(Resource::getRelatedDocumentId, Function.identity()));

        for (RelatedDocumentDto relatedDocumentDto : relatedDocumentDtos) {
            Resource resource = mapResources.get(relatedDocumentDto.getId());
            relatedDocumentDto.setFileName(resource.getFileName());
            relatedDocumentDto.setLinkImage(resource.getImage());
        }
        return relatedDocumentDtos;
    }

    @Override
    @Transactional
    public RelatedDocument save(RelatedDocumentDto relatedDocumentDto, String nameImage) {
        RelatedDocument relatedDocument = modelMapper.map(relatedDocumentDto, RelatedDocument.class);
        if (relatedDocumentDto.getId() != null) {

            RelatedDocument relatedDocumentUpdate = relatedDocumentRepository.findById(relatedDocumentDto.getId()).orElseGet(null);
            relatedDocumentUpdate.setName(relatedDocumentDto.getName());
            relatedDocumentUpdate.setTitle(relatedDocumentDto.getTitle());
            relatedDocumentUpdate.setDescription(relatedDocumentDto.getDescription());
            relatedDocumentUpdate.setLevelToeic(Constants.getLevelToeic(relatedDocumentDto.getLevelToeic()));
            RelatedDocument relatedDocumentUpdateSave = relatedDocumentRepository.save(relatedDocumentUpdate);

            Resource resource = resourceRepository.findByRelatedDocumentId(relatedDocumentDto.getId());
            if (!CommonUtils.isEmptyOrNull(relatedDocumentDto.getLinkImage())) {
                resource.setImage(relatedDocumentDto.getLinkImage());
                resource.setFileName(null);
            } else if (CommonUtils.isEmptyOrNull(relatedDocumentDto.getLinkImage()) &&
                    !CommonUtils.isEmptyOrNull(nameImage) &&
                    !nameImage.equals("No chosen")) {
                if (!nameImage.equals(resource.getFileName())) {
                    String linkImage = imageUploadService.upload(relatedDocumentDto.getFileImage());
                    resource.setImage(linkImage);
                    resource.setFileName(relatedDocumentDto.getFileImage().getOriginalFilename());
                }
            } else if (CommonUtils.isEmptyOrNull(relatedDocumentDto.getLinkImage()) &&
                    CommonUtils.isEmptyOrNull(relatedDocumentDto.getFileImage().getOriginalFilename())) {
                resource.setImage("");
                resource.setFileName(null);
            }
            resourceRepository.save(resource);
            return relatedDocumentUpdateSave;
        } else {
            relatedDocument.setNumberAccess(0);
            relatedDocument.setLevelToeic(Constants.getLevelToeic(relatedDocumentDto.getLevelToeic()));
            RelatedDocument relatedDocumentSave = relatedDocumentRepository.save(relatedDocument);
            // lưu ảnh
            Resource resource = new Resource();
            resource.setRelatedDocumentId(relatedDocumentSave.getId());
            if (relatedDocumentDto.getFileImage() != null && !relatedDocumentDto.getFileImage().isEmpty()) {
                resource.setFileName(relatedDocumentDto.getFileImage().getOriginalFilename());
            }
            resource.setTypeResource(TypeResource.IMAGE.getValue());

            if (!CommonUtils.isEmptyOrNull(relatedDocumentDto.getLinkImage())) {
                resource.setImage(relatedDocumentDto.getLinkImage());
            } else {
                String linkImage = imageUploadService.upload(relatedDocumentDto.getFileImage());
                resource.setImage(linkImage);
            }
            resourceRepository.save(resource);
            return relatedDocumentSave;
        }

    }

    @Override
    public RelatedDocumentDto findById(Integer id) {
        return modelMapper.map(relatedDocumentRepository.findById(id).orElseGet(null), RelatedDocumentDto.class);
    }

    @Override
    public RelatedDocumentDto detailUpdate(Integer id) {
        RelatedDocumentDto relatedDocumentDto = findById(id);
        if (relatedDocumentDto != null) {
            Resource resource = resourceRepository.findByRelatedDocumentId(id);
            if (!CommonUtils.isEmptyOrNull(resource.getFileName())) {
                relatedDocumentDto.setFileName(resource.getFileName());
            } else {
                relatedDocumentDto.setLinkImage(resource.getImage());
            }
            return relatedDocumentDto;
        }
        return null;
    }

    @Override
    public RelatedDocumentDto detailShow(Integer id) {
        RelatedDocumentDto relatedDocumentDto = findById(id);
        if (relatedDocumentDto != null) {
            Resource resource = resourceRepository.findByRelatedDocumentId(id);
            relatedDocumentDto.setFileName(resource.getFileName());
            relatedDocumentDto.setLinkImage(resource.getImage());
            return relatedDocumentDto;
        }
        return null;
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        RelatedDocument relatedDocument = relatedDocumentRepository.findById(id).orElseGet(null);
        if (relatedDocument == null) {
            return false;
        } else {
            Resource resource = resourceRepository.findByRelatedDocumentId(id);
            resource.setIsDeleted(true);
            resourceRepository.save(resource);
            relatedDocumentRepository.delete(relatedDocument);
            return true;
        }
    }


}
