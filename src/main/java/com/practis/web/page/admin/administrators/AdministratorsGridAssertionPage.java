package com.practis.web.page.admin.administrators;

import static java.util.Locale.ROOT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.practis.configuration.selenium.support.PractisPage;
import com.practis.dto.NewAdminInput;
import com.practis.dto.admin.Administrator;
import java.time.LocalDate;
import java.time.LocalTime;

@PractisPage
public class AdministratorsGridAssertionPage extends AdministratorsGridMappingPage {

  /**
   * To be added.
   */
  public void assertEqual(final NewAdminInput input, final Administrator administrator) {
    assertEquals(input.getEmail().toLowerCase(ROOT), administrator.getEmail());
    assertEquals(input.getFirstName(), administrator.getFirstName());
    assertEquals(input.getLastName(), administrator.getLastName());
    //todo add admin name
    //assertEquals(properties.getAdminCompanyName(), administrator.getOwner());
    assertEquals(LocalDate.now(), administrator.getDateCreated().toLocalDate());
    assertTrue(LocalTime.now().minusMinutes(1)
        .isBefore(administrator.getDateCreated().toLocalTime()));
  }

  /**
   * To be added.
   */
  public void assertNotEqual(final NewAdminInput input, final Administrator administrator) {
    assertNotEquals(input.getFirstName(), administrator.getFirstName());
    assertNotEquals(input.getLastName(), administrator.getLastName());
  }
}
