package com.practis.web.selenide.service.company;

import static com.codeborne.selenide.Selenide.$;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.labelPanel;
import static com.practis.web.util.SelenidePageLoadAwait.awaitFullPageLoad;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Selenide;
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
    await().pollDelay(TWO_SECONDS).until(() -> true);
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
    labelPanel().getSaveButton().click();
  }

  /**
   * Get Label Name.
   */
  public void checkLabelExists(final String name) {
    awaitFullPageLoad(10);
    Selenide.refresh();
    labelPanel().getExpandLabelIcon().click();
    labelPanel().getLabelRow().shouldHave(CollectionCondition.anyMatch("labelName",
        element -> name.equalsIgnoreCase($(element).parent().getAttribute("title"))));

  }

}
