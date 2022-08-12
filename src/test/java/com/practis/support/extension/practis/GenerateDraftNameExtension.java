package com.practis.support.extension.practis;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class GenerateDraftNameExtension
    implements BeforeEachCallback, AfterEachCallback, ParameterResolver {

  private String draftName;

  @Override
  public boolean supportsParameter(final ParameterContext parameterContext,
      final ExtensionContext extensionContext) throws ParameterResolutionException {
    return parameterContext.getParameter().getType()
        .equals(String.class);
  }

  @Override
  public Object resolveParameter(final ParameterContext parameterContext,
      final ExtensionContext extensionContext) throws ParameterResolutionException {
    return draftName;
  }

  @Override
  public void afterEach(final ExtensionContext context) throws Exception {
    practisApi().deleteDraftUser(draftName);
  }

  @Override
  public void beforeEach(final ExtensionContext context) throws Exception {
    draftName = String.format("Draft %s", timestamp());
  }
}
