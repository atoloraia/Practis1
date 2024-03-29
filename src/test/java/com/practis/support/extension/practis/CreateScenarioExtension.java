package com.practis.support.extension.practis;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.data.company.NewScenarioInputData.getNewScenarioInput;
import static java.lang.String.format;

import com.practis.rest.dto.company.library.RestScenarioResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class CreateScenarioExtension
        implements BeforeEachCallback, AfterEachCallback, ParameterResolver {

    private final List<RestScenarioResponse> scenariosToRemove = new ArrayList<>();

    @Override
    public void beforeEach(final ExtensionContext context) throws Exception {
        final var fileName = "/audio/sample.mp3";

        final var annotation =
                context.getTestMethod().orElseThrow().getAnnotation(ScenarioExtension.class);
        IntStream.range(0, annotation.count())
                .forEach(
                        idx -> {
                            final var input = getNewScenarioInput();
                            input.setTitle(format(input.getTitle(), idx + "-" + timestamp()));

                            final var scenario =
                                    practisApi().createScenarioWithLines(input, fileName);
                            scenariosToRemove.add(scenario);
                        });
    }

    @Override
    public void afterEach(final ExtensionContext context) throws Exception {
        scenariosToRemove.forEach(
                scenario -> practisApi().archiveAndDeleteScenario(scenario.getId()));
    }

    @Override
    public boolean supportsParameter(
            final ParameterContext parameterContext, final ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return parameterContext
                .getParameter()
                .getParameterizedType()
                .getTypeName()
                .equals(format("java.util.List<%s>", RestScenarioResponse.class.getName()));
    }

    @Override
    public Object resolveParameter(
            final ParameterContext parameterContext, final ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return scenariosToRemove;
    }
}
