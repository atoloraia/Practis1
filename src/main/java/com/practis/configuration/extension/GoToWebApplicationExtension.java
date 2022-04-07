package com.practis.configuration.extension;

import static com.codeborne.selenide.Selenide.open;
import static com.practis.web.selenide.configuration.model.WebApplicationConfiguration.webApplicationConfig;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class GoToWebApplicationExtension implements BeforeEachCallback {

  @Override
  public void beforeEach(final ExtensionContext context) throws Exception {
    open(webApplicationConfig().getUrl());
  }
}
