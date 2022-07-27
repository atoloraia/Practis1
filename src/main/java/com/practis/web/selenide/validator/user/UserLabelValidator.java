package com.practis.web.selenide.validator.user;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.labelModule;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.labelService;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;

public class UserLabelValidator {

  /**
   * Assert created label.
   */
  public static void assertCreatedLabel(final String label) {
    labelService().findLabelCheckbox(label).shouldBe(visible);
    labelService().findSelectedLabelCheckbox(label).has(Condition.attribute("checked"));
  }


  /**
   * Assert Label model.
   */
  public static void assertLabelModel() {
    labelModule().getSearchField().shouldBe(visible);
    labelModule().getNoSelectedText().shouldBe(visible);
    labelModule().getNoSelectedText().shouldBe(exactText("No Labels selected"));
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

}
