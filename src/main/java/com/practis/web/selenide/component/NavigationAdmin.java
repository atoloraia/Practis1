package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class NavigationAdmin {

  public final SelenideElement companyNavigationItem = $("a[data-test='sidebar-companies']");
  public final SelenideElement adminNavigationItem = $("a[data-test='sidebar-admin']");


}
