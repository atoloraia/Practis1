package com.practis.web.selenide.service;

import com.practis.web.selenide.component.NewItemSelector;

public class NewItemSelectorService {

  private final NewItemSelector newItemSelector = new NewItemSelector();

  public void create(final String itemName) {
    newItemSelector.getNewItemSelector().click();
    newItemSelector.getRow(itemName).click();
  }
}
