package com.practis.web.selenide.page.admin;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class AdminCreatePage {

  private final ElementsCollection inputRowElements = $$("div.sc-fcyzyh.jTsXuB");
  private final SelenideElement addRowLinkElement = $("a.sc-jRQAMF.cimCkU");

  private final ElementsCollection emailFieldElements = $$("input[name*='email']");
  private final ElementsCollection firstNameFieldElements = $$("input[name*='firstName']");
  private final ElementsCollection lastNameFieldElements = $$("input[name*='lastName']");
  private final ElementsCollection passwordFieldElements = $$("input[name*='password']");
  private final ElementsCollection passwordVisibilityButtonElements = $$("div.sc-egiSv.cFnxLQ");

  private final SelenideElement createButtonElement = $("button[type='submit']");
}
