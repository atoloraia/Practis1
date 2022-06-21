package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;

public class LibraryTabs {

  private final ElementsCollection navigationTabs = $$("a.sc-gloWkm.fRHUuU");

  public void goToTab(final String tabName) {
    executeJavaScript("arguments[0].click()", navigationTabs.find(Condition.text(tabName)));
  }

}
