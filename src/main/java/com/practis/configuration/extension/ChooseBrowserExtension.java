package com.practis.configuration.extension;

import static com.codeborne.selenide.Configuration.browser;
import static com.practis.web.selenide.configuration.model.WebApplicationConfiguration.webApplicationConfig;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class ChooseBrowserExtension implements BeforeAllCallback {

  @Override
  public void beforeAll(final ExtensionContext context) {
    browser = webApplicationConfig().getBrowser();
  }
}
