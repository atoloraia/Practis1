package com.practis.rest.dto.company.library;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RestCreateChallenge {

    Challenge challenge;
    @Builder.Default Integer sourceScenarioId = 0;

    @Value
    @Builder
    public static class Line {
        Long audioId;
        BigDecimal duration;
        Integer position;
        String speaker;
        String text;
    }

    @Value
    @Builder
    public static class Challenge {
        String description;
        @Builder.Default Integer sourceScenarioId = 0;
        String title;
    }
}
