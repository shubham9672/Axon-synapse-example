package com.example.api;

public class GiftCardCancelledEvent extends GiftCardEvent {
  protected GiftCardCancelledEvent(String id) {
    super(id, "GiftCardCancelledEvent");
  }
}
