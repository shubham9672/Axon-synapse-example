package com.example.client.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HandlerRegistrationResponse {
    private List<String> names;
    private String endpoint;
    private String endpointType;
    private List<EndpointOption> endpointOptions;
    private String clientId;
    private String componentName;
    private int loadFactor;
    private int concurrency;
    private boolean enabled;
    private String context;
    private String clientAuthenticationId;
    private String serverAuthenticationId;
    private String id;

    // Getters and setters
}

