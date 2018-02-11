package com.storage.config.generator;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DefaultUUIDGenerator implements UUIDGenerator {
    public DefaultUUIDGenerator() {
    }

    public String generate() {
        return UUID.randomUUID().toString();
    }
}
