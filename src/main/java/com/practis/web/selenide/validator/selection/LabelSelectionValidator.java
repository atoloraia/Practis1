package com.practis.web.selenide.validator.selection;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.labelModule;
import static com.practis.web.selenide.configuration.PageObjectFactory.userProfilePage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.labelService;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertSelectedTeam;
import static com.practis.web.selenide.validator.user.UserProfileValidator.assertUserData;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.practis.dto.NewUserInput;

public class LabelSelectionValidator {

  /**
   * Assert created label.
   */
  public static void assertCreatedLabel(final String label) {
    await().pollDelay(FIVE_SECONDS).until(() -> true);
    labelService().findLabelCheckbox(label).shouldBe(visible);
    labelModule().getLabelRows().shouldBe(CollectionCondition.size(1));
  }

  /**
   * Assert created label.
   */
  public static void assertDisabledApplyButton() {
    labelModule().getApplyButton().shouldBe(disabled);
  }

  /**
   * Assert Label model.
   */
  public static void assertLabelSelection() {
    labelModule().getSearchField().shouldBe(visible);
    labelModule().getSelectedText().shouldBe(visible);
    labelModule().getSelectedText().shouldBe(exactText("No Labels selected"));
    labelModule().getSelectedAllButton().shouldBe(visible);
    labelModule().getSelectedAllButton().shouldBe(exactText("Select All"));
    labelModule().getCancelButton().shouldBe(visible);
    labelModule().getApplyButton().shouldBe(visible);
    labelModule().getLabelRows().shouldBe(CollectionCondition.size(0));
  }

  /**
   * Assert empty Label model.
   */
  public static void assertEmptyLabelModel() {
    labelModule().getLabelTitle().shouldBe(visible);
    labelModule().getLabelTitle().shouldBe(matchText("Labels"));
    labelModule().getLabelRows().shouldBe(CollectionCondition.size(0));
    labelModule().getSearchField().shouldBe(visible);
    labelModule().getSearchField().shouldBe(attribute("font-size", "13px"));
    labelModule().getSearchField().shouldBe(attribute("disabled", "true"));
    labelModule().getSearchField().shouldBe(attribute("type", "text"));
    labelModule().getSearchFieldIcon().shouldBe(visible);
    labelModule().getNoLabelsAddedIcon().shouldBe(visible);
    labelModule().getNoLabelsAddedText().shouldBe(exactText("No Labels yet"));
  }

  /**
   * Assert No Labels Yet.
   */
  public static void assertNoLabelsYet() {
    labelModule().getNoLabelsYetTooltip().shouldBe(visible);
    labelModule().getNoLabelsYetTooltip().shouldBe(exactText("No labels added yet"));
  }

  /**
   * Assert no search results.
   */
  public static void assertNoLabelSearchResult() {
    await().pollDelay(TWO_SECONDS).until(() -> true);
    labelModule().getNoSearchResultText().shouldBe(visible);
    labelModule().getNoSearchResultImage().shouldBe(visible);
    labelModule().getSelectedText().shouldBe(visible);
    labelModule().getSelectedAllButton().shouldBe(visible);
    labelModule().getLabelRows().shouldBe(CollectionCondition.size(0));
  }

  /**
   * Assert search results.
   */
  public static void assertLabelSearchResult(final String label) {
    await().pollDelay(TWO_SECONDS).until(() -> true);
    labelService().findLabelCheckbox(label).shouldBe(visible);
    labelModule().getLabelRows().shouldBe(CollectionCondition.size(1));
  }

  /**
   * Assert Select All.
   */
  public static void assertSelectedAllLabels() {
    labelModule().getLabelCheckbox().shouldBe(CollectionCondition.allMatch("checked",
        element -> Selenide.$(element).has(Condition.attribute("checked"))));
    labelModule().getSelectedText().shouldBe(matchText("1 Label selected"));
    labelModule().getApplyButton().shouldBe(enabled);
  }

  /**
   * Assert Unselect All.
   */
  public static void assertUnSelectAllLabels() {
    labelModule().getLabelCheckbox().should(CollectionCondition.allMatch("checked",
        element -> !Selenide.$(element).has(Condition.attribute("checked"))));
    labelModule().getSelectedText().shouldBe(exactText("No Labels selected"));
  }

  /**
   * Assert the Label is selected.
   */
  public static void assertSelectedLabel(final String label) {
    labelService().findLabelCheckbox(label).shouldBe(visible);
    labelService().findSelectedLabelCheckbox(label).has(Condition.attribute("checked"));
  }

}
