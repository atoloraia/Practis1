package com.practis.web.selenide.page.company;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class PractisSetEditPage {

  private final SelenideElement editPractisSetTitle = $(".sc-hRyPyr.gHWulj");
  private final SelenideElement assignUsersButton = $(".sc-iAKVOt.fsPmRB.inverse");
  private final SelenideElement editButton = $(".sc-iAKVOt.cclkiD.primary");
  private final SelenideElement archiveButton = $(".sc-cURddv.ketvwP");
  private final SelenideElement publishedText = $(".sc-dSJNcr.jlAZty");

  private final SelenideElement titleField = $(".sc-jRQAMF.dKMzKE.sc-dEZnrQ.iAeZLM-container");
  private final SelenideElement createdByText = $(".sc-hCiPOX.eOIUWd");
  private final SelenideElement descriptionField = $(".sc-bIiQDN.lkGymL");

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
