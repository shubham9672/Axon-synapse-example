package com.example.repository;

import com.example.entity.GiftCardSummaryEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GiftCardSummaryRepo implements PanacheRepositoryBase<GiftCardSummaryEntity,String> {
    
}
