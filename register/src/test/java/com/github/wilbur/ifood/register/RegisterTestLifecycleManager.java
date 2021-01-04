package com.github.wilbur.ifood.register;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import org.testcontainers.containers.PostgreSQLContainer;

import java.util.HashMap;
import java.util.Map;

public class RegisterTestLifecycleManager implements QuarkusTestResourceLifecycleManager
{
    public static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:latest");

    @Override
    public Map<String, String> start()
    {
        POSTGRES.start();
        Map<String, String> properties = new HashMap<>();

        properties.put("quarkus.datasource.jdbc.url", POSTGRES.getJdbcUrl());
        properties.put("quarkus.datasource.username", POSTGRES.getUsername());
        properties.put("quarkus.datasource.password", POSTGRES.getPassword());

        return properties;
    }

    @Override
    public void stop()
    {
        if (POSTGRES != null && POSTGRES.isRunning()) {
            POSTGRES.stop();
        }
    }
}
