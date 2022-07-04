package com.practis.web.selenide.page.company;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class ChallengeCreatePage {

  private final SelenideElement addNewChallengeTitle = $(".sc-bmhVIU.kKFWfm");
  private final SelenideElement notPublishTitle = $("div.sc-bLenIk.crslKy");

  private final SelenideElement titleField = $("input[data-test='challenge-title']");
  private final SelenideElement descriptionField = $("textarea[placeholder='Description']");
  private final SelenideElement labelsButton = $("div[data-test='challenge-labels-button']");
  private final SelenideElement labelsButtonName = $("div[data-test='challenge-labels-label']");
  private final ElementsCollection labelRows = $$("div[data-test='challenge-labels-item']");
  private final SelenideElement saveChangesLabelButton =
      $("button[data-test='challenge-labels-save-changes']");
  private final ElementsCollection labelName = $$("div[data-test='challenge-labels-item-name']");

  private final SelenideElement generateForAllButton = $("button[title='Generate for All']");
  private final SelenideElement playForAllButton = $("button[title='Play All']");

  private final ElementsCollection customerTitle = $$("p.sc-gyYGyc.fdglIp");
  private final ElementsCollection repTitle = $$("p.sc-gyYGyc.fdglIp");

  private final SelenideElement customerLine = $("div[data-test='challenge-customer-line']");
  private final ElementsCollection deleteCustomerLine =
      $$("div[data-test='delete-challenge-customer-line']");
  private final ElementsCollection recordAudioButton =
      $$("button[title='Record Audio']");
  private final ElementsCollection playButtons = $$("button[title='Play']");
  private final ElementsCollection dragAndDropArea =
      $$("div[data-rbd-drag-handle-draggable-id='temp_af85e2e0-f587-11ec-a7f2-c1021c2b0e65']");

  private final ElementsCollection userWillRespondHereLine = $$(".sc-dprgub.kTeJjw");

  private final SelenideElement addCustomerLineButton =
      $("a[data-test='add-challenge-customer-line']");

  private final SelenideElement publishButton = $("button[data-test='publish-challenge']");
  private final SelenideElement saveAsDraftButton =
      $("button[data-test='save-challenge-as-draft']");

  public final SelenideElement outSideTheChallengeForm = $(".practis-logo");

  /**
   * Find label checkbox.
   */
  public SelenideElement findLabelCheckbox(final String label) {
    final var labelRow = labelRows.find(Condition.matchText(label));
    final var checkbox = labelRow.$("input[data-test='challenge-labels-item-checkbox']");
    return checkbox.parent();
  }
}
