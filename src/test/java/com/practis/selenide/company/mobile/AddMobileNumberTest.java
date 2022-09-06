package com.practis.selenide.company.mobile;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.addMobileService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.loginService;
import static com.practis.web.selenide.configuration.model.WebApplicationConfiguration.webApplicationConfig;
import static com.practis.web.selenide.configuration.model.WebCredentialsConfiguration.webCredentialsConfig;
import static com.practis.web.selenide.validator.AddMobileNumberValidator.assertElementsOnAddMobilePage;

import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.web.selenide.configuration.model.WebCredentialsConfiguration;
import org.junit.jupiter.api.DisplayName;

@SelenideTestClass
@TestRailTestClass
public class AddMobileNumberTest {

  private final WebCredentialsConfiguration credentials = webCredentialsConfig();

  /**
   * Add Mobile Number: Check WEB Elements 'Login' page.
   */
  @TestRailTest(caseId = 4515)
  @DisplayName("Check Elements on 'Add Mobile Number' page")
  void checkElementsAddMobilePage() {
    open(webApplicationConfig().getUrl());

    loginService().fillFormAndLogin(credentials.getLogin(), credentials.getPassword());
    assertElementsOnAddMobilePage();
  }

}
