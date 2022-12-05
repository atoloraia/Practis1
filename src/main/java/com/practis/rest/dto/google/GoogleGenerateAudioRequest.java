package com.practis.rest.dto.google;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GoogleGenerateAudioRequest {

    @Builder.Default AudioConfig audioConfig = new AudioConfig();
    Input input;
    @Builder.Default Voice voice = new Voice();

    @Value
    @Builder
    public static class AudioConfig {

        BigDecimal pitch = BigDecimal.valueOf(1.04);
        BigDecimal speakingRate = BigDecimal.valueOf(1.02);
        String audioEncoding = "MP3";
    }

    @Value
    @Builder
    public static class Input {

        String text;
    }

    @Value
    @Builder
    public static class Voice {

        String ssmlGender = "MALE";
        String name = "en-US-Wavenet-D";
        String languageCode = "en-US";
    }
}
