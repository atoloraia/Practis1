package com.practis.rest.mapper;

import static java.lang.Math.max;

import com.practis.dto.NewScenarioInput;
import com.practis.rest.dto.company.audio.SaveFileResponse;
import com.practis.rest.dto.company.library.RestCreateScenario;
import com.practis.rest.dto.company.library.RestCreateScenario.Line;
import com.practis.rest.dto.company.library.RestCreateScenario.Scenario;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ScenarioMapper {

    /** Maps input to rest request body. */
    public static RestCreateScenario toRestCreateScenario(
            final NewScenarioInput input, final SaveFileResponse audio) {
        return RestCreateScenario.builder()
                .scenario(
                        Scenario.builder()
                                .title(input.getTitle())
                                .description(input.getDescription())
                                .build())
                .lines(toLines(input, audio))
                .build();
    }

    private static List<Line> toLines(final NewScenarioInput input, final SaveFileResponse audio) {
        final var position = new AtomicInteger();
        final var linesCount = max(input.getCustomerLines().size(), input.getRepLines().size());
        return IntStream.range(0, linesCount)
                .mapToObj(
                        idx -> {
                            final var customerLine =
                                    RestCreateScenario.Line.builder()
                                            .audioId(audio.getId())
                                            .duration(audio.getMetadata().getFormat().getDuration())
                                            .position(position.addAndGet(1))
                                            .speaker("CUSTOMER")
                                            .text(input.getCustomerLines().get(idx))
                                            .build();
                            final var repLine =
                                    RestCreateScenario.Line.builder()
                                            .audioId(audio.getId())
                                            .duration(audio.getMetadata().getFormat().getDuration())
                                            .position(position.addAndGet(1))
                                            .speaker("REP")
                                            .text(input.getRepLines().get(idx))
                                            .build();
                            return List.of(customerLine, repLine);
                        })
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
