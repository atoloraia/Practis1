package com.practis.web.selenide.page.company;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class PractisSetEditPage {

  private final SelenideElement titleField = $("input.sc-fotPbf.igAmbG.sc-ddmHQj.dAmMMA");
  private final SelenideElement descriptionField = $("textarea.sc-hrjXZO.dCUvnd");

}
