package com.example.api;

public abstract class GiftCardEvent {
    private final String id;
    private final String kind;

    protected GiftCardEvent(String id,String kind) {
        this.id = id;
        this.kind = "GiftCardEvent";
    }

    public String getId() {
        return id;
    }

    public String getKind() {
        return kind;
    }
    
}
