package com.practis.rest.mapper;

import com.practis.dto.NewPractisSetInput;
import com.practis.rest.dto.company.library.RestChallengeResponse;
import com.practis.rest.dto.company.library.RestPractisSetRequest;
import com.practis.rest.dto.company.library.RestPractisSetRequest.Content;
import com.practis.rest.dto.company.library.RestScenarioResponse;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PractisSetMapper {

    /**
     * Maps input to rest request body.
     *
     * @return
     */
    public static RestPractisSetRequest toRestCreatePractisSet(final NewPractisSetInput input) {
        return RestPractisSetRequest.builder()
                .name(input.getName())
                .description(input.getDescription())
                .build();
    }

    public static List<Content> toRestCreatePractisSetContent(
            final List<RestChallengeResponse> challenges,
            final List<RestScenarioResponse> scenarios) {
        return Stream.concat(
                        toPractisSetContentChallenge(challenges).stream(),
                        toPractisSetContentScenario(scenarios).stream())
                .collect(Collectors.toList());
    }

    private static List<Content> toPractisSetContentChallenge(
            List<RestChallengeResponse> challenges) {
        return challenges.stream()
                .map(
                        challenge ->
                                Content.builder()
                                        .contentType("CHALLENGE")
                                        .challengeId(challenge.getId())
                                        .position(0)
                                        .build())
                .collect(Collectors.toList());
    }

    private static List<Content> toPractisSetContentScenario(List<RestScenarioResponse> scenarios) {
        return scenarios.stream()
                .map(
                        scenario ->
                                Content.builder()
                                        .contentType("SCENARIO")
                                        .scenarioId(scenario.getId())
                                        .minRepsCount(1)
                                        .position(1)
                                        .build())
                .collect(Collectors.toList());
    }
}
