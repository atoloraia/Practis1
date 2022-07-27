package com.practis.web.selenide.page.company.challenge;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class ChallengeEditPage {

  private final SelenideElement headerText = $("div[data-test='challenge-page-title']");
  private final SelenideElement publishedText = $("span[data-test='challenge-publish-date']");
  private final SelenideElement archiveButton = $(".sc-iAKVOt.fsPmRB.inverse");
  private final SelenideElement editButton = $(".sc-iAKVOt.cclkiD.primary");
  private final SelenideElement cancelEditButton = $(".sc-jcFkyM.hPyXsU.inverse");
  private final SelenideElement saveChangesButton = $(".sc-jcFkyM.gNfFbi.undefined.primary");

  private final SelenideElement titleField = $("input[data-test='challenge-title']");
  private final SelenideElement labelsButton = $("div[data-test='challenge-labels-button']");
  private final SelenideElement labelsText = $("div[data-test='challenge-labels-label']");
  private final SelenideElement createdByText = $(".sc-gMpDKJ.cGtbaP");

  private final SelenideElement descriptionField =
      $("textarea[data-test='chanllenge-description']");
  private final SelenideElement descriptionCounterText =
      $("div[data-test='chanllenge-description-counter']");
  private final SelenideElement generateForAllButton =
      $("button[data-test='challenge-generate-for-all']");
  private final SelenideElement playAllButton = $("button[data-test='challenge-play-all']");
  private final SelenideElement customerAvatar = $("div[data-test='challenge-customer-avatar']");
  private final SelenideElement customerText = $("p[data-test='challenge-customer-label']");
  private final SelenideElement repAvatar = $("div[data-test='challenge-rep-logo']");
  private final SelenideElement repText = $("p[data-test='challenge-rep-label']");
  private final SelenideElement dragButton =
      $("div[data-test='challenge-customer-line-drag-handle']");
  private final SelenideElement customerLineText = $(".sc-kwDLmJ.fVplyE");
  private final SelenideElement customerLineField = $("div[data-test='challenge-customer-line']");
  private final SelenideElement playCustomerLineButton =
      $("button[data-test='play-challenge-customer-line-audio']");
  private final SelenideElement deleteCustomerLineButton =
      $("button[data-test='delete-challenge-customer-line']");
  private final SelenideElement recordCustomerLineButton =
      $("button[data-test='record-challenge-customer-line-audio']");
  private final SelenideElement generateCustomerLineAudioButton =
      $("button[data-test='generate-challenge-customer-line-audio']");
  private final SelenideElement repLineText = $("span[data-test='challenge-user-auto-reply']");
  private final SelenideElement repLineField = $(".sc-gwcONz.gxkXPa");
  private final SelenideElement addCustomerLineButton =
      $("a[data-test='add-challenge-customer-line']");

}
