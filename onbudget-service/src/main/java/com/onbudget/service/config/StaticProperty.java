package com.onbudget.service.config;

import java.util.Optional;

/**
 * Configuration property with static value.
 */
public final class StaticProperty implements ConfigurationProperty {

    private final String value;

    public StaticProperty(final String value) {
        this.value = value;
    }

    @Override
    public Optional<String> optionalValue() {
        return Optional.of(this.value);
    }
}
