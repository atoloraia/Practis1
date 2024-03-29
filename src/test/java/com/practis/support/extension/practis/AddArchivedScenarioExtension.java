package com.practis.support.extension.practis;

import static com.practis.rest.configuration.PractisApiV2ClientConfiguration.practisApiClientV2;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.data.company.NewScenarioInputData.getNewScenarioInput;
import static java.lang.String.format;

import com.practis.dto.NewScenarioInput;
import com.practis.rest.dto.company.library.RestScenarioResponse;
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
public class AddArchivedScenarioExtension
        implements BeforeEachCallback, AfterEachCallback, ParameterResolver {

    @Getter(AccessLevel.PACKAGE)
    private final List<RestScenarioResponse> scenariosToRemove = new ArrayList<>();

    private final List<NewScenarioInput> scenarioInputs = new ArrayList<>();

    @Override
    public void beforeEach(final ExtensionContext context) throws Exception {
        final var annotation =
                context.getTestMethod()
                        .orElseThrow()
                        .getAnnotation(ArchivedScenariosExtension.class);

        final var fileName = "/audio/sample.mp3";
        final var scenarioInput = getNewScenarioInput();
        scenarioInput.setTitle(String.format(scenarioInput.getTitle(), timestamp()));
        scenarioInputs.add(scenarioInput);
        final var scenario = practisApi().createScenarioWithLines(scenarioInput, fileName);
        scenariosToRemove.add(scenario);
        archiveScenario(scenario.getTitle());
    }

    public void archiveScenario(final String name) {
        practisApi()
                .findScenario(name)
                .ifPresent(
                        scenario -> {
                            practisApiClientV2().archiveScenario(List.of(scenario.getId()));
                        });
    }

    @Override
    public void afterEach(final ExtensionContext context) throws Exception {
        scenariosToRemove.forEach(
                scenario -> {
                    try {
                        practisApi().archiveAndDeleteScenario(scenario.getId());
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
                .equals(format("java.util.List<%s>", NewScenarioInput.class.getName()));
    }

    @Override
    public Object resolveParameter(
            final ParameterContext parameterContext, final ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return scenarioInputs;
    }
}
