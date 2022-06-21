package com.practis.web.selenide.component;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class LabelPanel {

  private final SelenideElement expandLabelIcon = $("div[data-test='sidebar-labels-expand']");
  private final SelenideElement collapseLabelIcon = $("div[data-test='sidebar-labels-collapse']");

  private final SelenideElement searchLabelField = $(".sc-jdhxzS.cxtuGB");

  private final SelenideElement filterByText = $(".sc-bxDcbH.dEfOZT");
  private final SelenideElement sideBarLabelMenu = $("div[data-test='sidebar-labels-menu']");

  private final SelenideElement noLabelsSelectedText = $(".sc-kHdrEO.eEdWKj");
  private final SelenideElement clearLabelsButton = $("div[data-test='sidebar-labels-clear']");

  private final SelenideElement addLabelLink = $("div[data-test='sidebar-labels-add']");
  private final SelenideElement labelInput = $("input.sc-fotPbf.foPnlk.sc-eicnZh.bSQUzR");
  private final SelenideElement saveLabelLinkEmptyField = $("a.sc-hiwReK.ljwuRH.sc-ewSSRw.gxSiLH");
  private final SelenideElement saveLabelLink = $("a.sc-hiwReK.jNUMxZ.sc-ewSSRw.dlrIgY");
  private final SelenideElement cancelLabelInput = $(".sc-hiwReK.jNUMxZ.sc-hJZJLb.dsruXg");


  private final ElementsCollection labelRow = $$("div[data-test*='sidebar-labels-item']");
}
