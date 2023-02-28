package com.practis.support.extension.practis;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.data.company.NewChallengeInputData.getNewChallengeInput;
import static com.practis.web.selenide.configuration.data.company.NewPractisSetInputData.getNewPractisSetInputs;
import static com.practis.web.selenide.configuration.data.company.NewScenarioInputData.getNewScenarioInput;
import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

import com.practis.dto.NewPractisSetInput;
import com.practis.rest.dto.company.library.RestChallengeResponse;
import com.practis.rest.dto.company.library.RestScenarioResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.AccessLevel;
import lombok.Getter;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class CreatePractisExtension
        implements BeforeEachCallback, AfterEachCallback, ParameterResolver {

    @Getter(AccessLevel.PACKAGE)
    private final List<NewPractisSetInput> practisSetToRemove = new ArrayList<>();

    private final List<RestChallengeResponse> challengesToRemove = new ArrayList<>();
    private final List<RestScenarioResponse> scenariosToRemove = new ArrayList<>();
    private final AtomicInteger integer = new AtomicInteger();

    @Override
    public void beforeEach(final ExtensionContext context) throws Exception {
        final var annotation =
                context.getTestMethod().orElseThrow().getAnnotation(PractisSetExtension.class);

        createPractisSets(annotation.count());
    }

    void createPractisSets(final int limit) {
        final var fileName = "/audio/sample.mp3";

        final var practisSetInputs =
                getNewPractisSetInputs().stream().limit(limit).collect(toList());

        final var challengeInput = getNewChallengeInput();
        challengeInput.setTitle(String.format(challengeInput.getTitle(), timestamp()));
        final var challenge = practisApi().createChallengeWithLines(challengeInput, fileName);
        challengesToRemove.add(challenge);

        final var scenarioInput = getNewScenarioInput();
        scenarioInput.setTitle(String.format(scenarioInput.getTitle(), timestamp()));
        final var scenario = practisApi().createScenarioWithLines(scenarioInput, fileName);
        scenariosToRemove.add(scenario);

        practisSetInputs.forEach(
                practisSetInput -> {
                    practisSetInput.setName(
                            String.format("test-%s-%s", integer.addAndGet(1), timestamp()));
                    final var response =
                            practisApi()
                                    .createPractisSet(
                                            practisSetInput, List.of(challenge), List.of(scenario));
                    practisSetToRemove.add(
                            NewPractisSetInput.builder()
                                    .id(response.getId())
                                    .name(response.getName())
                                    .build());
                });
    }

    @Override
    public void afterEach(final ExtensionContext context) throws Exception {
        practisSetToRemove.forEach(
                practisSet -> practisApi().deletePractisSet(practisSet.getName()));
        challengesToRemove.forEach(
                challenge -> practisApi().archiveAndDeleteChallenge(challenge.getTitle()));
        scenariosToRemove.forEach(
                scenario -> practisApi().archiveAndDeleteScenario(scenario.getTitle()));
    }

    @Override
    public boolean supportsParameter(
            final ParameterContext parameterContext, final ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return parameterContext
                .getParameter()
                .getParameterizedType()
                .getTypeName()
                .equals(format("java.util.List<%s>", NewPractisSetInput.class.getName()));
    }

    @Override
    public Object resolveParameter(
            final ParameterContext parameterContext, final ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return practisSetToRemove;
    }
}
