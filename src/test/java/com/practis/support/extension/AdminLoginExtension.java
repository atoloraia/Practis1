package com.practis.support.extension;

import static com.codeborne.selenide.Selenide.localStorage;
import static com.codeborne.selenide.Selenide.open;
import static com.practis.web.rest.service.PractisApiService.getToken;
import static com.practis.web.rest.service.PractisApiService.setCampaign;
import static com.practis.web.selenide.configuration.model.WebApplicationConfiguration.webApplicationConfig;
import static com.practis.web.selenide.configuration.model.WebCredentialsConfiguration.webCredentialsConfig;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class AdminLoginExtension implements BeforeEachCallback {

  @Override
  public void beforeEach(final ExtensionContext context) throws Exception {
    setCampaign(webCredentialsConfig().getId());

    open("view-source:" + webApplicationConfig().getAdminUrl());

    localStorage().setItem("token", getToken());
    localStorage().setItem("analyticsToken", getToken());

    open(webApplicationConfig().getAdminUrl());
  }
}
