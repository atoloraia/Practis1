package com.practis.support.extension;

import static com.codeborne.selenide.Selenide.localStorage;
import static com.codeborne.selenide.Selenide.open;
import static com.practis.rest.service.PractisApiService.getToken;
import static com.practis.rest.service.PractisApiService.resetToken;
import static com.practis.rest.service.PractisApiService.setAdminCompany;
import static com.practis.web.selenide.configuration.model.WebApplicationConfiguration.webApplicationConfig;
import static com.practis.web.selenide.configuration.model.WebCredentialsConfiguration.webCredentialsConfig;
import static com.practis.web.util.SelenidePageUtil.openPage;

import com.practis.web.util.SelenidePageUtil;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class AdminLoginExtension implements BeforeEachCallback {

  @Override
  public void beforeEach(final ExtensionContext context) throws Exception {
    setAdminCompany(webCredentialsConfig().getId());

    open("view-source:" + webApplicationConfig().getAdminUrl());

    localStorage().setItem("token", getToken());
    localStorage().setItem("userId", webCredentialsConfig().getId().toString());

    openPage(webApplicationConfig().getAdminUrl());
  }
}
