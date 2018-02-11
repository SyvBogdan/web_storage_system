package com.storage.controller;

import com.storage.dto.WebStorageDTO;
import com.storage.service.WebStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/storage/media")
public class WebStorageController {

    private WebStorageService webStorageService;

    @Autowired
    public WebStorageController(WebStorageService webStorageService) {
        this.webStorageService = webStorageService;
    }

    @RequestMapping(
            method = RequestMethod.POST)
    @ResponseBody
    public String store(@RequestBody byte[] storageInfo, @RequestBody Long cameraId) {
        return webStorageService.saveMediaInfo(
                WebStorageDTO.builder()
                        .byteMediaInfo(storageInfo)
                        .build()
                , cameraId);
    }

    @RequestMapping(value = "/cameraId/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImageAsResponseEntity(@PathVariable("cameraId") Long cameraId,
                                                           @PathVariable("uuid") String uuid) {
        byte[] mediaInfo = webStorageService.getMediaInfo(uuid).getByteMediaInfo();
        return new ResponseEntity<>(mediaInfo, getHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/cameraId/{dateFrom}/{dateTo}", method = RequestMethod.GET)
    public ResponseEntity<List<byte[]>> getImagebyDateInterval(@PathVariable("dateFrom") OffsetDateTime dateFrom,
                                                               @PathVariable("dateTo") OffsetDateTime dateTo) {

        List<byte[]> fileList = webStorageService.getMediaInfoByDateInterval(dateFrom, dateTo)
                .stream().map(WebStorageDTO::getByteMediaInfo).collect(Collectors.toList());
        return new ResponseEntity<>(fileList, getHeaders(), HttpStatus.OK);
    }

    private HttpHeaders getHeaders() {

        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl(CacheControl.maxAge(24, TimeUnit.HOURS)
                .cachePublic()
                .getHeaderValue());
        return headers;
    }


}
