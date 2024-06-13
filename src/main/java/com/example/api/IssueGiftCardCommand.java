package com.example.api;


public class IssueGiftCardCommand extends GiftCardCommand {
  public IssueGiftCardCommand(String id,int amount) {
    super(id, "IssueGiftCardCommand");
    this.amount=amount;
  }
  int amount;
  public int getAmount() {
    return amount;
  }
}