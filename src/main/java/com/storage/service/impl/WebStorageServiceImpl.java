package com.storage.service.impl;

import com.storage.config.generator.NowGenerator;
import com.storage.config.generator.UUIDGenerator;
import com.storage.dto.WebStorageDTO;
import com.storage.entity.CameraReferenceEntity;
import com.storage.entity.WebStorageEntity;
import com.storage.repository.CameraReferenceRepository;
import com.storage.repository.WebStorageRepository;
import com.storage.service.WebStorageService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WebStorageServiceImpl extends AbstractMapperService<WebStorageEntity, WebStorageDTO> implements WebStorageService {

    private UUIDGenerator uuidGenerator;

    private NowGenerator nowGenerator;

    private WebStorageRepository webStorageRepository;

    private CameraReferenceRepository cameraReferenceRepository;

    public WebStorageServiceImpl(UUIDGenerator uuidGenerator,
                                 NowGenerator nowGenerator,
                                 WebStorageRepository webStorageRepository,
                                 CameraReferenceRepository cameraReferenceRepository) {
        this.uuidGenerator = uuidGenerator;
        this.nowGenerator = nowGenerator;
        this.webStorageRepository = webStorageRepository;
        this.cameraReferenceRepository = cameraReferenceRepository;
    }

    @Transactional
    @Override
    public String saveMediaInfo(WebStorageDTO webStorageDTO, Long cameraId) {
        webStorageDTO.setUuid(uuidGenerator.generate());

        CameraReferenceEntity cameraReferenceEntity = CameraReferenceEntity.builder()
                .webStorage(toEntity(webStorageDTO))
                .date(nowGenerator.generateTime())
                .cameraId(cameraId)
                .build();

        return cameraReferenceRepository.save(
                cameraReferenceEntity)
                .getWebStorage()
                .getId();
    }


    @Override
    public WebStorageDTO getMediaInfo(String uuid) {
        return toDTO(webStorageRepository.findOne(uuid));
    }

    @Override
    public List<WebStorageDTO> getMediaInfoByDateInterval(OffsetDateTime from, OffsetDateTime to) {
        List<CameraReferenceEntity> listReference = cameraReferenceRepository.findByDateBetween(from, to);
        return listReference.stream()
                .map(resultList -> toDTO(resultList.getWebStorage())).collect(Collectors.toList());
    }

    @Override
    protected Class<WebStorageDTO> getDTOClass() {
        return WebStorageDTO.class;
    }

    @Override
    protected Class<WebStorageEntity> getEntityClass() {
        return WebStorageEntity.class;
    }
}
