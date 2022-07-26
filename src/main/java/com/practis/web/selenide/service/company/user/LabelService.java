package com.practis.web.selenide.service.company.user;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.labelModule;

import com.codeborne.selenide.CheckResult;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Driver;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import javax.annotation.Nonnull;
import org.openqa.selenium.WebElement;

public class LabelService {

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

}
