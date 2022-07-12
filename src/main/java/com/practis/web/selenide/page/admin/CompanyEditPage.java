package com.practis.web.selenide.page.admin;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class CompanyEditPage {

  private final SelenideElement titleNameElement =
      $("div[data-test='company-settings-page-title']");
  private final SelenideElement headerNameElement =
      $("div[data-test='company-settings-page-subtitle']");
  private final SelenideElement backButton = $(".sc-hlGCtx.jfUeE");

  private final SelenideElement companySelector = $("div[data-test='companyDropDownSection']");
  private final SelenideElement actionButton = $("div[data-test='actionDropDownToggleButton']");

  private final SelenideElement smallUserpic = $(".sc-xiKGw.fRxJuv");
  private final SelenideElement companyTitle = $(".sc-eaUozk.gagvqI");
  private final SelenideElement createdAtText = $(".sc-jsKGvB.fkftBY");

  private final ElementsCollection downloadReportButton = $$(".sc-ikJzcn.jzlQLO.inverse");
  private final SelenideElement viewAssessmentButton =
      $(".sc-ikJzcn.imkdtE.undefined.primary");

  private final SelenideElement companyDetailsButton = $(".sc-jtdmdz.cVXiWL");
  private final SelenideElement largeUserpic = $(".sc-fKVsgm.jEEVOP");
  private final SelenideElement uploadPictureButton =
      $(".sc-kLwgWK.dTxZDk.sc-iztaVh.fktAMC");
  private final SelenideElement pictureText = $(".sc-gKdcnr.fQfNNv");

  private final SelenideElement companyNameFieldElement = $("input[name='companyName']");
  private final SelenideElement companyOwnerField = $(".sc-LQrcB.iolXbq");
  private final SelenideElement emailField = $(".sc-dPiKHq.dyAUFj");
  private final SelenideElement deleteButton = $(".sc-gWXaA-D.lpaQjp.primary");
  private final SelenideElement updateButton = $(".sc-gWXaA-D.graIUG.primary");

}
