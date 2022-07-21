package com.onbudget.service.jersey;

import org.glassfish.jersey.server.ResourceConfig;

public final class OnBudgetFakeAPI extends ResourceConfig {

    public OnBudgetFakeAPI() {
        super(
            PingEndpoint.class
        );
    }
}
