package com.onbudget.service;

import java.io.IOException;

import com.onbudget.service.config.TestEnviroment;
import com.onbudget.service.server.OnBudgetServer;

/**
 * Entry point.
 */
public final class Main {

    public static final void main(final String[] args) throws IOException {
        new OnBudgetServer(
                new TestEnviroment()
        ).start();
        System.in.read();
    }
}
