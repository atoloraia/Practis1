package com.practis.support.extension.practis;

import static com.practis.rest.configuration.PractisApiV2ClientConfiguration.practisApiClientV2;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.data.company.NewChallengeInputData.getNewChallengeInput;
import static java.lang.String.format;

import com.practis.dto.NewChallengeInput;
import com.practis.rest.dto.company.library.RestChallengeResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

@Slf4j
public class AddArchivedChallengeExtension
        implements BeforeEachCallback, AfterEachCallback, ParameterResolver {

    @Getter(AccessLevel.PACKAGE)
    private final List<RestChallengeResponse> challengeToRemove = new ArrayList<>();

    private final List<NewChallengeInput> challengeInputs = new ArrayList<>();

    @Override
    public void beforeEach(final ExtensionContext context) throws Exception {
        final var fileName = "/audio/sample.mp3";

        final var annotation =
                context.getTestMethod().orElseThrow().getAnnotation(ChallengeExtension.class);

        final var inputChallenge = getNewChallengeInput();
        inputChallenge.setTitle(String.format(inputChallenge.getTitle(), timestamp()));
        challengeInputs.add(inputChallenge);
        final var challenge = practisApi().createChallengeWithLines(inputChallenge, fileName);
        challengeToRemove.add(challenge);
        archiveChallenge(challenge.getTitle());
    }

    public void archiveChallenge(final String name) {
        practisApi()
                .findChallenge(name)
                .ifPresent(
                        challenge -> {
                            practisApiClientV2().archiveChallenge(List.of(challenge.getId()));
                        });
    }

    @Override
    public void afterEach(final ExtensionContext context) throws Exception {
        challengeToRemove.forEach(
                scenario -> {
                    try {
                        practisApi().archiveAndDeleteChallenge(scenario.getId());
                    } catch (Exception e) {
                        log.warn("Can't remove scenario. Reason: {}", e.getMessage(), e);
                    }
                });
    }

    @Override
    public boolean supportsParameter(
            final ParameterContext parameterContext, final ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return parameterContext
                .getParameter()
                .getParameterizedType()
                .getTypeName()
                .equals(format("java.util.List<%s>", NewChallengeInput.class.getName()));
    }

    @Override
    public Object resolveParameter(
            final ParameterContext parameterContext, final ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return challengeInputs;
    }
}
