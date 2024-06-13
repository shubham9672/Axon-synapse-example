package com.example.api;

public class GiftCardRedeemedEvent extends GiftCardEvent {
  protected GiftCardRedeemedEvent(String id, int amount) {
    super(id, "GiftCardRedeemedEvent");
    this.amount=amount;
  }
  private int amount;
  public int getAmount() {
    return amount;
  }
  public void setAmount(int amount) {
    this.amount = amount;
  }
  
}
