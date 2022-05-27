package com.practis.web.selenide.validator;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.text;
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
}
