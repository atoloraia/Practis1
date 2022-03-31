package com.practis.web.page.admin.administrators;

import static org.openqa.selenium.By.cssSelector;

import com.practis.configuration.selenium.support.PractisPage;

@PractisPage
public class AdministratorsNewMappingPage extends AdministratorsNewActionPage {

  public String getPasswordValidationMessage() {
    return getPasswordValidationMessage(0);
  }

  /**
   * To be added.
   */
  public String getPasswordValidationMessage(final int rowNum) {
    return inputRowElements.get(rowNum)
        .findElements(cssSelector(getInputRowColumnSelector()))
        .get(3)
        .findElement(cssSelector(getErrorMessageSelector()))
        .getText();
  }
}
