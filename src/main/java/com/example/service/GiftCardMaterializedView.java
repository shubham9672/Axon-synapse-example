package com.example.service;

import com.example.api.GiftCardEvent;
import com.example.entity.GiftCardSummaryEntity;
import com.example.queries.GiftCardSummary;
import com.example.repository.GiftCardSummaryRepo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class GiftCardMaterializedView {

    private final GiftCardSummary eventHandler;
    private final GiftCardSummaryRepo viewStateRepository;

    @Inject
    public GiftCardMaterializedView(GiftCardSummary eventHandler, GiftCardSummaryRepo viewStateRepository) {
        this.eventHandler = eventHandler;
        this.viewStateRepository = viewStateRepository;
    }

    @Transactional
    public void handleEvent(GiftCardEvent event) {
        GiftCardSummaryEntity currentState = viewStateRepository.findById(event.getId());
        GiftCardSummary newState = eventHandler.handleEvent(currentState != null ? toDomain(currentState) : null,
                event);
        viewStateRepository.persist(toEntity(newState));
    }

    private GiftCardSummary toDomain(GiftCardSummaryEntity entity) {
        return new GiftCardSummary(entity.getId(), entity.getInitialAmount(), entity.getRemainingAmount(), entity.isActive());
    }

    private GiftCardSummaryEntity toEntity(GiftCardSummary summary) {
        return new GiftCardSummaryEntity(summary.getId(),summary.getInitialAmount(), summary.getRemainingAmount(), summary.isActive());
    }
}
