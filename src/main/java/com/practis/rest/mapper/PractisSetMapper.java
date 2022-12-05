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
    public static RestPractisSetRequest toRestCreatePractisSet(
            final NewPractisSetInput input,
            final List<RestChallengeResponse> challenges,
            final List<RestScenarioResponse> scenario) {
        return RestPractisSetRequest.builder()
                .name(input.getName())
                .description(input.getDescription())
                .content(
                        Stream.concat(
                                        toPractisSetContentChallenge(challenges).stream(),
                                        toPractisSetContentScenario(scenario).stream())
                                .collect(Collectors.toList()))
                .build();
    }

    private static List<Content> toPractisSetContentChallenge(
            List<RestChallengeResponse> challenges) {
        return challenges.stream()
                .map(challenge -> Content.builder().id(challenge.getId()).type("CHALLENGE").build())
                .collect(Collectors.toList());
    }

    private static List<Content> toPractisSetContentScenario(List<RestScenarioResponse> scenarios) {
        return scenarios.stream()
                .map(
                        scenario ->
                                Content.builder()
                                        .id(scenario.getId())
                                        .type("SCENARIO")
                                        .minRepsCount(1)
                                        .build())
                .collect(Collectors.toList());
    }
}
