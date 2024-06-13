package com.example.api;

public abstract class GiftCardCommand {
    private String id;
    private String kind;
    
    public GiftCardCommand(String id, String kind) {
        this.id = id;
        this.kind = kind;
    }
    public String getId() {
        return id;
    }
    public String getKind() {
        return kind;
    }
    
}
