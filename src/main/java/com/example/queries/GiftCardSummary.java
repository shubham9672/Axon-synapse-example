package com.example.queries;

import com.example.api.GiftCardCancelledEvent;
import com.example.api.GiftCardIssuedEvent;
import com.example.api.GiftCardRedeemedEvent;

import io.quarkus.logging.Log;

import com.example.api.GiftCardEvent;

public class GiftCardSummary {
    private final String id;
    private final int initialAmount;
    private final int remainingAmount;
    private final boolean isActive;

    public GiftCardSummary(String id, int initialAmount, int remainingAmount) {
        this(id, initialAmount, remainingAmount, true);
    }

    public GiftCardSummary(String id, int initialAmount, int remainingAmount, boolean isActive) {
        this.id = id;
        this.initialAmount = initialAmount;
        this.remainingAmount = remainingAmount;
        this.isActive = isActive;
    }

    // Getters for the fields
    public String getId() {
        return id;
    }

    public int getInitialAmount() {
        return initialAmount;
    }

    public int getRemainingAmount() {
        return remainingAmount;
    }

    public boolean isActive() {
        return isActive;
    }
    

    public  GiftCardSummary handleEvent(GiftCardSummary state, GiftCardEvent event) {
        if (event instanceof GiftCardIssuedEvent) {
            GiftCardIssuedEvent issuedEvent = (GiftCardIssuedEvent) event;
            Log.infov("GiftCardIssuedEvent event {0} handled successfully", issuedEvent.getId());
            return new GiftCardSummary(issuedEvent.getId(), issuedEvent.getAmount(), issuedEvent.getAmount());
        } else if (event instanceof GiftCardRedeemedEvent) {
            GiftCardRedeemedEvent redeemedEvent = (GiftCardRedeemedEvent) event;
            Log.infov("GiftCardRedeemedEvent event {0} handled successfully", redeemedEvent.getId());
            return state != null
                    ? new GiftCardSummary(state.getId(), state.getInitialAmount(), state.getRemainingAmount() - redeemedEvent.getAmount())
                    : state;
        } else if (event instanceof GiftCardCancelledEvent) {
            GiftCardCancelledEvent cancelledEvent = (GiftCardCancelledEvent) event;
            Log.infov("GiftCardCancelledEvent event {0} handled successfully", cancelledEvent.getId());
            return state != null
                    ? new GiftCardSummary(state.getId(), state.getInitialAmount(), 0, false)
                    : state;
        } else {
            Log.warnv("Unhandled event type: {0}", event.getClass().getName());
            return state;
        }
}
}