package com.practis.rest.configuration;

import static java.lang.System.currentTimeMillis;

import feign.Response;
import feign.RetryableException;
import java.util.Date;

public class PractisAuthorizationException extends RetryableException {

  /**
   * Custom exception constructor.
   */
  public PractisAuthorizationException(final Response response) {
    super(response.status(), "practis auth retry", response.request().httpMethod(),
        new RuntimeException("authentication failed"), new Date(currentTimeMillis()),
        response.request());
  }
}
