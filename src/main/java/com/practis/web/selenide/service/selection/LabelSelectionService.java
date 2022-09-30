package com.practis.web.selenide.service.selection;

import static com.codeborne.selenide.Condition.attributeMatching;
import static com.codeborne.selenide.Selenide.$;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.labelModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.teamModule;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.labelService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamService;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.ONE_SECOND;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.practis.web.selenide.service.company.InviteUserService;
import java.util.function.Predicate;
import org.openqa.selenium.WebElement;

public class LabelSelectionService {

  /**
   * Find label checkbox.
   */
  public SelenideElement findLabelCheckbox(final String label) {
    final var labelRow = labelModule().getLabelRows().find(Condition.match("child attribute value",
        element -> {
          final var input = $(element).find(String.format("input[value='%s']", label));
          return input.exists();
        }));
    final var checkbox = labelRow.$("[data-test='label-item-checkbox-view']");
    return checkbox;
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

  /**
   * Select label.
   */
  public InviteUserService selectLabel(final String label) {
    await().pollDelay(ONE_SECOND).until(() -> true);
    labelService().findLabelCheckbox(label).click();
    return null;
  }

}
