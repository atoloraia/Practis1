package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class NudgePopUp {

  private final SelenideElement nudgeTitle = $(".sc-dDllZQ.bWOfqM");
  private final SelenideElement nudgeDescription = $(".sc-fkpjlT.tbyUX");
  private final SelenideElement fromField = $(".sc-gKckTs.ewVxfk.sc-iBjMXQ.caFaKa");
  private final SelenideElement messageField = $(".sc-lbJCdB.ggMcvG");
  private final SelenideElement messageFieldText = $(".sc-bXRCX.kmfYeP.sc-QsvGH.hbqvsi");
  private final SelenideElement cancelButton = $(".sc-jcFkyM.gNfFbi.inverse");
  private final SelenideElement applyButton = $(".sc-jcFkyM.gNfFbi.undefined.primary");
}
