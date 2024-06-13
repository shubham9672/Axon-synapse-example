package com.example.client;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class ConfigService {

    @ConfigProperty(name = "axon.context", defaultValue = "default")
    String axonContext;

    @ConfigProperty(name = "axon.api.url", defaultValue = "http://localhost:8080/v1")
    String axonApiUrl;

    public String getAxonContext() {
        return axonContext;
    }

    public String getAxonApiUrl() {
        return axonApiUrl;
    }
}
