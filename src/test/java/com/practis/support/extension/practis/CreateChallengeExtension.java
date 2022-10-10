package com.practis.support.extension.practis;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.data.company.NewChallengeInputData.getNewChallengeInput;

import com.practis.rest.dto.company.library.RestChallenge;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class CreateChallengeExtension implements
    BeforeEachCallback, AfterEachCallback, ParameterResolver {

  private final List<RestChallenge> challengeToRemove = new ArrayList<>();

  @Override
  public void beforeEach(final ExtensionContext context) throws Exception {
    final var input = getNewChallengeInput();
    input.setTitle(String.format(input.getTitle(), timestamp()));
    final var challenge = practisApi().createChallenge(input);
    challengeToRemove.add(challenge);
  }

  @Override
  public void afterEach(final ExtensionContext context) throws Exception {
    challengeToRemove.forEach(challenge -> practisApi().deleteChallenge(challenge.getTitle()));
  }

  @Override
  public boolean supportsParameter(
      final ParameterContext parameterContext, final ExtensionContext extensionContext)
      throws ParameterResolutionException {
    return parameterContext.getParameter().getType()
        .equals(RestChallenge.class);
  }

  @Override
  public Object resolveParameter(
      final ParameterContext parameterContext, final ExtensionContext extensionContext)
      throws ParameterResolutionException {
    return challengeToRemove.stream().findFirst().orElse(null);
  }


}
