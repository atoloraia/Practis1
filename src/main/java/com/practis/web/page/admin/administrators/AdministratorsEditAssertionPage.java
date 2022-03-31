package com.practis.web.page.admin.administrators;

import static com.practis.web.page.admin.administrators.mapper.AdministratorsMapper.firstNameFromString;
import static com.practis.web.page.admin.administrators.mapper.AdministratorsMapper.lastNameFromString;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.practis.configuration.selenium.support.PractisPage;
import com.practis.dto.NewAdminInput;
import java.util.Locale;

@PractisPage
public class AdministratorsEditAssertionPage extends AdministratorsEditMappingPage {

  /**
   * To be added.
   */
  public void assertPageData(final NewAdminInput input) {
    //assertEquals(input.getFirstName(), firstNameFromString(headerNameElement.getText()));
    assertEquals(input.getFirstName(), firstNameFromString(nameInfoElement.getText()));
    assertEquals(input.getFirstName(), firstNameFieldElement.getAttribute("value"));

    //assertEquals(input.getLastName(), lastNameFromString(headerNameElement.getText()));
    assertEquals(input.getLastName(), lastNameFromString(nameInfoElement.getText()));
    assertEquals(input.getLastName(), lastNameFieldElement.getAttribute("value"));

    final var inputEmail = input.getEmail().toLowerCase(Locale.ROOT);
    assertEquals(inputEmail, emailInfoElement.getText());
    assertEquals(inputEmail, emailFieldElement.getAttribute("value"));
  }
}
