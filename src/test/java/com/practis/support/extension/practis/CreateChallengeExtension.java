package com.practis.support.extension.practis;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.data.company.NewChallengeInputData.getNewChallengeInput;
import static java.lang.String.format;

import com.practis.rest.dto.company.library.RestChallengeResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class CreateChallengeExtension
        implements BeforeEachCallback, AfterEachCallback, ParameterResolver {

    private final List<RestChallengeResponse> challengeToRemove = new ArrayList<>();

    @Override
    public void beforeEach(final ExtensionContext context) throws Exception {
        final var fileName = "/audio/sample.mp3";

        final var annotation =
                context.getTestMethod().orElseThrow().getAnnotation(ChallengeExtension.class);
        IntStream.range(0, annotation.count())
                .forEach(
                        idx -> {
                            final var input = getNewChallengeInput();
                            input.setTitle(String.format(input.getTitle(), timestamp()));

                            final var challenge =
                                    practisApi().createChallengeWithLines(input, fileName);
                            challengeToRemove.add(challenge);
                        });
    }

    @Override
    public void afterEach(final ExtensionContext context) throws Exception {
        challengeToRemove.forEach(
                challenge -> practisApi().archiveAndDeleteChallenge(challenge.getId()));
    }

    @Override
    public boolean supportsParameter(
            final ParameterContext parameterContext, final ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return parameterContext
                .getParameter()
                .getParameterizedType()
                .getTypeName()
                .equals(format("java.util.List<%s>", RestChallengeResponse.class.getName()));
    }

    @Override
    public Object resolveParameter(
            final ParameterContext parameterContext, final ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return challengeToRemove;
    }
}
