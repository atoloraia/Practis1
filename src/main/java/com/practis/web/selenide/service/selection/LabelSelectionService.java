package com.practis.web.selenide.service.selection;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.labelModule;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class LabelSelectionService {

  /**
   * Find label checkbox.
   */
  public SelenideElement findLabelCheckbox(final String label) {
    return labelModule().getLabelRows().get(0)
        .parent().parent().$("input[value='" + label + "']");
  }

  /**
   * Find selected label checkbox.
   */
  public SelenideElement findSelectedLabelCheckbox(final String label) {
    final var labelRow = labelModule().getLabelRows().find(Condition.matchText(label));
    return labelRow.$("[data-test='label-item-checkbox']");
  }

  /**
   * Search Label.
   */
  public void searchLabel(final String input) {
    labelModule().getSearchField().setValue(input.substring(0, input.length() - 1));
    labelModule().getSearchField().append(input.substring(input.length() - 1));
  }

  /**
   * Select All Labels.
   */
  public void selectAllLabels() {
    await().pollDelay(TWO_SECONDS).until(() -> true);
    labelModule().getSelectedAllButton().click();
  }

  /**
   * Unselect All Labels.
   */
  public void unSelectAllLabels() {
    await().pollDelay(TWO_SECONDS).until(() -> true);
    labelModule().getUnSelectedAllButton().click();
  }

}
