package com.practis.support.extension.practis;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.data.company.NewScenarioInputData.getNewScenarioInput;

import com.practis.rest.dto.company.library.RestScenarioResponse;
import java.util.ArrayList;
import java.util.List;
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

        final var input = getNewScenarioInput();
        input.setTitle(String.format(input.getTitle(), timestamp()));

        final var scenario = practisApi().createScenarioWithLines(input, fileName);
        scenariosToRemove.add(scenario);
    }

    @Override
    public void afterEach(final ExtensionContext context) throws Exception {
        scenariosToRemove.forEach(
                scenario -> practisApi().archiveAndDeleteScenario(scenario.getTitle()));
    }

    @Override
    public boolean supportsParameter(
            final ParameterContext parameterContext, final ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(RestScenarioResponse.class);
    }

    @Override
    public Object resolveParameter(
            final ParameterContext parameterContext, final ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return scenariosToRemove.stream().findFirst().orElse(null);
    }
}
