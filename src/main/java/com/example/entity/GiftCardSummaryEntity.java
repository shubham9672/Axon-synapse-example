package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class GiftCardSummaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private int initialAmount;

    @Column(nullable = false)
    private int remainingAmount;

    @Column(nullable = false)
    private boolean isActive = true;

    // Default constructor for JPA
    protected GiftCardSummaryEntity() {
    }

    public GiftCardSummaryEntity(String id, int initialAmount, int remainingAmount, boolean isActive) {
        this.id = id;
        this.initialAmount = initialAmount;
        this.remainingAmount = remainingAmount;
        this.isActive = isActive;
    }

    public GiftCardSummaryEntity(String id, int initialAmount, int remainingAmount) {
        this(id, initialAmount, remainingAmount, true);
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getInitialAmount() {
        return initialAmount;
    }

    public void setInitialAmount(int initialAmount) {
        this.initialAmount = initialAmount;
    }

    public int getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(int remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
