package com.practis.support.extension;

import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Configuration.fastSetValue;
import static com.codeborne.selenide.Configuration.remote;
import static com.codeborne.selenide.FileDownloadMode.PROXY;
import static com.practis.utils.EnvironmentUtils.isRunOnContinuousIntegration;
import static com.practis.web.selenide.configuration.model.WebApplicationConfiguration.webApplicationConfig;
import static java.util.Optional.ofNullable;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class ChooseBrowserExtension implements BeforeAllCallback {

  @Override
  public void beforeAll(final ExtensionContext context) {
    fastSetValue = true;

    if (isRunOnContinuousIntegration()) {
      ofNullable(System.getenv("BROWSER_URL"))
          .ifPresentOrElse(url -> remote = url,
              () -> remote = "http://localhost:4444");
      System.out.println("run tests on CI env. Url: " + remote);
    }

    Configuration.proxyEnabled = true;
    Configuration.fileDownload = PROXY;
    Configuration.downloadsFolder = "build/selenide/download";
    Configuration.timeout = 10000;

    browser = webApplicationConfig().getBrowser();
  }
}
