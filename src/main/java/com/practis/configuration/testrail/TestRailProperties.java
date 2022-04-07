package com.practis.configuration.testrail;

import static com.practis.utils.ConfigurationLoader.loadConfig;
import static java.util.Objects.isNull;

import lombok.Value;

@Value
public class TestRailProperties {

  private static TestRailProperties INSTANCE;

  /**
   * Initialize WebApplicationConfiguration.
   */
  public static TestRailProperties testRailConfig() {
    if (isNull(INSTANCE)) {
      INSTANCE = loadConfig(
          "/configuration/testrail.json", TestRailProperties.class);
    }
    return INSTANCE;
  }

  String url;
  String user;
  String password;
  String project;
}
