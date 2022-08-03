package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class PacingDropdown {

  private final SelenideElement pacingButton =
      $("div[data-test='practis-set-pacing-selected-value']");
  private final ElementsCollection pacingItem =
      $$("div[data-test='practis-set-pacing-select-item']");

}