package com.example.api;

public class RedeemGiftCardCommand extends GiftCardCommand {
    public RedeemGiftCardCommand(String id, int amount) {
        super(id, "RedeemGiftCardCommand");
        this.amount=amount;
    }

    int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
