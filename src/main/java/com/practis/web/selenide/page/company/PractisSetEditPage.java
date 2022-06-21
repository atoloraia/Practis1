package com.practis.web.selenide.page.company;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class PractisSetEditPage {

  private final SelenideElement titleField = $("input.sc-fotPbf.igAmbG.sc-cEqpQT.byyINI");
  private final SelenideElement descriptionField = $("textarea.sc-fpYaub.jjJPJh  ");

}
