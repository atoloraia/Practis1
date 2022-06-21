package com.practis.web.selenide.validator;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.challengeCreatePage;
import static com.practis.web.selenide.configuration.PageObjectFactory.challengeEditPage;

import com.practis.dto.NewChallengeInput;
import com.practis.dto.NewCompanyInput;
import com.practis.web.selenide.component.GridRow;
import com.practis.web.selenide.page.admin.CompanyEditPage;
import com.practis.web.selenide.page.company.ChallengeEditPage;

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
    challengeEditPage.getTitleChallenge().shouldBe(attribute("value", inputData.getTitle()));
    challengeEditPage.getDescriptionChallenge().shouldBe(text(inputData.getDescription()));
  }

  /**
   * Assert Title on edit page with input.
   */
  public static void assertChallengeTitle(final NewChallengeInput inputData,
      final ChallengeEditPage challengeEditPage) {
    challengeEditPage.getTitleChallenge().shouldBe(attribute("value", inputData.getTitle()));
  }

  /**
   * Assert elements on New Challenge page.
   */
  public static void assertElementsOnNewChallengePage() {
    challengeCreatePage().getAddNewChallengeTitle().shouldBe(exactText("Add New Challenge"));
    challengeCreatePage().getNotPublishTitle().shouldBe(exactText("Not Published Yet"));

    challengeCreatePage().getSaveAsDraftButton().shouldBe(visible);
    challengeCreatePage().getPublishButton().shouldBe(visible);

    challengeCreatePage().getTitleField().shouldBe(visible);
    challengeCreatePage().getTitleField()
        .shouldBe(attribute("placeholder", "Challenge Title"));

    challengeCreatePage().getAddLabels().shouldBe(visible);

    challengeCreatePage().getDescriptionField().shouldBe(visible);
    challengeCreatePage().getDescriptionField().shouldBe(attribute("placeholder", "Description"));

    challengeCreatePage().getGenerateForAllButton().shouldBe(visible);
    challengeCreatePage().getGenerateForAllButton()
    .shouldBe(attribute("title", "Generate for All"));

    challengeCreatePage().getPlayForAllButton().shouldBe(visible);
    challengeCreatePage().getPlayForAllButton().shouldBe(exactText("Play All"));

    challengeCreatePage().getCustomerTitle().get(0).shouldBe(visible);
    challengeCreatePage().getCustomerTitle().get(0).shouldBe(exactText("Customer"));

    challengeCreatePage().getRepTitle().get(1).shouldBe(visible);
    challengeCreatePage().getRepTitle().get(1).shouldBe(exactText("Rep"));

    challengeCreatePage().getCustomerLine().shouldBe(visible);
    challengeCreatePage().getCustomerLine()
        .shouldBe(attribute("placeholder", "Write customer’s line here"));
    challengeCreatePage().getDeleteCustomerLine().get(0).shouldBe(visible);
    challengeCreatePage().getRecordAudioButton().get(0).shouldBe(visible);

    challengeCreatePage().getUserWillRespondHereLine().get(1)
        .shouldBe(exactText("User will respond here"));
    challengeCreatePage().getAddCustomerLineButton().shouldBe(visible);
  }
}
