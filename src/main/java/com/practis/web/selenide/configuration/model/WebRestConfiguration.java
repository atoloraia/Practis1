package com.practis.web.selenide.configuration.model;

import static com.practis.utils.ConfigurationLoader.loadConfig;
import static java.util.Objects.isNull;

import lombok.Value;

@Value
public class WebRestConfiguration {

  private static WebRestConfiguration INSTANCE;

  /**
   * Initialize WebApplicationConfiguration.
   */
  public static WebRestConfiguration webRestConfig() {
    if (isNull(INSTANCE)) {
      INSTANCE = loadConfig(
          "/configuration/web/rest.json", WebRestConfiguration.class);
    }
    return INSTANCE;
  }

  String analyticsApiUrl;
  String practisApiUrl;
  String googleApiUrl;
}
