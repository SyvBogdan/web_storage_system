package com.storage.config.generator;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public interface NowGenerator {

    OffsetDateTime generateTime();

}
