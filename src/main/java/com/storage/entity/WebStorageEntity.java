package com.storage.entity;


import com.storage.config.JpaConfig;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Builder
@Table(name="tb_media_storage",schema = JpaConfig.DB_SCHEMA, catalog = JpaConfig.DB_CATALOG)
public class WebStorageEntity implements Serializable {

    private static final long serialVersionUID = 823733608523519332L;

    @Id
    @Column(name="id")
    private String id;

    @Lob
    @Column(name="binary_file")
    private byte[] mediaFile;

}
