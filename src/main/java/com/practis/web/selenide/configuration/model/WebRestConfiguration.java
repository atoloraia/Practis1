package com.practis.web.selenide.configuration.model;

import static com.practis.utils.ConfigurationLoader.loadConfig;
import static java.lang.System.getenv;
import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter(AccessLevel.PRIVATE)
public class WebRestConfiguration {

  private static WebRestConfiguration INSTANCE;

  /**
   * Initialize WebApplicationConfiguration.
   */
  public static WebRestConfiguration webRestConfig() {
    if (isNull(INSTANCE)) {
      INSTANCE = loadConfig(
          "/configuration/web/rest.json", WebRestConfiguration.class);
      ofNullable(getenv("WEB_REST_PRACTIS_URL"))
          .filter(StringUtils::isNotEmpty)
          .ifPresent(value -> INSTANCE.setPractisApiUrl(value));

      ofNullable(getenv("WEB_REST_PRACTIS_V2_URL"))
          .filter(StringUtils::isNotEmpty)
          .ifPresent(value -> INSTANCE.setPractisApiV2Url(value));
    }
    return INSTANCE;
  }

  String analyticsApiUrl;
  String practisApiUrl;
  String practisApiV2Url;
  String googleApiUrl;
}
