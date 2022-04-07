package com.practis.configuration.extension;

import static com.codeborne.selenide.Selenide.closeWebDriver;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class CloseBrowserExtension implements AfterEachCallback {

  @Override
  public void afterEach(final ExtensionContext context) {
    closeWebDriver();
  }
}
