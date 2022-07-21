package com.onbudget.service.config;

import java.util.Optional;

public final class TestEnviroment implements ConfigurationProperty {

    private final String key;

    public TestEnviroment() {
        this("TEST_ENV");
    }

    public TestEnviroment(final String value) {
        this.key = value;
    }

    @Override
    public Optional<String> optionalValue() {
        return Optional.of(System.getenv(key));
    }
}
