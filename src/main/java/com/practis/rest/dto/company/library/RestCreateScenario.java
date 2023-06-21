package com.practis.rest.dto.company.library;

import java.math.BigDecimal;
import java.util.List;
import javax.sound.sampled.Line;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RestCreateScenario {

    Scenario scenario;
    List<Line> lines;

    @Value
    @Builder
    public static class Line {
        Long audioId;
        BigDecimal duration;
        @Builder.Default List<Object> keywords = List.of();
        Integer position;
        String speaker;
        String text;
    }

    @Value
    @Builder
    public static class Scenario {
        String description;
        String title;
    }
}
