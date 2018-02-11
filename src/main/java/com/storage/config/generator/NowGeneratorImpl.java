package com.storage.config.generator;

import java.time.OffsetDateTime;

public class NowGeneratorImpl implements NowGenerator {
    @Override
    public OffsetDateTime generateTime() {
        return OffsetDateTime.now();
    }
}
