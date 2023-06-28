package com.practis.rest.dto.company.library;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RestPractisSetRequest {

    String name;
    String description;
    Integer pacingId = 2;

    @Value
    @Builder
    public static class Content {
        String contentType;
        Integer scenarioId;
        Integer challengeId;
        Integer minRepsCount;
        @Builder.Default Integer position = 0;
    }
}
