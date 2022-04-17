package com.practis.web.selenide.configuration.model;

import static com.practis.utils.ConfigurationLoader.loadConfig;
import static java.util.Objects.isNull;

import lombok.Value;

@Value
public class WebApplicationConfiguration {

  private static WebApplicationConfiguration INSTANCE;

  /**
   * Initialize WebApplicationConfiguration.
   */
  public static WebApplicationConfiguration webApplicationConfig() {
    if (isNull(INSTANCE)) {
      INSTANCE = loadConfig(
          "/configuration/web/application.json", WebApplicationConfiguration.class);
    }
    return INSTANCE;
  }

  String url;
  String adminUrl;
  String browser;
  String adminCompanyName;
  String automationCompanyName;
}
