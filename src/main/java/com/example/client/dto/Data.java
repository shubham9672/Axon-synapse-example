package com.example.client.dto;

import java.util.List;

import lombok.AllArgsConstructor;
@lombok.Data
@AllArgsConstructor
public class Data {
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
