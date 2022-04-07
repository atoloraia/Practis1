package com.practis.web.selenide.configuration.model;

import static com.practis.utils.ConfigurationLoader.loadConfig;
import static java.util.Objects.isNull;

import lombok.Value;

@Value
public class WebCredentialsConfiguration {

  private static WebCredentialsConfiguration INSTANCE;

  /**
   * Initialize WebCredentialsConfiguration.
   */
  public static WebCredentialsConfiguration webCredentialsConfig() {
    if (isNull(INSTANCE)) {
      INSTANCE = loadConfig(
          "/configuration/web/credentials.json", WebCredentialsConfiguration.class);
    }
    return INSTANCE;
  }

  String login;
  String password;
}
