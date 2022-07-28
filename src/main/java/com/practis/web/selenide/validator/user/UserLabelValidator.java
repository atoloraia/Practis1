package com.practis.web.selenide.validator.user;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.labelModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.teamModule;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.labelService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamService;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.CollectionCondition;

public class UserLabelValidator {

  /**
   * Assert created label.
   */
  public static void assertCreatedLabel(final String label) {
    labelService().findLabelCheckbox(label).shouldBe(visible);
    labelModule().getLabelRows().shouldBe(CollectionCondition.size(1));
    //labelService().findSelectedLabelCheckbox(label).has(Condition.attribute("checked"));
  }


  /**
   * Assert Label model.
   */
  public static void assertLabelModel() {
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
    labelModule().getNoLabelsText().shouldBe(visible);
    labelModule().getNoLabelsText().shouldBe(exactText("No Labels added yet"));
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

}
