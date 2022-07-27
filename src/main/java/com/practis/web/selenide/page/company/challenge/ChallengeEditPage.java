package com.practis.web.selenide.page.company.challenge;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class ChallengeEditPage {

  private final SelenideElement headerTextChallenge = $("div[data-test='challenge-page-title']");
  private final SelenideElement publishedTextChallenge =
      $("span[data-test='challenge-publish-date']");
  private final SelenideElement archiveButtonChallenge = $(".sc-iAKVOt.fsPmRB.inverse");
  private final SelenideElement editButtonChallenge = $(".sc-iAKVOt.cclkiD.primary");

  private final SelenideElement titleChallenge = $("input[data-test='challenge-title']");
  private final SelenideElement labelsButtonChallenge =
      $("div[data-test='challenge-labels-button']");
  private final SelenideElement labelsTextChallenge =
      $("div[data-test='challenge-labels-label']");
  private final SelenideElement createdByTextChallenge = $(".sc-gMpDKJ.cGtbaP");

  private final SelenideElement descriptionChallenge =
      $("textarea[data-test='chanllenge-description']");
  private final SelenideElement descriptionCounterChallenge =
      $("div[data-test='chanllenge-description-counter']");
  private final SelenideElement generateForAllChallenge =
      $("button[data-test='challenge-generate-for-all']");
  private final SelenideElement playAllChallenge = $("button[data-test='challenge-play-all']");
  private final SelenideElement customerAvatarChallenge =
      $("div[data-test='challenge-customer-avatar']");
  private final SelenideElement customerTextChallenge =
      $("p[data-test='challenge-customer-label']");
  private final SelenideElement repAvatarChallenge = $("div[data-test='challenge-rep-logo']");
  private final SelenideElement repTextChallenge = $("p[data-test='challenge-rep-label']");
  private final SelenideElement dragButtonChallenge =
      $("div[data-test='challenge-customer-line-drag-handle']");
  private final SelenideElement customerLineTextChallenge = $(".sc-kwDLmJ.fVplyE");
  private final SelenideElement customerLineChallenge =
      $("div[data-test='challenge-customer-line']");
  private final SelenideElement playCustomerLineChallenge =
      $("button[data-test='play-challenge-customer-line-audio']");
  private final SelenideElement repLineTextChallenge = $(".sc-kwDLmJ.fVplyE.sc-iCiyNz.eKywve");
  private final SelenideElement repLineChallenge = $(".sc-gwcONz.gxkXPa");

}
