package com.practis.web;

import static com.practis.utils.AwaitUtils.awaitSeconds;

import com.practis.configuration.web.properties.WebApplicationProperties;
import com.practis.web.component.CompanySelectorComponent;
import com.practis.web.page.HomePage;
import com.practis.web.page.LoginPage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Getter
@Component
@Slf4j
public class WebApplication {

  private final WebDriver driver;
  private final WebApplicationProperties properties;

  private final HomePage homePage;
  private final LoginPage loginPage;

  private final CompanySelectorComponent companySelector;

  /**
   * To be added.
   */
  public void init() {
    driver.get(properties.getUrl());
  }

  /**
   * To be added.
   */
  public void initLogin() {
    init();
    homePage.login();
  }

  /**
   * To be added.
   */
  public void initAdmin() {
    login();

    if (!isCompanySelected(properties.getAdminCompanyName())) {
      companySelector.selectCompany(properties.getAdminCompanyName());
    }
    awaitSeconds(10, () -> isCompanySelected(properties.getAdminCompanyName()));
  }

  /**
   * To be added.
   */
  public void initAutomationCompany() {
    login();

    if (!isCompanySelected(properties.getAutomationCompanyName())) {
      companySelector.selectCompany(properties.getAutomationCompanyName());
    }
    awaitSeconds(10, () -> isCompanySelected(properties.getAutomationCompanyName()));
  }

  private void login() {
    initLogin();
    loginPage.login(properties.getAdminCredentials());
  }

  public void close() {
    driver.close();
  }

  private boolean isCompanySelected(final String expected) {
    return companySelector.getSelected().equals(expected);
  }
}
