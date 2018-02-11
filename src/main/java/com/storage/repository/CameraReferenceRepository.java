package com.storage.repository;

import com.storage.entity.CameraReferenceEntity;
import com.storage.entity.WebStorageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;

public interface CameraReferenceRepository extends JpaRepository<CameraReferenceEntity, WebStorageEntity> {

    List<CameraReferenceEntity> findByDateBetween(OffsetDateTime dateFrom, OffsetDateTime dateTo);

}
