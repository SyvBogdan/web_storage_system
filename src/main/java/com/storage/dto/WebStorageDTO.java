package com.storage.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Arrays;

@Data
@Builder
public class WebStorageDTO implements Serializable {

    private static final long serialVersionUID = 793865353531713021L;

    private String uuid;
    private byte[] byteMediaInfo;
}
