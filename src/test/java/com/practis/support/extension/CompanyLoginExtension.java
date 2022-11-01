package com.practis.support.extension;

import static com.codeborne.selenide.Selenide.localStorage;
import static com.codeborne.selenide.Selenide.open;
import static com.practis.rest.service.PractisApiService.getToken;
import static com.practis.rest.service.PractisApiService.resetToken;
import static com.practis.rest.service.PractisApiService.setCompany;
import static com.practis.web.selenide.configuration.model.WebApplicationConfiguration.webApplicationConfig;
import static com.practis.web.selenide.configuration.model.WebCredentialsConfiguration.webCredentialsConfig;
import static com.practis.web.util.SelenidePageLoadAwait.awaitFullPageLoad;

import com.practis.rest.service.PractisApiService;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class CompanyLoginExtension implements BeforeEachCallback {

  @Override
  public void beforeEach(final ExtensionContext context) throws Exception {
    setCompany(webCredentialsConfig().getId(), webApplicationConfig().getAutomationCompanyName());

    final var homePage = webApplicationConfig().getUrl() + "/teams";
    open(homePage);

    localStorage().setItem("token", getToken());
    localStorage().setItem("analyticsToken", getToken());

    open(homePage);

    //todo check if it works without await
    awaitFullPageLoad(10);
  }
}
