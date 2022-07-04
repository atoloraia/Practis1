package com.practis.web.selenide.page.company;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class ScenarioEditPage {

  private final SelenideElement titleField = $("input[data-test='scenario-title']");
  private final SelenideElement descriptionField = $("textarea[data-test='scenario-description']");



}
