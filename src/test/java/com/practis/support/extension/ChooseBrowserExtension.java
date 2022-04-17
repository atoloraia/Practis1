package com.practis.support.extension;

import static com.codeborne.selenide.Configuration.browser;
import static com.practis.web.selenide.configuration.model.WebApplicationConfiguration.webApplicationConfig;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class ChooseBrowserExtension implements BeforeAllCallback {

  @Override
  public void beforeAll(final ExtensionContext context) {
    Configuration.fastSetValue = true;
    browser = webApplicationConfig().getBrowser();
  }
}
