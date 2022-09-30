package com.practis.web.selenide.validator.selection;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.labelModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.teamModule;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.labelService;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

public class LabelSelectionValidator {

  /**
   * Assert search on Label model.
   */
  public static void assertSearchElementsOnLabelsModal() {
    await().pollDelay(TWO_SECONDS).until(() -> true);
    labelModule().getSearchField().shouldBe(visible);
    labelModule().getSearchField().shouldBe(attribute("font-size", "13px"));
    labelModule().getSearchField().shouldBe(enabled);
    labelModule().getSearchField().shouldBe(attribute("type", "text"));
    labelModule().getSearchFieldIcon().shouldBe(visible);
    labelModule().getCleanSearchIcon().shouldNotBe(visible);
  }

  /**
   * Assert Search should be performed after entering 1 characters.
   */
  public static void assertLabelSearchingAfter1Char(final String searchString) {
    final var input = searchString.charAt(searchString.length() - 1);
    labelModule().getSearchField().append(String.valueOf(input));
    labelModule().getCleanSearchIcon().shouldBe(Condition.visible);
    labelModule().getLabelRows().get(0).shouldBe(visible);
  }

  /**
   * Assert clean search on Label model.
   */
  public static void assertCleanLabelSearch(int row) {
    await().pollDelay(TWO_SECONDS).until(() -> true);
    labelModule().getCleanSearchIcon().shouldNotBe(visible);
    labelModule().getLabelRows().shouldHave(CollectionCondition.size(row));
    labelModule().getSearchField().append("check clean icon");
    labelModule().getCleanSearchIcon().shouldBe(visible);
    labelModule().getLabelRows().shouldHave(CollectionCondition.size(0));
    labelModule().getCleanSearchIcon().click();
    labelModule().getCleanSearchIcon().shouldNotBe(visible);
    labelModule().getLabelRows().shouldHave(CollectionCondition.size(row));
  }

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
    await().pollDelay(TWO_SECONDS).until(() -> true);
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
