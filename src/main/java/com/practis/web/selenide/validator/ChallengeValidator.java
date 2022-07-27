package com.practis.web.selenide.validator;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.challengeCreatePage;
import static com.practis.web.selenide.configuration.PageObjectFactory.challengeEditPage;

import com.practis.dto.NewChallengeInput;
import com.practis.web.selenide.component.GridRow;
import com.practis.web.selenide.page.company.challenge.ChallengeEditPage;

public class ChallengeValidator {

  /**
   * Assert grid row with input data.
   */
  public static void assertChallengeGridRow(final NewChallengeInput inputData,
      final GridRow gridRow) {
    gridRow.get("Challenges").shouldBe(matchText(".*" + inputData.getTitle()));
  }

  /**
   * Assert data on edit page with input.
   */
  public static void assertChallengeData(final NewChallengeInput inputData,
      final ChallengeEditPage challengeEditPage) {
    challengeEditPage.getTitleField().shouldBe(attribute("value", inputData.getTitle()));
    challengeEditPage.getDescriptionField().shouldBe(text(inputData.getDescription()));
  }

  /**
   * Assert Title on edit page with input.
   */
  public static void assertChallengeTitle(final NewChallengeInput inputData,
      final ChallengeEditPage challengeEditPage) {
    challengeEditPage.getTitleField().shouldBe(attribute("value", inputData.getTitle()));
  }

  /**
   * Assert elements on New Challenge page.
   */
  public static void assertElementsOnNewChallengePage() {
    challengeCreatePage().getAddNewChallengeTitle().shouldBe(exactText("Add New Challenge"));
    challengeCreatePage().getAddNewChallengeTitle().shouldBe(visible);
    challengeCreatePage().getNotPublishTitle().shouldBe(exactText("Not Published Yet"));
    challengeCreatePage().getNotPublishTitle().shouldBe(visible);

    challengeCreatePage().getSaveAsDraftButton().shouldBe(visible);
    challengeCreatePage().getPublishButton().shouldBe(visible);

    challengeCreatePage().getTitleField().shouldBe(visible);
    challengeCreatePage().getTitleField()
        .shouldBe(attribute("placeholder", "Challenge Title"));

    challengeCreatePage().getLabelsButton().shouldBe(visible);
    challengeCreatePage().getLabelsButtonName().shouldBe(visible);
    challengeCreatePage().getLabelsButtonName().shouldBe(exactText("Labels"));

    challengeCreatePage().getDescriptionField().shouldBe(visible);
    challengeCreatePage().getDescriptionField().shouldBe(attribute("placeholder", "Description"));

    challengeCreatePage().getGenerateForAllButton().shouldBe(visible);
    challengeCreatePage().getGenerateForAllButton()
        .shouldBe(attribute("title", "Generate for All"));

    challengeCreatePage().getPlayForAllButton().shouldBe(visible);
    challengeCreatePage().getPlayForAllButton().shouldBe(exactText("Play All"));

    challengeCreatePage().getCustomerPic().shouldBe(visible);
    challengeCreatePage().getCustomerTitle().shouldBe(visible);
    challengeCreatePage().getCustomerTitle().shouldBe(exactText("Customer"));

    challengeCreatePage().getRepPic().shouldBe(visible);
    challengeCreatePage().getRepTitle().shouldBe(visible);
    challengeCreatePage().getRepTitle().shouldBe(exactText("Rep"));

    challengeCreatePage().getCustomerLine().shouldBe(visible);
    challengeCreatePage().getCustomerLine()
        .shouldBe(attribute("placeholder", "Write customer’s line here"));
    challengeCreatePage().getDeleteCustomerLine().get(0).shouldBe(visible);
    challengeCreatePage().getRecordAudioButton().get(0).shouldBe(visible);

    challengeCreatePage().getUserWillRespondHereLine().get(0)
        .shouldBe(exactText("User will respond here"));
    challengeCreatePage().getAddCustomerLineButton().shouldBe(visible);
  }

  /**
   * Assert elements on View Challenge page.
   */
  public static void assertElementsOnViewChallengePage() {
    challengeEditPage().getHeaderText().shouldBe(exactText("Edit Challenge"));
    challengeEditPage().getPublishedText().shouldBe(matchText("Published"));
    challengeEditPage().getArchiveButton().shouldBe(exactText("Archive"));
    challengeEditPage().getEditButton().shouldBe(exactText("Edit"));

    challengeEditPage().getTitleField().shouldBe(visible);
    challengeEditPage().getCreatedByText().shouldBe(matchText("Created by"));
    challengeEditPage().getLabelsButton().shouldBe(visible);
    challengeEditPage().getLabelsText().shouldBe(exactText("Labels"));

    challengeEditPage().getDescriptionField().shouldBe(visible);
    challengeEditPage().getDescriptionCounterText().shouldBe(visible);
    challengeEditPage().getDescriptionCounterText().shouldBe(matchText("/500"));
    challengeEditPage().getGenerateForAllButton().shouldBe(exactText("Generate for All"));
    challengeEditPage().getPlayAllButton().shouldBe(exactText("Play All"));
    challengeEditPage().getCustomerAvatar().shouldBe(visible);
    challengeEditPage().getCustomerText().shouldBe(exactText("Customer"));
    challengeEditPage().getRepAvatar().shouldBe(visible);
    challengeEditPage().getRepText().shouldBe(exactText("Rep"));
    challengeEditPage().getCustomerLineField().shouldBe(visible);
    challengeEditPage().getPlayCustomerLineButton().shouldBe(exactText("Play"));
    challengeEditPage().getCustomerLineText().shouldBe(exactText("Customer"));
    challengeEditPage().getRepLineField().shouldBe(exactText("User will respond here"));

  }

  /**
   * Assert elements on Edit Challenge page.
   */
  public static void assertElementsOnEditChallengePage() {
    {
      challengeEditPage().getHeaderText().shouldBe(exactText("Edit Challenge"));
      challengeEditPage().getEditButton().shouldBe(exactText("Edit"));

      challengeEditPage().getTitleField().shouldBe(visible);
      challengeEditPage().getCreatedByText().shouldBe(matchText("Created by"));
      challengeEditPage().getLabelsButton().shouldBe(visible);
      challengeEditPage().getLabelsText().shouldBe(exactText("Labels"));

      challengeEditPage().getDescriptionField().shouldBe(visible);
      challengeEditPage().getDescriptionCounterText().shouldBe(visible);
      challengeEditPage().getDescriptionCounterText().shouldBe(matchText("/500"));
      challengeEditPage().getGenerateForAllButton().shouldBe(exactText("Generate for All"));
      challengeEditPage().getPlayAllButton().shouldBe(exactText("Play All"));
      challengeEditPage().getCustomerAvatar().shouldBe(visible);
      challengeEditPage().getCustomerText().shouldBe(exactText("Customer"));
      challengeEditPage().getRepAvatar().shouldBe(visible);
      challengeEditPage().getDragButton().shouldBe(visible);
      challengeEditPage().getRepText().shouldBe(exactText("Rep"));
      challengeEditPage().getCustomerLineField().shouldBe(visible);
      challengeEditPage().getGenerateCustomerLineAudioButton()
          .shouldBe(exactText("Generate Audio"));
      challengeEditPage().getRecordCustomerLineButton().shouldBe(exactText("Record Audio"));
      challengeEditPage().getPlayCustomerLineButton().shouldBe(exactText("Play"));
      challengeEditPage().getCustomerLineText().shouldBe(exactText("Customer"));
      challengeEditPage().getRepLineField().shouldBe(exactText("User will respond here"));
      challengeEditPage().getAddCustomerLineButton().shouldBe(visible);
      challengeEditPage().getAddCustomerLineButton().shouldBe(exactText("+ Add a customer line"));

    }
  }

}
