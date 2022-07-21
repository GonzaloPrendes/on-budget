package com.onbudget.service.server;

import java.util.Optional;
import com.onbudget.service.config.ConfigurationProperty;
import com.onbudget.service.config.StaticProperty;
import com.onbudget.service.jersey.OnBudgetFakeAPI;

public final class OnBudgetServer implements Server {

    private final ConfigurationProperty config;

    public OnBudgetServer(final ConfigurationProperty conf) {
        this.config = conf;
    }

    @Override
    public void start() {
        this.testConfig()
            .ifPresentOrElse(
                    this::startTestServer,
                    this::startServer
            );
    }

    private Optional<String> testConfig() {
        return this.config.optionalValue();
    }

    private void startTestServer(final String arg) {
        new GrizzlyServer(
                new StaticProperty(
                    "8081"
                ),
                new OnBudgetFakeAPI()
        ).start();
    }

    private void startServer() {
        throw new UnsupportedOperationException("not implemented yet.");
    }
}
