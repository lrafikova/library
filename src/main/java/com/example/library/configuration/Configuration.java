package com.example.library.configuration;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

/**
 * Configuration.class allow to create a {@link Config} instance
 * from the specified interface
 *
 * @author Liliya Rafikova
 */
public class Configuration {

    public static final Configuration INSTANCE = new Configuration();

    private final CommonConfiguration commonConfiguration;

    private Configuration() {
        this.commonConfiguration = ConfigFactory.create(CommonConfiguration.class);
    }

    /**
     * Creates a {@link Config} instance from the specified interface
     *
     * @return instance of CommonConfiguration interface
     */
    public CommonConfiguration getCommonConfiguration() {
        return commonConfiguration;
    }
}
