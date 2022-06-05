package com.practis.web.selenide.page.company;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.practis.dto.NewPractisSetInput;
import lombok.Getter;

@Getter
public class PractisSetCreatePage {

  private final SelenideElement titleField = $("input.sc-fotPbf.igAmbG.sc-ddmHQj.dAmMMA");
  private final SelenideElement descriptionField = $("textarea.sc-hrjXZO.dCUvnd  ");
  private final SelenideElement publishButton = $("button.sc-jgrIVw.lclJYS.primary");
  private final SelenideElement saveAsDraftButton = $("button.sc-jgrIVw.bHkvOE.inverse");

  private final ElementsCollection tabs = $$("div.sc-jnqsAJ");
  private final ElementsCollection scenarios = $$(
      "div[data-rbd-draggable-id*='PRACTIS_SET_DRAGGABLE_ID']");

  private final SelenideElement totalDuration = $("div.sc-bGJWfc.dUroER");
  private final SelenideElement totalReps = $("div.sc-dRtHqC.kqYilk");
  private final SelenideElement minAccuracy = $("div.sc-gfvLdu.dXHJOC");

  /**
   * Fill Title.
   */
  public void fillTitle(final NewPractisSetInput inputData) {
    titleField.append(inputData.getTitle());
  }

  /**
   * Fill Title, Description, add Challenge and Scenario.
   */
  public void createPractisSet(final NewPractisSetInput inputData, final String label,
      final String scenarioTitle, final String challengeTitle) {
    fillForm(inputData, label);
    addScenario(scenarioTitle);
    addChallenge(challengeTitle);

  }

  public void fillForm(final NewPractisSetInput inputData, final String label) {
    fillTitle(inputData);
    descriptionField.append(inputData.getDescription());
  }

  /**
   * Adds Scenario to PractisSet.
   */
  public void addScenario(final String scenarioTitle) {
    tabs.find(Condition.matchText("Scenarios")).click();
    scenarios.find(Condition.matchText(scenarioTitle)).doubleClick();
  }

  /**
   * Adds Challenge to PractisSet.
   */
  public void addChallenge(final String challengeTitle) {
    tabs.find(Condition.matchText("Challenges")).click();
    scenarios.find(Condition.matchText(challengeTitle)).doubleClick();
  }
}
