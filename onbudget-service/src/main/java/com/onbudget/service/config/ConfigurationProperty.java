package com.onbudget.service.config;

import java.util.Optional;

/**
 * System configuration property.
 */
public interface ConfigurationProperty {

    /**
     * @return optional value of the configuration property
     */
    Optional<String> optionalValue();
}
