package com.practis.web.selenide.validator.selection;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.labelModule;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.labelService;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

public class LabelSelectionValidator {

  /**
   * Assert created label.
   */
  public static void assertCreatedLabel(final String label) {
    await().pollDelay(TWO_SECONDS).until(() -> true);
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
    labelModule().getLabelRows().shouldBe(CollectionCondition.size(0));
    labelModule().getSearchField().shouldBe(visible);
    labelModule().getSearchFieldIcon().shouldBe(visible);
    labelModule().getNoLabelsAddedIcon().shouldBe(visible);
    labelModule().getNoLabelsAddedText().shouldBe(exactText("No Labels yet"));
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



}
