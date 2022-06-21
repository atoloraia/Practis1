package com.practis.web.selenide.service.company;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.labelPanel;

import com.codeborne.selenide.CollectionCondition;
import com.practis.dto.NewLabelInput;

public class LabelPanelService {

  /**
   * Create new Label.
   */
  public void createLabel(final NewLabelInput inputData) {
    openPanel();
    clickAddLabel();
    fillLabelInput(inputData.getName());
    saveLabel();
  }

  /**
   * Open Label Panel.
   */
  public void openPanel() {
    labelPanel().getExpandLabelIcon().click();
  }

  /**
   * Click 'Add Label' button.
   */
  public void clickAddLabel() {
    labelPanel().getAddLabelLink().click();
  }

  /**
   * Fill input field.
   */
  public void fillLabelInput(final String value) {
    labelPanel().getLabelInput().sendKeys(value);
  }

  /**
   * Save input data.
   */
  public void saveLabel() {
    labelPanel().getSaveLabelLink().click();
  }

  /**
   * Get Label Name.
   */
  public void checkLabelExists(final String name) {
    labelPanel().getLabelRow().shouldHave(CollectionCondition.anyMatch("labelName",
        element -> name.equalsIgnoreCase($(element).parent().getAttribute("title"))));

  }

}
