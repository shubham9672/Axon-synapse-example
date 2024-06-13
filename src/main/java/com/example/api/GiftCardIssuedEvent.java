package com.example.api;

public class GiftCardIssuedEvent extends GiftCardEvent {
  protected GiftCardIssuedEvent(String id,int amount) {
    super(id, "GiftCardIssuedEvent");
    this.amount = amount;
  }
  private int amount;
  public int getAmount() {
    return amount;
  }
  public void setAmount(int amount) {
    this.amount = amount;
  }
  
}
