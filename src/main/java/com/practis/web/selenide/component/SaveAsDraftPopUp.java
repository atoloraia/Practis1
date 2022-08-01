package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class SaveAsDraftPopUp {

  private final SelenideElement saveAsDraftTitle = $(".sc-gqBScU.cxfQjP");
  private final SelenideElement saveAsDraftText = $(".sc-loNplZ.ljnJBb");
  private final SelenideElement draftTitleField = $(".sc-gKckTs.AtGud.sc-exjpvi.kXTLtS");
  private final SelenideElement cancelButton = $(".sc-jcFkyM.gNfFbi.inverse");
  private final SelenideElement saveButton = $(".sc-iAKVOt.cclkiD.primary");
}
