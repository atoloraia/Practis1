package com.practis.web.rest.configuration;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.practis.web.rest.service.PractisApiService.getToken;
import static com.practis.web.selenide.configuration.model.WebRestConfiguration.webRestConfig;
import static java.lang.String.format;
import static java.util.Objects.isNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practis.web.rest.client.PractisApiClient;
import feign.Feign;
import feign.Logger.Level;
import feign.RequestInterceptor;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;

public class PractisClientConfiguration {

  private static PractisApiClient PRACTIS_API_CLIENT;

  /**
   * Returns api client.
   */
  public static PractisApiClient practisApiClient() {
    if (isNull(PRACTIS_API_CLIENT)) {
      PRACTIS_API_CLIENT = getPractisApiClient();
    }
    return PRACTIS_API_CLIENT;
  }

  private static PractisApiClient getPractisApiClient() {
    return Feign.builder()
        .decoder(new JacksonDecoder(objectMapper()))
        .encoder(new JacksonEncoder(objectMapper()))
        .requestInterceptor(headerInterceptor())
        .logger(new Slf4jLogger(PractisApiClient.class))
        .logLevel(Level.FULL)
        .target(PractisApiClient.class, webRestConfig().getPractisApiUrl());
  }

  private static RequestInterceptor headerInterceptor() {
    return template -> {
      template.header("content-type", "application/json");
      if (!template.path().equals("/api/auth/login")) {
        template.header("authorization", format("JWT %s", getToken()));
      }
    };
  }

  private static ObjectMapper objectMapper() {
    final var mapper = new ObjectMapper();
    mapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    return mapper;
  }
}
