package com.storage.service;

import com.storage.dto.WebStorageDTO;

import java.time.OffsetDateTime;
import java.util.List;

public interface WebStorageService {

    String saveMediaInfo(WebStorageDTO webStorageDTO, Long cameraId);

    WebStorageDTO getMediaInfo(String uuid);

    List<WebStorageDTO> getMediaInfoByDateInterval(OffsetDateTime from, OffsetDateTime to);

}
