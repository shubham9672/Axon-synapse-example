package com.example.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item {
    private String id;
    private MetaData metaData;
    private Object payload;
    private String payloadType;
    private String payloadRevision;
    private String name;
    private String aggregateId;
    private String aggregateType;
    private int sequenceNumber;
    private int index;

    // Getters and setters
}

