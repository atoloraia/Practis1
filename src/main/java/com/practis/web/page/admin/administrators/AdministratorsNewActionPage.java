package com.practis.web.page.admin.administrators;

import com.practis.configuration.selenium.support.PractisPage;
import com.practis.dto.NewAdminInput;

@PractisPage
public class AdministratorsNewActionPage extends AdministratorsNewPage {

  public void fillFormAndSubmit(final NewAdminInput input) {
    fillRow(0, input);
    submit();
  }

  /**
   * To be added.
   */
  public void fillRow(final int rowNum, final NewAdminInput input) {
    emailFieldElements.get(rowNum).sendKeys(input.getEmail());
    firstNameFieldElements.get(rowNum).sendKeys(input.getFirstName());
    lastNameFieldElements.get(rowNum).sendKeys(input.getLastName());
    passwordFieldElements.get(rowNum).sendKeys(input.getPassword());
  }

  public void submit() {
    createButtonElement.click();
  }

  /**
   * To be added.
   */
  public void showPassword(final int rowNum) {
    if (passwordFieldElements.get(rowNum).getAttribute("type").equals("password")) {
      passwordVisibilityLinkElements.get(rowNum).click();
    }
  }

  /**
   * To be added.
   */
  public void hidePassword(final int rowNum) {
    if (!passwordFieldElements.get(rowNum).getAttribute("type").equals("password")) {
      passwordVisibilityLinkElements.get(rowNum).click();
    }
  }

  /**
   * To be added.
   */
  public void setRowsNumber(final int size) {
    while (inputRowElements.size() != size) {
      addRowLinkElement.click();
      setRowsNumber(size);
    }
  }
}
