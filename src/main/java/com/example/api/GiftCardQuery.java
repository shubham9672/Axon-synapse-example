package com.example.api;

public abstract class GiftCardQuery {
    private String kind;

    public GiftCardQuery(String kind) {
        this.kind = kind;
    }

    public String getKind() {
        return kind;
    }
}
