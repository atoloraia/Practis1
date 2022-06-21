package com.practis.web.selenide.page.company;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class ChallengeCreatePage {

  private final SelenideElement addNewChallengeTitle = $(".sc-hBdiqp.cVSLkd");
  private final SelenideElement notPublishTitle = $("div.sc-oEdJy.bWvQrU");

  private final SelenideElement titleField = $("input[data-test='challenge-title']");
  private final SelenideElement descriptionField = $("textarea[placeholder='Description']");
  private final SelenideElement addLabels = $(".sc-gJfZKJ.kXGlCv  ");

  private final SelenideElement generateForAllButton = $("button[title='Generate for All']");
  private final SelenideElement playForAllButton = $(".sc-khQdMy.kgXwrK.sc-iTTZDz.fMJojC.primary");

  private final ElementsCollection customerTitle = $$(".sc-WttwK.hzaBbY");
  private final ElementsCollection repTitle = $$(".sc-WttwK.hzaBbY");

  private final SelenideElement customerLine = $("div[data-test='challenge-customer-line']");
  private final ElementsCollection deleteCustomerLine =
      $$("div[data-test='delete-challenge-customer-line']");
  private final ElementsCollection recordAudioButton =
      $$(".sc-khQdMy.clHYnC.sc-eFTzKH.eGmYXo.transparent");
  private final ElementsCollection playButtons = $$("button[title='Play']");
  private final ElementsCollection dragAndDropArea =
      $$("div[data-rbd-drag-handle-draggable-id='temp_768926a0-f145-11ec-9e4d-eda5676421a3']");

  private final ElementsCollection userWillRespondHereLine = $$(".sc-gqBScU.cxfQjP");

  private final SelenideElement addCustomerLineButton =
      $("a[data-test='add-challenge-customer-line']");

  private final SelenideElement publishButton = $("button[data-test='publish-challenge']");
  private final SelenideElement saveAsDraftButton =
      $("button[data-test='save-challenge-as-draft']");

  public final SelenideElement outSideTheChallengeForm = $(".sc-jCHUzJ.jnbUyO");
}
