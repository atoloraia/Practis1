package com.practis.selenide.admin;

import static com.codeborne.selenide.Selenide.open;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.loginService;
import static com.practis.web.selenide.configuration.model.WebApplicationConfiguration.webApplicationConfig;
import static com.practis.web.selenide.configuration.model.WebCredentialsConfiguration.webCredentialsConfig;
import static com.practis.web.selenide.validator.AdminValidator.assertElementsOnAdminPage;

import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.web.selenide.configuration.model.WebCredentialsConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SelenideTestClass
@TestRailTestClass
class AdminsListTest {

  private final WebCredentialsConfiguration credentials = webCredentialsConfig();

  @BeforeEach
  void beforeEach() {
    open(webApplicationConfig().getUrl());
  }


  /**
   * Check Web elements on Admins page.
   */
  @Test
  @TestRailTest(caseId = 11671)
  @DisplayName("Check Web Elements on Admins list")

  void assertWebElementsOnAdminsPage() {
    loginService().fillFormAndLogin(credentials.getLogin(), credentials.getPassword());
    assertElementsOnAdminPage();
  }
}
