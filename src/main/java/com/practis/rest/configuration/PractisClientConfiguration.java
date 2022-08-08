package com.practis.rest.configuration;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.practis.rest.service.PractisApiService.getToken;
import static com.practis.rest.service.PractisApiService.resetToken;
import static com.practis.web.selenide.configuration.model.WebRestConfiguration.webRestConfig;
import static java.lang.String.format;
import static java.util.Objects.isNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practis.rest.client.PractisApiClient;
import feign.Feign;
import feign.Logger.Level;
import feign.RequestInterceptor;
import feign.Response;
import feign.RetryableException;
import feign.Retryer.Default;
import feign.codec.ErrorDecoder;
import feign.form.FormEncoder;
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
        .encoder(new FormEncoder(new JacksonEncoder(objectMapper())))
        .requestInterceptor(headerInterceptor())
        .retryer(tokenRetryer())
        .errorDecoder(new ErrorDecoder.Default() {
          @Override
          public Exception decode(final String methodKey, final Response response) {
            if (response.status() == 401) {
              throw new PractisAuthorizationException(response);
            }
            return super.decode(methodKey, response);
          }
        })
        .logger(new Slf4jLogger(PractisApiClient.class))
        .logLevel(Level.FULL)
        .target(PractisApiClient.class, webRestConfig().getPractisApiUrl());
  }

  private static Default tokenRetryer() {
    return new Default() {
      @Override
      public void continueOrPropagate(final RetryableException e) {
        if (e instanceof PractisAuthorizationException) {
          resetToken();
        }
        super.continueOrPropagate(e);
      }
    };
  }

  private static RequestInterceptor headerInterceptor() {
    return template -> {
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
