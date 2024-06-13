package com.example.api;

public class FindByIdQuery extends GiftCardQuery{
  public FindByIdQuery(String id) {
    super("FindByIdQuery");
    this.id=id;
    //TODO Auto-generated constructor stub
  }
  private String id;
  public void setId(String id) {
    this.id = id;
  }
  public String getId() {
    return id;
  }
}