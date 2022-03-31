package com.practis.web.page.admin.administrators;

import static com.practis.web.page.admin.administrators.mapper.AdministratorsMapper.fromGridRow;

import com.practis.configuration.selenium.support.PractisPage;
import com.practis.dto.admin.Administrator;
import org.openqa.selenium.WebElement;

@PractisPage
public class AdministratorsGridActionPage extends AdministratorsGridPage {

  /**
   * To be added.
   */
  public void goTo(final Administrator createdAdministrator) {
    administratorsGridRowElements.stream()
        .filter(row -> fromGridRow(row, getGridRowColumnSelector()).equals(createdAdministrator))
        .findFirst()
        .ifPresent(WebElement::click);
  }
}
