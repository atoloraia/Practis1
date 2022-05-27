package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;

public class NavigationAdmin {

  private final ElementsCollection navigationItems = $$("a.sc-fXEqXD.gMoVQi");

  public void goTo(final String name) {
    executeJavaScript("arguments[0].click()", navigationItems.find(Condition.text(name)));
  }

  //TODO add navigation selectors

}
