package com.practis.web.selenide.configuration.model;

import static com.practis.utils.ConfigurationLoader.loadConfig;
import static java.lang.Integer.valueOf;
import static java.lang.System.getenv;
import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
public class WebCredentialsConfiguration {

  private static WebCredentialsConfiguration INSTANCE;

  /**
   * Initialize WebCredentialsConfiguration.
   */
  public static WebCredentialsConfiguration webCredentialsConfig() {
    if (isNull(INSTANCE)) {
      INSTANCE = loadConfig(
          "/configuration/web/credentials.json", WebCredentialsConfiguration.class);
      ofNullable(getenv("WEB_USER_ID")).ifPresent(value -> INSTANCE.setId(valueOf(value)));
      ofNullable(getenv("WEB_USER_LOGIN")).ifPresent(value -> INSTANCE.setLogin(value));
      ofNullable(getenv("WEB_USER_PASSWORD")).ifPresent(value -> INSTANCE.setPassword(value));
    }
    return INSTANCE;
  }

  Integer id;
  String login;
  String password;
}
