package com.practis.rest.dto.google;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GoogleGenerateAudioResponse {

    String audioContent;
}
