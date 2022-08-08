package com.practis.rest.configuration;

import static java.util.Date.from;

import com.codepine.api.testrail.model.Run;
import feign.Request;
import feign.Request.HttpMethod;
import feign.Response;
import feign.RetryableException;
import java.time.Instant;
import java.util.Date;

public class PractisAuthorizationException extends RetryableException {

  public PractisAuthorizationException(final Response response) {
    super(response.status(), "practis auth retry", response.request().httpMethod(),
        new RuntimeException("authentication failed"), from(Instant.now()), response.request());
  }
}
