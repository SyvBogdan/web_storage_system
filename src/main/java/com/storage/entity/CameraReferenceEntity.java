package com.storage.entity;


import com.storage.config.JpaConfig;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@Table(name="tb_camera_reference",schema = JpaConfig.DB_SCHEMA, catalog = JpaConfig.DB_CATALOG)
public class CameraReferenceEntity {

    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private WebStorageEntity webStorage;

    @Column(name="camera_id")
    private Long cameraId;

    @Column(name="storage_date")
    private OffsetDateTime date;

}
