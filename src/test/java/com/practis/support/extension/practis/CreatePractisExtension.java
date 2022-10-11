package com.practis.support.extension.practis;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.data.company.NewChallengeInputData.getNewChallengeInput;
import static com.practis.web.selenide.configuration.data.company.NewPractisSetInputData.getNewPractisSetInput;

import com.practis.rest.dto.company.library.RestChallengeResponse;
import com.practis.rest.dto.company.library.RestPractisSetResponse;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class CreatePractisExtension implements
    BeforeEachCallback, AfterEachCallback, ParameterResolver {

  private final List<RestPractisSetResponse> practisSetToRemove = new ArrayList<>();

  @Override
  public void beforeEach(final ExtensionContext context) throws Exception {
    final var fileName = "/audio/sample.mp3";

    final var practisSetInput = getNewPractisSetInput();
    practisSetInput.setTitle(String.format(practisSetInput.getTitle(), timestamp()));

    final var challengeInput = getNewChallengeInput();
    challengeInput.setTitle(String.format(challengeInput.getTitle(), timestamp()));
    final var challenge = practisApi().createChallengeWithLines(challengeInput, fileName);

    final var practisSet = practisApi().createPractisSet(practisSetInput, List.of(challenge));
    practisSetToRemove.add(practisSet);
  }

  @Override
  public void afterEach(final ExtensionContext context) throws Exception {
    practisSetToRemove.forEach(practisSet -> practisApi().deletePractisSet(practisSet.getName()));
  }

  @Override
  public boolean supportsParameter(
      final ParameterContext parameterContext, final ExtensionContext extensionContext)
      throws ParameterResolutionException {
    return parameterContext.getParameter().getType()
        .equals(RestPractisSetResponse.class);
  }

  @Override
  public Object resolveParameter(
      final ParameterContext parameterContext, final ExtensionContext extensionContext)
      throws ParameterResolutionException {
    return practisSetToRemove.stream().findFirst().orElse(null);
  }


}
