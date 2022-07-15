package com.practis.web.selenide.page.company;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class PractisSetEditPage {

  private final SelenideElement editPractisSetTitle = $("div[data-test='practis-set-page-title']");
  private final SelenideElement assignUsersButton =
      $("button[data-test='assign-practis-set-users']");
  private final SelenideElement editButton = $("button[data-test='edit-practis-set']");
  private final SelenideElement archiveButton = $("button[data-test='archive-practis-set']");
  private final SelenideElement publishedText = $("span[data-test='practis-set-publish-date']");

  private final SelenideElement titleField = $("input[data-test='practis-set-title']");
  private final SelenideElement createdByText = $(".sc-hCiPOX.eOIUWd");
  private final SelenideElement descriptionField =
      $("textarea[data-test='practis-set-description']");

  private final SelenideElement contentField = $(".sc-fMfzYA.sc-lvLSk.cYTnV.kZDvaj");
  private final SelenideElement previewButton = $(".sc-dNiKyU.hYZJvx");
  private final SelenideElement durationText = $(".sc-bTeyJZ.cmLbtq");
  private final SelenideElement minimumRepsText = $(".sc-jxUdiu.imoPSG");
  private final SelenideElement requiredRepsCountText = $(".sc-gvnPBw.keakTH");
  private final SelenideElement deleteContentButton = $(".sc-fusXuK.hzkCg");
  private final SelenideElement scenarioTitle = $(".sc-crMJNM.hAbdJf");
  private final SelenideElement challengeTitle = $(".sc-crMJNM.jinoNE");
  private final SelenideElement contentTitle = $(".sc-dZldFf.geKNHN");


}
