package com.practis.support.extension.practis;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static java.lang.String.format;

import com.practis.rest.dto.company.RestTeamResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class CreateTeamExtension implements
    BeforeEachCallback, AfterEachCallback, ParameterResolver {

  private final List<RestTeamResponse> teamsToRemove = new ArrayList<>();

  @Override
  public void beforeEach(final ExtensionContext context) throws Exception {
    final var annotation = context.getTestMethod().orElseThrow()
        .getAnnotation(TeamExtension.class);
    IntStream.range(0, annotation.count()).forEach(idx -> {
      final var team = practisApi().createTeam(String.format("test-%s", timestamp()));
      teamsToRemove.add(team);
    });

  }

  @Override
  public void afterEach(final ExtensionContext context) throws Exception {
    teamsToRemove.forEach(team -> practisApi().deleteTeam(team.getName()));
  }

  @Override
  public boolean supportsParameter(final ParameterContext parameterContext,
      final ExtensionContext extensionContext) throws ParameterResolutionException {
    return parameterContext.getParameter().getParameterizedType().getTypeName()
        .equals(format("java.util.List<%s>", RestTeamResponse.class.getName()));
  }

  @Override
  public Object resolveParameter(final ParameterContext parameterContext,
      final ExtensionContext extensionContext) throws ParameterResolutionException {
    return teamsToRemove;
  }
}
