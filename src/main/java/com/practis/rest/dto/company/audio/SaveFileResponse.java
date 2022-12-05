package com.practis.rest.dto.company.audio;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class SaveFileResponse {

    Long id;

    Metadata metadata;

    @Builder
    @Value
    public static class Metadata {

        Format format;

        @Builder
        @Value
        public static class Format {

            BigDecimal duration;
        }
    }
}
