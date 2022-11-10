package com.practis.support.extension.practis;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static java.lang.String.format;

import com.practis.rest.dto.company.RestTeamResponse;
import com.practis.support.extension.dto.TeamWithChildren;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class CreateTeamWithUsersAndPractisSetsExtension implements
    BeforeEachCallback, AfterEachCallback, ParameterResolver {

  private final SignUpUserExtension signUpUserExtension = new SignUpUserExtension();
  private final CreatePractisExtension createPractisExtension = new CreatePractisExtension();

  private RestTeamResponse teamToRemove;

  @Override
  public void beforeEach(final ExtensionContext context) throws Exception {
    final var annotation = context.getTestMethod().orElseThrow()
        .getAnnotation(TeamExtensionWithUsersAndPractisSets.class);
    teamToRemove = practisApi().createTeam(format("test-%s", timestamp()));
    signUpUserExtension.signUpUsers(annotation.users(), 132, 7);
    createPractisExtension.createPractisSets(annotation.practisSets());
  }

  @Override
  public void afterEach(final ExtensionContext context) throws Exception {
    practisApi().deleteTeam(teamToRemove.getName());
    signUpUserExtension.afterEach(context);
    createPractisExtension.afterEach(context);
  }

  @Override
  public boolean supportsParameter(final ParameterContext parameterContext,
      final ExtensionContext extensionContext) throws ParameterResolutionException {
    return TeamWithChildren.class.isAssignableFrom(parameterContext.getParameter().getType());
  }

  @Override
  public Object resolveParameter(final ParameterContext parameterContext,
      final ExtensionContext extensionContext) throws ParameterResolutionException {
    return TeamWithChildren.builder()
        .team(teamToRemove)
        .users(signUpUserExtension.getUsersToRemove())
        .practisSets(createPractisExtension.getPractisSetToRemove())
        .build();
  }
}
