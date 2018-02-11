package com.storage.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = {"com.storage.entity"})
public class JpaConfig {

    public static final String DB_SCHEMA = "storage";
    public static final String DB_CATALOG = "media";

}
