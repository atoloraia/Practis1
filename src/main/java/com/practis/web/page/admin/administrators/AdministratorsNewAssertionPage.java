package com.practis.web.page.admin.administrators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.practis.configuration.selenium.support.PractisPage;

@PractisPage
public class AdministratorsNewAssertionPage extends AdministratorsNewMappingPage {

  public void assertCreateButtonDisabled() {
    assertNotNull(createButtonElement.getAttribute("disabled"),
        "Create button expected to be disabled");
  }

  public void assertPasswordValidationMessage() {
    assertEquals("Password must be at least 8 characters long.", getPasswordValidationMessage());
  }

  public void assertPasswordVisible(final int row) {
    assertEquals("text", passwordFieldElements.get(row).getAttribute("type"));
  }

  public void assertPasswordNotVisible(final int row) {
    assertEquals("password", passwordFieldElements.get(row).getAttribute("type"));
  }
}
