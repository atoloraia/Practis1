package com.practis.selenide.admin;

import static com.codeborne.selenide.Selenide.open;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.companySelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.loginService;
import static com.practis.web.selenide.configuration.model.WebApplicationConfiguration.webApplicationConfig;
import static com.practis.web.selenide.configuration.model.WebCredentialsConfiguration.webCredentialsConfig;
import static com.practis.web.selenide.validator.CurrentUserAdminValidator.assertElementsOnCurrentAdminValidator;
import static com.practis.web.selenide.validator.CurrentUserAdminValidator.assertElementsOnCurrentAdminValidatorCompany;
import static com.practis.web.util.AwaitUtils.awaitElementNotExists;

import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.web.selenide.configuration.model.WebCredentialsConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SelenideTestClass
@TestRailTestClass
class CurrentUserAdminTest {

  private final WebCredentialsConfiguration credentials = webCredentialsConfig();

  @BeforeEach
  void beforeEach() {
    open(webApplicationConfig().getUrl());
  }


  /**
   * Check Current User Setting view.
   */
  @Test
  @TestRailTest(caseId = 9522)
  @DisplayName("Check Web Elements on Current User Settings view")

  void assertWebElementsOnCompaniesPage() {
    loginService().fillFormAndLogin(credentials.getLogin(), credentials.getPassword());
    assertElementsOnCurrentAdminValidator();
  }
}
