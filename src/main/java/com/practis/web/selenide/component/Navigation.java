package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;

public class Navigation {

  private final ElementsCollection navigationItems = $$("a.sc-cNKpQo.cbzisw");

  public void goTo(final String name) {
    navigationItems.find(Condition.text(name)).click();
  }
}
